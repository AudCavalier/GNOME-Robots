package panels;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

	/**
	 * Clase para guardar las instrucciones del juego en un JOptionPane
	 * Almacenamos las instrucciones de texto en un String y lo agregamos a una JTextArea
	 * Posteriormente el jugador puede consultarlas en la barra de menú del MainFrame en la opción "Cómo jugar
	 * @author AudCavalier
	 *
	 */
public class Instruct extends JOptionPane{

	/**
	 * Constructor de clase
	 */
	public Instruct(){
		/**
		 * Etiqueta para denotar que se refiere a las instrucciones del juego
		 */
		JLabel lab = new JLabel();
		/**
		 * Variable de texto donde se almacenan las instrucciones de cómo jugar
		 */
		String text = settext();
		/**
		 * JTextArea donde se mostrarán las instrucciones del juego
		 */
		JTextArea txt = new JTextArea();
		this.setLayout(null);
		txt.setText(text);
		txt.setEditable(false);
		txt.setText(text);
		txt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lab.setText("Instrucciones de Juego");
		lab.setBounds(120, 0, 400, 20);
		txt.setBounds(0, 20, 400, 600);
		add(lab);
		add(txt);
	}
	
	/**
	 * Este método se utiliza para escribir las instrucciones del juego en la variable de tipo String
	 * @return text; Variable de tipo string con las instrucciones del juego
	 */
	public static String settext(){
		/**
		 * Variable con las instrucciones del juego
		 */
		String text = "Movimiento: \n"
				+ "Q      W      E\n"
		+ "  \\       |       /  \n"
		+ "A -           - D \n"
		+ "  /       |        \\ \n"
		+ "Z       X       C \n\n\n"
		+ "T - Teletransportación normal\n\n"
		+ "G - Teletransportación segura\n (Si la hay, si no, hace teletransportación normal) \n\n"
		+ "B - Auto-completar nivel\n (Los robots se moveran en automático, o pueden matarte).\n (Si los robots se matan entre ellos, cada robot muerto te dará 1\n teletransportación segura)\n\n"
		+ "El objetivo es hacer que todos los robots choquen entre sí,\nevitando que te maten.\n\n"
		+ "Si te toca un robot, o tocas una pila de basura pierdes\n\n"
		+ "Si chocan dos robots entre ellos, \nmueren dejando una pila de basura\n\n"
		+ "Si un robot choca con una pila de basura, muere\n\n"
		+ "Si pierdes, puedes presionar la barra espaciadora para reiniciar\n\nClave\n"
		+" T= Turno\n"
		+"ST= Teletransportaciones seguras";
		return text;
	}
}
