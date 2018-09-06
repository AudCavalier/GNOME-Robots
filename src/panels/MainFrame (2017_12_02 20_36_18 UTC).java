package panels;

import robotos.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

/**
 * Clase principal donde se definira el JFrame principal y se agregar�n paneles para jugar y para ver los datos del juego en curso
 * @author AudCavalier
 *
 */
public class MainFrame extends JFrame{

	/**
	 * El frame principal
	 */
	static MainFrame frame = new MainFrame();
	/**
	 * Panel para mostrar numero de robots, pilas de basura, n�mero de turnos, etc.
	 */
	static JPanel stat = new Stats();
	/**
	 * El grid que mostrar� el juego
	 */
	static JPanel TrueGrid; 
	/**
	 * El frame de las instrucciones de como jugar
	 */
	private Instruct instfr = new Instruct(); 
	/**
	 * JDialog donde se mostrar�n las instrucciones del juego
	 */
	final JDialog inst = new JDialog(this, "Instrucciones", true); 
	/**
	 * Imagen para agregar a la aplicaci�n como �cono personalizado
	 */
	static Image img;

	
	/**
	 * Constructor del frame principal donde se agregan los menus y los paneles
	 */
	public MainFrame(){ 
		
		/**
		 * Barra de men�
		 */
		JMenuBar bar = new JMenuBar();  
		/**
		 * Men� juego, opciones para iniciar nuevo juego o salir del juego
		 */
		JMenu Game = new JMenu("Juego");
		/**
		 * Men� M�s, opciones para ver las instrucciones y con informaci�n del juego
		 */
		JMenu About = new JMenu("M�s");
		/**
		 * Opci�n para iniciar nuevo juego
		 */
		JMenuItem niu = new JMenuItem("Nuevo Juego");
		/**
		 * Opci�n para salir del juego
		 */
		JMenuItem exito = new JMenuItem("Salir");
		/**
		 * Opci�n para ver las instrucciones de c�mo jugar
		 */
		JMenuItem Instruct = new JMenuItem("C�mo jugar");
		/**
		 * Opci�n para ver informaci�n del juego
		 */
		JMenuItem inf = new JMenuItem("Acerca de este juego");
		
		//agregamos a la barra de men� los men�s "Juego" y "M�s"
		bar.add(Game);
		bar.add(About);
		//A�adimos al men� "Juego" opciones para iniciar nuevo juego o para salir
		Game.add(niu);
		Game.add(exito);
		//ActionListener para acci�n "Nuevo"
		niu.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						Globals.setlevel(1); //Fijamos el nivel a jugar como el primer nivel (1)
						run(); //Corremos el m�todo "run" que inicializar� un nuevo juego
					}					
				}
			);
		//ActionListener para acci�n salir
		exito.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//Mostramos un JOptionPane que preguntar� si queremos salir
						int exit = JOptionPane.showConfirmDialog(inst, "Deseas salir del juego", "Salir", JOptionPane.YES_NO_OPTION);
						if(exit==JOptionPane.YES_OPTION) //Si el usuario elige la opci�n "SI" salimos del programa
							System.exit(0);
					}
				
				}
			);
		//Al men� "M�s" le a�adimos las opciones para mostrar instrucciones o para mostrar informaci�n sobre el juego
		About.add(Instruct);
		About.add(inf);
		//Actionlistener para la opci�n "Instrucciones"
		Instruct.addActionListener(
				new ActionListener(){
					@Override
					//mostramos el JDialog con las instrucciones de c�mo jugar
					public void actionPerformed(ActionEvent e) {
						inst.setSize(420, 662);
						inst.setContentPane(instfr); 
						inst.setVisible(true); 
					}			
				}
			);
		//ActionListener para la opci�n "Informaci�n del juegO"
		inf.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//Mostramos un mensajito de CopyRight
						JOptionPane.showMessageDialog(inst, "Versi�n 2.0\n �2017 Gabriel Valiant\n todos los derechos reservados");
					}			
				}
			);
		//Fijamos como barra de men� el objeto previamente definido y llenado
		setJMenuBar(bar);
		/**
		 * InputStream para obtener la imagen de los recursos
		 */
		InputStream stream = getClass().getClassLoader().getResourceAsStream("resources/Roboto.png");
		/**
		 * �cono de la aplicaci�n
		 */
		ImageIcon icon;
		try {
			icon = new ImageIcon(ImageIO.read(stream));
			img = icon.getImage();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	/**
	 * M�todo Run, utilizado para iniciar el juego
	 */
	public void run(){
		/*Dentro de un bloque Try/catch creamos una instancia del objeto TrueGrid, que es el tablero donde se lleva a cabo el juego
		 * Si por alg�n error falla el juego (ya sea que la JVM detecte un error, o intencionalmente se haya logrado hacer "crashear"
		 * el juego) Muestre un mensaje de error y salga del programa inmediatamente.
		 * Cada que se cargue un nuevo juego o un nuevo nivel, esta funci�n es llamada
		 */
		try{
			Globals.setturn(0); //Fijamos los turnos en 0
			Globals.setscraparr(0); //Fijamos el arreglo de pilas basura en 0
			Globals.setalivebot(0); //Fijamos el n�mero de robots en 0, esto para evitar problemas con el ArrayList
			Stats.update(); //Modificamos los n�meros mostrados por el panel "stat", llamando su m�todo definido
			//Revisamos si ya existe una instancia de truegrid, para evitar duplicidad, si ya existe una la eliminamos
			if (TrueGrid != null)
				frame.remove(TrueGrid);
			//tras asegurarnos que no haya duplicidad, creamos una nueva instancia de truegrid
			TrueGrid = new TrueGrid();
			TrueGrid.setBounds(0, 0, 680, 600);
			//A�adimos la nueva instancia de TrueGrid al frame
			frame.add(TrueGrid);
			//Lo fijamos como "Focusable" para que podamos utilizar el keylistener
			TrueGrid.setFocusable(true);
			TrueGrid.requestFocusInWindow();			
		}catch(Exception e){
			//Si hay alg�n error al iniciar el juego, mostramos mensaje de error y salimos del programa
			JOptionPane.showMessageDialog(inst, "ERROR FATAL, NO SE PUEDE INICIALIZAR EL JUEGO");
			System.exit(0);
		}
	}
	
	/**
	 * M�todo principal 
	 */
	public static void main(String args[]){
		//Aqu� inicializamos el frame principal, as� como el frame "Stats"
		stat.setBounds(690, 0, 140, 600);
		frame.setIconImage(img);
		frame.setTitle("Robotos");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(790, 638);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.add(stat);
	}
}
