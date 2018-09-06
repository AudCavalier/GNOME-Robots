package panels;

import robotos.*;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
	/**
	 * Clase que muestra un Panel donde se muestran todos los valores de los objetos y poderes que hay en el juego
	 * siendo estos el número de robots, número de pilas de basura, número de teletransportaciones seguras que hay
	 * el turno actual y el nivel que se está jugando
	 * @author AudCavalier
	 */
public class Stats extends JPanel{
	
	/**
	 * Etiqueta para denotar el contador del total de robots activos en el juego
	 */
	private static JLabel rcounter = new JLabel(); 
	/**
	 * Etiqueta para denotar el contador del total de pilas de basura activas en el juego
	 */
	private static JLabel ccounter = new JLabel(); 
	/**
	 * Etiqueta para denotar el contador del total de teletransportaciones seguras disponibles
	 */
	private static JLabel stcounter = new JLabel();
	/**
	 * Etiqueta para denotar el contador de turnos
	 */
	private static JLabel tcounter = new JLabel();
	/**
	 * Etiqueta para denotar el contador del nivel en juego
	 */
	private static JLabel lcounter = new JLabel();
	/**
	 * Variable que almacena el contador de robots activos en juego
	 */
	private static int robotc;
	/**
	 * Variable que almacena el contador de pilas de basura activos en juego
	 */
	private static int scrapc;
	/**
	 * Variable que almacena el número de teletransportaciones seguras disponibles
	 */
	private static int safetelec;
	/**
	 * Variable que almacena el turno actual
	 */
	private static int turnc;
	/**
	 * Variable que almacena el nivel actual
	 */
	private static int levelc; 
	
	/**
	 * Constructor de clase, donde agregamos etiquetas al panel
	 */
	public Stats(){
		setLayout(null); 
		/**
		 * InputStream para obtener imagen de los recursos
		 */
		InputStream stream1 = getClass().getClassLoader().getResourceAsStream("resources/Roboto.png");
		/**
		 * InputStream para obtener imagen de los recursos
		 */
		InputStream stream2 = getClass().getClassLoader().getResourceAsStream("resources/Scrap.png");
		/**
		 * Icono de Robot para mostrar gráficamente la etiqueta del robot
		 */
		ImageIcon rob;
		/**
		 * Icono de pila de basura para mostrar gráficamente la etiqueta de las pilas de basura
		 */
		ImageIcon Scrap;
		try {
			rob = new ImageIcon(ImageIO.read(stream1));
			Scrap = new ImageIcon(ImageIO.read(stream2));
			/**
			 *  Asignación de imagen robot a una etiqueta de robots
			 */
			JLabel robertoroboto = new JLabel("", rob, JLabel.CENTER);
			/**
			 * Asignación de imagen de pila de basura a una etiqueta de pilas de basura
			 */
			JLabel crap = new JLabel("", Scrap, JLabel.CENTER);
			robertoroboto.setBounds(0, 20, 32, 32);
			crap.setBounds(0, 62, 32, 32);
			add(robertoroboto);
			add(crap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * Etiqueta que muestra las iniciales "ST" de "Safe Teleport"
		 */
		JLabel safetele = new JLabel("ST");
		/**
		 * Etiqueta que muestra la palabra "TURN" para identificar el turno
		 */
		JLabel turn = new JLabel("TURN");
		/**
		 * Etiqueta que muestra la palabra "NVL" de "Nivel"
		 */
		JLabel level = new JLabel("NVL");

		//Acomodamos en el panel las JLabel

		rcounter.setBounds(40, 20, 32, 32);
		ccounter.setBounds(40, 62, 32, 32);
		safetele.setBounds(20, 98, 32, 32);
		stcounter.setBounds(40, 98, 32, 32);
		turn.setBounds(10, 140, 32, 32);
		tcounter.setBounds(40, 140, 32, 32);
		level.setBounds(10, 180, 32, 32);
		lcounter.setBounds(40, 180, 32, 32);
		//Agregamos al panel

		add(rcounter);
		add(ccounter);
		add(safetele);
		add(stcounter);
		add(turn);
		add(tcounter);
		add(level);
		add(lcounter);
		update();
	}
	
	/**
	 *Este Mëtodo es muy importante, se llama prácticamente en todas las demás clases, sirve para
	 *actualizar el panel de Stats y mostrar de forma correcta el número de objetos y poderes que hay
	 *si se agregan robots, o se eliminan, así como si se agregan pilas de basura, si el jugador se mueve,
	 *o si cambiamos de nivel, se cambian los valores, por lo que aquí se actualiza el panel
	 */
	public static void update(){
		robotc = Globals.getalivebot();
		safetelec = Globals.getst();
		turnc = Globals.getturn();
		scrapc = Globals.getscraparr();
		levelc = Globals.getlevel();
		rcounter.setText(": " + robotc);
		ccounter.setText(": " + scrapc);
		stcounter.setText(": " + safetelec);
		tcounter.setText(": " + turnc);
		lcounter.setText(": " + levelc);
	}
}
