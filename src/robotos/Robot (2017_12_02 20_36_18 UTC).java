/***********************************************************************************************************************************
 * Copyright (c) 2017 Gabriel Valiant
 * Todos los derechos reservados
 **********************************************************************************************************************************/

package robotos;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Clase de los robots, extiende de la clase Actor (con variables y métodos comúnes para todo jugador)
 * @author AudCavalier
 *
 */
public class Robot extends Actor{

	/**
	 * variable para identificar si el robot se debe eliminar
	 */
	public boolean selfdestruct; 

	/**
	 * Constructor de clase
	 * @param x; Número entero, coordenada X del robot
	 * @param y; Número entero, coordenada Y del robot
	 */
	public Robot(int x, int y){
		RobotStart(x, y); 
	}
	
	/**
	 * Método para iniciar al robot
	 * @param x; Coordenada X del robot
	 * @param y; Coordenada Y del robot
	 */
	private void RobotStart(int x, int y){
		this.xpos = x; //fijamos la posición en X
		this.ypos = y; //fijamos la posición en Y
		/**
		 * InputStream para obtener la imagen de los recursos
		 */
		InputStream stream = getClass().getClassLoader().getResourceAsStream("resources/Roboto.png");
		/**
		 * Ícono para almacenar la imagen del robot
		 */
		ImageIcon roboto;
		try {
			roboto = new ImageIcon(ImageIO.read(stream));
			this.sprite = roboto.getImage();
			this.selfdestruct = false; //Indicamos que ahora no se debe eliminar
			this.isalive = true; //Indicamos que el robot está vivo
			getImageDimensions(); //Obtenemos dimensiones para revisar colisiones
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //fijamos la imagen del robot
		
	}
		
}
