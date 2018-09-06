/***********************************************************************************************************************************
 * Copyright (c) 2017 Gabriel Valiant
 * Todos los derechos reservados
 **********************************************************************************************************************************/

package robotos;

import java.awt.Image;
import java.awt.Rectangle;

	/**
	 * Superclase Actor, contiene variables y métodos comunes de todos los jugadores
	 * @author AudCavalier
	 *
	 */
public class Actor {

	/**
	 * Posición X del actor
	 */
	protected int xpos; 
	/**
	 * Posición Y del actor
	 */
	protected int ypos;
	/**
	 * Imagen para visualizar al actor en el grid
	 */
	protected Image sprite;
	/**
	 * Variable para almacenar el ancho de sprite del actor
	 */
	private int width;
	/**
	 * Variable para almacenar la altura del sprite del actor
	 */
	private int height;
	/**
	 * Variable para saber si el actor está vivo o no
	 */
	public boolean isalive = true;
	
	/**
	 * Método para obtener el gráfico del actor
	 * @return sprite, imagen que representa al actor
	 */
	public Image getImg(){
		return sprite;
	}
	
	/**
	 * Método para obtener la posición X del actor
	 * @return xpos; número Entero, posición X del actor
	 */
	public int getx(){
		return this.xpos;
	}
	
	/**
	 * Método para obtener la posición Y del actor
	 * @return ypos; Número entero, posición Y del actor 
	 */
	public int gety(){
		return this.ypos;
	}
	
	/**
	 * Método para obtener un rectángulo a partir del actor
	 * @return rectangle; objeto rectángulo con la información del "cuadro" que abarca el actor
	 */
	public Rectangle getBounds(){
		return new Rectangle(xpos, ypos, width, height);
	}
	
	/**
	 * Método para obtener las dimensiones del sprite del actor
	 */
	protected void getImageDimensions(){
		width = sprite.getWidth(null);
		height = sprite.getHeight(null);
	}
	
	/**
	 * Método para fijar la posición del actor en X, Y (sirve para teletransportaciones, o iniciar posiciones)
	 * @param x; Número entero, posición X del actor
	 * @param y; Número entero, posición Y del actor
	 */
	public void setpos(int x, int y){
		this.xpos = x;
		this.ypos = y;
	}
	
}
