/***********************************************************************************************************************************
 * Copyright (c) 2017 Gabriel Valiant
 * Todos los derechos reservados
 **********************************************************************************************************************************/

package robotos;

import java.awt.Image;
import java.awt.Rectangle;

	/**
	 * Superclase Actor, contiene variables y m�todos comunes de todos los jugadores
	 * @author AudCavalier
	 *
	 */
public class Actor {

	/**
	 * Posici�n X del actor
	 */
	protected int xpos; 
	/**
	 * Posici�n Y del actor
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
	 * Variable para saber si el actor est� vivo o no
	 */
	public boolean isalive = true;
	
	/**
	 * M�todo para obtener el gr�fico del actor
	 * @return sprite, imagen que representa al actor
	 */
	public Image getImg(){
		return sprite;
	}
	
	/**
	 * M�todo para obtener la posici�n X del actor
	 * @return xpos; n�mero Entero, posici�n X del actor
	 */
	public int getx(){
		return this.xpos;
	}
	
	/**
	 * M�todo para obtener la posici�n Y del actor
	 * @return ypos; N�mero entero, posici�n Y del actor 
	 */
	public int gety(){
		return this.ypos;
	}
	
	/**
	 * M�todo para obtener un rect�ngulo a partir del actor
	 * @return rectangle; objeto rect�ngulo con la informaci�n del "cuadro" que abarca el actor
	 */
	public Rectangle getBounds(){
		return new Rectangle(xpos, ypos, width, height);
	}
	
	/**
	 * M�todo para obtener las dimensiones del sprite del actor
	 */
	protected void getImageDimensions(){
		width = sprite.getWidth(null);
		height = sprite.getHeight(null);
	}
	
	/**
	 * M�todo para fijar la posici�n del actor en X, Y (sirve para teletransportaciones, o iniciar posiciones)
	 * @param x; N�mero entero, posici�n X del actor
	 * @param y; N�mero entero, posici�n Y del actor
	 */
	public void setpos(int x, int y){
		this.xpos = x;
		this.ypos = y;
	}
	
}
