/***********************************************************************************************************************************
 * Copyright (c) 2017 Gabriel Valiant
 * Todos los derechos reservados
 **********************************************************************************************************************************/

package robotos;

	/**
	 * Esta clase almacena toda la información numérica del juego, número de turnos, nivel actual, número de teletransportaciones seguras
	 * se utiliza siempre que se quiera acceder a dicha información, sin comprometer sus valores (utilizando sets,gets)
	 * la clase por si sola explica su funcionamiento, considerando los turnos, niveles, teletransportaciones seguras, robots
	 * vivos, número de pilas de basura activas; como las variables utilizadas
	 * @author AudCavalier
	 *
	 */
public class Globals {
	/**
	 * Variable que almacena la información del turno actual
	 */
	private static int turn=0; 
	/**
	 * Variable que almacena el nivel que se está jugando
	 */
	private static int level=0; 
	/**
	 * Variable que almacena la cantidad de teletransportaciones seguras
	 */
	private static int safetele=0;
	/**
	 * Variable que almacena el número de robots vivos
	 */
	private static int alivebot=0;
	/**
	 * Variable que almacena el número de pilas de basura presentes
	 */
	private static int scrap = 0;
	
	/**
	 * Método para obtener el número del turno actual
	 * @return turn
	 */
	public static int getturn(){
		return turn;
	}
	
	/**
	 * Método para obtener el número del nivel actual
	 * @return level
	 */
	public static int getlevel(){
		return level;
	}
	
	/**
	 * Método para obtener el número de teletransportaciones seguras disponibles
	 * @return safetele
	 */
	public static int getst(){
		return safetele;
	}
	
	/**
	 * Método para obtener el número de robots vivos
	 * @return alivebot
	 */
	public static int getalivebot(){
		return alivebot;
	}
	
	/**
	 * Método para obtener el número de pilas de basura activas
	 * @return scrap
	 */
	public static int getscraparr(){
		return scrap;
	}
	
	/**
	 * Método para fijar el número de pilas de basura activas
	 * @param x; Número entero
	 */
	public static void setscraparr(int x){
		scrap = x;
	}
	
	/**
	 * Método para fijar el turno actual
	 * @param x; Número entero
	 */
	public static void setturn(int x){
		turn = x;
	}
	
	/**
	 * Método para fijar el número del nivel actual
	 * @param x; Número entero
	 */
	public static void setlevel(int x){
		level = x;
	}
	
	/**
	 * Método para fijar el número de teletransportaciones seguras
	 * @param x; Número entero
	 */
	public static void setst(int x){
		safetele = x;
	}
	
	/**
	 * Método para fijar el número de robots vivos
	 * @param x; Número entero
	 */
	public static void setalivebot(int x){
		alivebot = x;
	}
	
}
