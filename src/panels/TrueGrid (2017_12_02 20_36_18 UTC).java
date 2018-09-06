package panels;

import robotos.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.Timer;

	/**
	 * Esta clase es la clase principal el juego, el tablero donde se lleva a cabo todo el juego
	 * Implementa la interfaz ActionListener para manejar las entradas de teclado
	 * Además se encarga de dibujar todos los objetos del juego, por lo que contiene métodos de paintcomponent
	 * @author AudCavalier
	 */
public class TrueGrid extends JPanel implements ActionListener{
	
	/**
	 * Variable de tipo booleano para saber si la entrada el jugador solicita autocompletar el nivel
	 */
	private boolean autofinishswitch;
	/**
	 * Variable booleana para evitar acciones consecutivas del jugador sin reacción de los robot
	 */
	protected boolean keyholder;
	/**
	 * Variable para identificar si seguimos jugando o si hemos perdido
	 */
	private boolean ingame;
	/**
	 * El jugador
	 */
	private Player player;
	/**
	 * ArrayList de los robots en juego
	 */
	private ArrayList<Robot> roboto = new ArrayList<>();
	/**
	 * ArrayList de las pilas de basura en el juego
	 */
	private ArrayList<Junk> scrap = new ArrayList<>();
	/**
	 * Instanciamos el mainframe
	 */
	private MainFrame mf = new MainFrame();
	/**
	 * Variable para saber en qué nivel estamos
	 */
	private int level;
	/**
	 * Timer para controlar el tiempo de realizar acciones
	 */
	private Timer timer;
	/**
	 * Demora para poner "control" al timer (Ver función actionPerformed)
	 */
	private final int DELAY = 100;
	
	/**
	 * Constructor de clase
	 */
	public TrueGrid(){
		init(); //Iniciamos el TrueGrid
	}
	
	/**
	 * Función para iniciar el ciclo del juego
	 */
	public void init(){
		addKeyListener(new TAdapter()); //Agregamos el KeyListener (subclase interior)
		setFocusable(true); //Hacemos que haga "Focus" el panel
		gameStart(); //Iniciamos el juego
		timer = new Timer(DELAY, this); //Inicializamos variable timer
		timer.start(); //Comenzamos el timer (para que el juego "Corra" de acuerdo con el reloj; ver función actionPerformed)
	}
	
	/**
	 * Función para iniciar un nuevo juego, donde se colocan los actores en el tablero
	 */
	public void gameStart(){
		ingame = true; //Al iniciar el juego declaramos que estamos jugando, esto es falso sólo si el jugador ha muerto
		keyholder=true; //Inicializamos variable para controlar cuando se deja presionada una tecla
		level = Globals.getlevel(); //Conseguimos el número del nivel en que estamos
		//A continuación agregaremos los robots al ArrayList de robots
		for (int i=0;i<level*10;i++){ //Siempre habrá level*10 robots, por lo que iteramos ese número de veces
			Random rand = new Random(); //Creamos un nuevo random
			//A continuación conseguimos números aleatorios para x y y
			int robotx=rand.nextInt(641/32)*32;
			int roboty=rand.nextInt(545/32)*32;
			/*
			 * Nos aseguramos que ningún robot se cree en el punto inicial del jugador, por lo que si sus coordenadas
			 * X y Y son 320 y 256 al mismo tiempo, cambiamos hasta que una sea diferente 
			 */
			while (robotx ==320 && roboty == 256){
				robotx=rand.nextInt(641/32)*32;
				roboty=rand.nextInt(545/32)*32;
			}
			//Creamos un nuevo objeto robot y pasamos sus coordenadas
			Robot robot = new Robot(robotx, roboty);
			//Agregamos el robot al ArrayList
			roboto.add(robot);
		}
		Globals.setalivebot(roboto.size()); //Fijamos el número de robots vivos
		Globals.setst(Globals.getst()); //Obtenemos el número de teletransportaciones seguras
		Stats.update(); //Actualizamos el panel que muestra números de objetos (o poderes) disponibles
		player = new Player(); ///creamos al jugador
	}
	
	/**
	 * Función para dibujar en el frame
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawRect(0, 0, 673, 577);		
		//Si el jugador está vivo comenzamos a dibujar
		if(ingame){
			drawfunc(g); //dibujamos al jugador
			drawRobots(g); //dibujamos a los robots
			drawScraps(g); //dibujamos las pilas de basura
			if(player.isalive==false){ //Si en un momento del juego el jugador muere, hacemos falsa la variable "ingame"
				ingame=false; 
			}
		}else{ //Si no estamos en el juego, primero detenemos el timer para dejar de llamar la función actionPerformed
			timer.stop();
			drawGameOver(g); //dibujamos un mensaje de "Game Over"
		}
		Toolkit.getDefaultToolkit().sync();	
	}
	
	/**
	 * Función para dibujar una pantalla de "Game Over" cuando hemos perdido
	 * @param g
	 */
	private void drawGameOver(Graphics g){
		Globals.setst(0); //Fijamos las teletransportaciones seguras en 0
		String msg = "Game Over"; //Creamos una cadena con el mensaje "Game Over"
		Font small = new Font("Helvetica", Font.BOLD, 14); //Fijamos el tamaño y tipo de letra
		FontMetrics fm = getFontMetrics(small); //Esto graba la información de la cadena (tamaño, posición, etc), con el fin de poder dibujarla
		g.setColor(Color.black);
		g.setFont(small);
		g.drawString(msg, (this.getWidth()-fm.stringWidth(msg))/2, 544/2); //Dibujamos el mensaje de game over
	}
	
	/**
	 * Función para dibujar al jugador
	 * @param g
	 */
	public void drawfunc(Graphics g){
		g.drawImage(player.getImg(), player.getx(), player.gety(), this);
	}
	
	/**
	 * Función para dibujar los robots
	 * @param g
	 */
	public void drawRobots(Graphics g){
		for(Robot robot: roboto){
			if(robot.isalive)
				g.drawImage(robot.getImg(), robot.getx(), robot.gety(), this);
		}
	}
	
	/**
	 * Función para dibujar las pilas de basura
	 * @param g
	 */
	public void drawScraps(Graphics g){
		for(Junk junk: scrap){
			g.drawImage(junk.getImg(), junk.getx(), junk.gety(), this);
		}
	}
	
	/**
	 * esta función sirve para eliminar robots del arreglo de robots y dejar basura en su lugar
	 */
	public void removerobots(){
	Iterator<Robot> it=roboto.iterator(); //creamos un iterator para recorrer el arraylist de robots y poder modificarlo
		while(it.hasNext()){
			if(it.next().isalive==false){ //Si el robot debería morir, lo quitamos del arreglo
				if (autofinishswitch==true){ //si los robots se están suicidando, aumentan los safetele
					Globals.setst(Globals.getst()+1);
					Stats.update(); //actualizamos la pantalla con la información del nivel
				}
				it.remove(); //eliminamos el robot en cuestión del arreglo
			}
		}
	}	
	
	/**
	 * función para revisar colisiones, en este juego si algunos objetos colisionan, tienen distintas reglas para "morir"
	 */
	public void checkCollisions(){
		/**
		 * Rectángulo con las dimensiones del jugador
		 */
		Rectangle plr = player.getBounds(); //instanciamos un rectángulo con las dimensiones del jugador (colisionador)
		for (Robot robot: roboto){ //iteramos sobre la lista de robots
			/**
			 * Rectángulo con las dimensiones de un robot
			 */
			Rectangle r1r = robot.getBounds(); //instanciamos un rectángulo con las dimensiones del robot de la iteración "i"
			for(Robot robot2: roboto){ //iteramos nuevamente sobre la lista de robots
				if(robot != robot2){ //nos aseguramos que ambos robots "i" y "j" sean distintos
					/**
					 * Rectángulo con las dimensiones de un robot
					 */
					Rectangle r2r = robot2.getBounds(); //instanciamos un rectángulo con las dimensiones del robot de la iteración "j"
					if(r1r.intersects(r2r)){ //Revisamos si ambos robots intersecan: es decir, sus rectángulos se están encimando
						robot2.isalive = false; //Fijamos que el robot "j" ya ha muerto
						//Obtenemos coordenadas X y Y de donde murió el robot en cuestión para crear una pila de basura y agregarla
						//a su ArrayList
						int scrapx = robot2.getx();
						int scrapy = robot2.gety();
						Junk junk = new Junk(scrapx, scrapy);
						scrap.add(junk);
						break;
					}
				}
			}
			for(Junk junk: scrap){ //iteramos sobre la lista de pilas de basura para detectar si el robot "i" chocó con una pila de basura
				/**
				 * Rectángulo con las dimensiones de una pila de basura
				 */
				Rectangle rsr = junk.getBounds();
				if(r1r.intersects(rsr)){
					robot.isalive = false;
				}
			}
			if(r1r.intersects(plr)){ //revisamos si el robot chocó con el jugador, de ser así, el jugador muere
				player.isalive=false;
			}
		}
		for(Junk junk: scrap){ //iteramos sobre la lista de pilas de basura para detectar si el jugador chocó con una
			/**
			 * Rectángulo con las dimensiones de una pila de basura
			 */
			Rectangle rsr = junk.getBounds();
			if(rsr.intersects(plr)){
				player.isalive=false;
			}
		}
		removerobots(); //lamamos la función para eliminar robots, si es que hubo robots que murieron
		//Actualizamos informaciones
		Globals.setalivebot(roboto.size());
		Globals.setscraparr(scrap.size());
		Stats.update();
	}
	
	/**
	 * Función para mover al robot
	 */
	public void RobotMove(){
		for(Robot robot: roboto){
			/*
			 *Cada que el jugador se mueva, los robots deben moverse hacia el
			 *los siguientes movimientos resuelven de tal manera que cada robot
			 *se vaya acercando al jugador
			 */
				if (robot.getx()<player.getx()){ 
					robot.setpos(robot.getx()+32, robot.gety());
				}else if (robot.getx()>player.getx()){
					robot.setpos(robot.getx()-32, robot.gety());
				}
				if (robot.gety()<player.gety()){
					robot.setpos(robot.getx(), robot.gety()+32);
				}else if (robot.gety()>player.gety()){
					robot.setpos(robot.getx(), robot.gety()-32);
				}
		}
	}
	
	/**
	 * Función utilizada para obtener coordenada X de teletransportación segura (es decir, no hay robots en la posición X)
	 * @return xt; Posición en X que no esté ocupada por un robot
	 */
	public int isXSafePlace(){ 
		Random rand = new Random();
		int xt=rand.nextInt(641/32)*32;
		for(Robot robot: roboto){
			while(xt==robot.getx()){
				xt=rand.nextInt(641/32)*32;
			}
		}
		return xt;
	}
	
	/**
	 * Función utilizada para obtener coordenada Y de telentrasportación segura (es decir, no hay robots en la posición Y)
	 * @return xy; Posición en Y que no esté ocupada por un robot
	 */
	public int isYSafePlace(){ 
		Random rand = new Random();
		int yt=rand.nextInt(545/32)*32;
		for(Robot robot: roboto){
			while(yt==robot.gety()){
				yt=rand.nextInt(641/32)*32;
			}
		}
		return yt;
	}
	
	/**
	 *  Función actionPerformed, propia de la interfaz ActionListener, esta se invoca cada que una acción se da a lugar
	 *  Al utilizar un timer con un DELAY, nos aseguramos que esta función sea llamada cada DELAY
	 */
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(player.playermoved == true){ //Si hubo un input de teclado, resolvemos movimiento de jugador
			player.move();
			Globals.setturn(Globals.getturn() +1); //aumentamos número de turnos
			RobotMove(); //resolvemos movimiento de robots
			checkCollisions(); //revisamos si hubo colisiones
		}
		if(roboto.size()==0){ //Si no hay robots, cargamos el siguiente nivel
			Globals.setlevel(Globals.getlevel()+1); //fijamos el número del nivel
			init(); //volvemos a iniciar el tablero
			mf.run(); //Hacemos que el mainframe resuelva los movimientos de instanciar TrueGrid (para evitar duplicidad de tableros)
		}
		if(player.safetporting==true){ //Si hubo input de teletransportación segura, buscamos un lugar seguro para teletransportar
			player.setpos(isXSafePlace(), isYSafePlace());
			player.safetporting=false; //tras la teletransportación, esperamos otra instrucción de teletransportación segura antes de repetir
		}
		if(player.auto==true){ //Si hubo input de auto-completar nivel, dejamos que los robots comiencen a moverse automáticamente
			autofinishswitch=true;
			RobotMove();
			timer.setDelay(500); //Fijamos un delay de 0.5 segundos para que se puedan hacer los cálculos necesarios además de hacer más visual
			//El autocompletado del nivel
			checkCollisions(); //resolvemos colisiones
			if(!player.isalive || roboto.size()<=0) //en el momento en que muera el jugador o acaben los robots, dejamos de esperar
				player.auto=false;
		}
		repaint(); //llamamos el repaint para actualizar el panel
	}
	
	/**
	 * subclase interna para manejar el input del jugador
	 * @author AudCavalier
	 *
	 */
	private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
            keyholder = true;  //permitimos que se vuelva a recibir entrada de teclado
            player.playermoved = false; //declaramos que el jugador ha dejado de moverse
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e); //resolvemos el input desde el jugador
            timer.setDelay(170); //aumentamos un poco el delay para evitar que por accidente se obtenga un doble input
            keyholder = false; //denegamos entrada de teclado hasta que se suelte la tecla actual
        }
    }
}
