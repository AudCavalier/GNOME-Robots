/***********************************************************************************************************************************
 * Copyright (c) 2017 Gabriel Valiant
 * Todos los derechos reservados
 **********************************************************************************************************************************/

package robotos;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

	/**
	 * Clase para las pilas de basura, cuando dos robots chocan, estos se destruyen y dejan una pila de basura
	 * A pesar de no tener otra función, las pilas de basura son parte fundamental del juego
	 * sirven para ayudar a matar otros robots, como obstáculo y también para que se autodestruyan los robots contra ellas
	 * al activar el autocompletado de nivel y así de teletransportaciones seguras
	 * @author AudCavalier
	 *
	 */
public class Junk extends Actor {

	/**
	 * Constructor de clase
	 * @param x; Número entero, posición X
	 * @param y; Número entero, posición Y
	 */
	public Junk(int x, int y){
		JunkStart(x, y); //Constructor: Inicializamos pila de basura
	}
	
	/**
	 * Método para inicializar la pila de basura
	 * @param x; Número entero, posición X
	 * @param y; Número entero, posición Y
	 */
	private void JunkStart(int x, int y){
		/**
		 * InputStream para obtener la imagen de los recursos
		 */
		InputStream stream = getClass().getClassLoader().getResourceAsStream("resources/Scrap.png");
		/**
		 * Ícono para almacenar la imagen de la pila de basura
		 */
		ImageIcon scrap;
		try {
			scrap = new ImageIcon(ImageIO.read(stream));
			this.sprite = scrap.getImage();
			this.xpos = x; //fijamos posición en X
			this.ypos = y; //fijamos posición en Y
			getImageDimensions(); //Obtenemos dimensiones para calcular colisiones 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//fijamos imagen del objeto

	}
	
}
