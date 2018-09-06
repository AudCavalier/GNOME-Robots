/***********************************************************************************************************************************
 * Copyright (c) 2017 Gabriel Valiant
 * Todos los derechos reservados
 **********************************************************************************************************************************/

package robotos;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import panels.*;

	/**
	 * Clase del jugador, extiende la clase actor (que contiene posiciones, sprite, y m�todos com�nes con otros jugadores)
	 * @author AudCavalier
	 *
	 */
public class Player extends Actor{
	/**
	 * Variable booleana para verificar si el jugador puede teletransportarse de forma segura
	 */
	public boolean safetporting = false; 
	/**
	 * Variable booleana para verificar si el jugador ha realizado un movimiento (para que los robots hagan el suyo)
	 */
	public boolean playermoved = false;
	/**
	 * Variable booleana para determinar si el jugador esperar� a que termine el nivel de forma autom�tica
	 */
	public boolean auto = false;
	/**
	 * Variable para manejar cambio en X
	 */
	private int cx;
	/**
	 * Variable para manejar cambio en Y
	 */
	private int cy;
	
	/**
	 * Constructor de clase
	 */
	public Player(){
		PlayerStart(); //iniciamos el jugador
	}
	
	/**
	 * M�todo para iniciar al jugador
	 */
	private void PlayerStart(){
		/**
		 * InputStream para obtener la imagen de los recursos
		 */
		InputStream stream = getClass().getClassLoader().getResourceAsStream("resources/Stan.png");
		/**
		 * �cono para almacenar la imagen del jugador
		 */
		ImageIcon stanlee;
		try {
			stanlee = new ImageIcon(ImageIO.read(stream));
			sprite = stanlee.getImage();
			this.xpos = 320; //fijamos posicion inicial del jugador, este siempre comenzar� en el medio
			this.ypos = 256;
			getImageDimensions(); //almacenamos la informaci�n de las dimensiones del jugador para revisar colisiones cuando es necesario
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //fijamos el sprite del jugador

	}
	
	/**
	 * M�todo para determinar el cambio de movimiento del jugador
	 */
	public void move(){ //cada que el jugador hace un movimiento, cambia su posici�n en X y en Y
		xpos += cx;
		ypos += cy;
		/*
		 * A continuaci�n revisamos si el jugador se ha salido de los l�mites del tablero, de ser as�, dijamos que su posici�n es el l�mite
		 * propio, si se sale de X o Y en 0, su posici�n se fija en 0, y lo mismo con el l�mite final de X y Y.
		 */
		if(xpos <=0) 
			xpos = 0;
		if(xpos >=640)
			xpos = 640;
		if(ypos <=0)
			ypos =0;
		if(ypos >=544)
			ypos = 544;
	}

	/**
	 * M�todo para determinar el cambio de posici�n de acuerdo al input recibido
	 * @param e
	 */
	public void keyPressed(KeyEvent e){
		int key=e.getKeyCode();
		//resolvemos todos los posibles movimientos del jugador, seg�n la entrada de teclado recibida
		if(key==KeyEvent.VK_Q){
			cx = -32;
			cy = -32;
			playermoved=true;
		}
		if(key==KeyEvent.VK_A){
			cx = -32;
			cy = 0;
			playermoved=true;
		}
		if(key==KeyEvent.VK_E){
			cy = -32;
			cx = 32;
			playermoved=true;
		}
		if(key==KeyEvent.VK_X){
			cx = 0;
			cy = 32;
			playermoved=true;
 		}
		if(key==KeyEvent.VK_C){
			cx = 32;
			cy = 32;
			playermoved=true;
		}
		if(key==KeyEvent.VK_D){
			cx = 32;
			cy = 0;
			playermoved=true;
		}
		if(key==KeyEvent.VK_Z){
			cx = -32;
			cy = 32;
			playermoved=true;
		}
		if(key==KeyEvent.VK_W){
			cx = 0;
			cy = -32;
			playermoved=true;
		}
		if(key==KeyEvent.VK_T){ //Teletransportaci�n normal
			Random rand = new Random();
			int xt=rand.nextInt(641/32)*32;
			int yt=rand.nextInt(545/32)*32;
			setpos(xt, yt);
			playermoved=true;
		}
		if(key==KeyEvent.VK_G){ //Teletransportaci�n segura, (si la hay)
			if(Globals.getst()>0){
				Globals.setst(Globals.getst()-1);
				safetporting=true;
			}else{
			Random rand = new Random();
			int xt=rand.nextInt(641/32)*32;
			int yt=rand.nextInt(545/32)*32;
			setpos(xt, yt);
			}
			playermoved=true;
		}
		if(key==KeyEvent.VK_B){ //Autocompletar el nivel
			cx = 0;
			cy = 0;
			auto = true;
			playermoved=true;
		}
		if(playermoved==true){ //Siempre que el jugador se haya movido, actualizamos el tablero Stats
			Stats.update();
		}
	}
	
	/**
	 * M�todo para determinar que ya se dej� de presionar una tecla
	 * @param e
	 */
	public void keyReleased(KeyEvent e){
		//Al soltar la tecla, fijamos el cambio que se le har� a X, Y en 0
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_Q || key == KeyEvent.VK_W || key==KeyEvent.VK_E || key == KeyEvent.VK_A || key==KeyEvent.VK_D || key == KeyEvent.VK_Z || key == KeyEvent.VK_X || key == KeyEvent.VK_C){
			cx = 0;
			cy = 0;
		}
	}
	
}
