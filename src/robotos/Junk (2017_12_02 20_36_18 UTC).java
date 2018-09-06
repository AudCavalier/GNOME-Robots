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
	 * A pesar de no tener otra funci�n, las pilas de basura son parte fundamental del juego
	 * sirven para ayudar a matar otros robots, como obst�culo y tambi�n para que se autodestruyan los robots contra ellas
	 * al activar el autocompletado de nivel y as� de teletransportaciones seguras
	 * @author AudCavalier
	 *
	 */
public class Junk extends Actor {

	/**
	 * Constructor de clase
	 * @param x; N�mero entero, posici�n X
	 * @param y; N�mero entero, posici�n Y
	 */
	public Junk(int x, int y){
		JunkStart(x, y); //Constructor: Inicializamos pila de basura
	}
	
	/**
	 * M�todo para inicializar la pila de basura
	 * @param x; N�mero entero, posici�n X
	 * @param y; N�mero entero, posici�n Y
	 */
	private void JunkStart(int x, int y){
		/**
		 * InputStream para obtener la imagen de los recursos
		 */
		InputStream stream = getClass().getClassLoader().getResourceAsStream("resources/Scrap.png");
		/**
		 * �cono para almacenar la imagen de la pila de basura
		 */
		ImageIcon scrap;
		try {
			scrap = new ImageIcon(ImageIO.read(stream));
			this.sprite = scrap.getImage();
			this.xpos = x; //fijamos posici�n en X
			this.ypos = y; //fijamos posici�n en Y
			getImageDimensions(); //Obtenemos dimensiones para calcular colisiones 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//fijamos imagen del objeto

	}
	
}
