/***********************************************************************************************************************************
 * Copyright (c) 2017 Gabriel Valiant
 * Todos los derechos reservados
 **********************************************************************************************************************************/

package robotos;

	/**
	 * Esta clase almacena toda la informaci�n num�rica del juego, n�mero de turnos, nivel actual, n�mero de teletransportaciones seguras
	 * se utiliza siempre que se quiera acceder a dicha informaci�n, sin comprometer sus valores (utilizando sets,gets)
	 * la clase por si sola explica su funcionamiento, considerando los turnos, niveles, teletransportaciones seguras, robots
	 * vivos, n�mero de pilas de basura activas; como las variables utilizadas
	 * @author AudCavalier
	 *
	 */
public class Globals {
	/**
	 * Variable que almacena la informaci�n del turno actual
	 */
	private static int turn=0; 
	/**
	 * Variable que almacena el nivel que se est� jugando
	 */
	private static int level=0; 
	/**
	 * Variable que almacena la cantidad de teletransportaciones seguras
	 */
	private static int safetele=0;
	/**
	 * Variable que almacena el n�mero de robots vivos
	 */
	private static int alivebot=0;
	/**
	 * Variable que almacena el n�mero de pilas de basura presentes
	 */
	private static int scrap = 0;
	
	/**
	 * M�todo para obtener el n�mero del turno actual
	 * @return turn
	 */
	public static int getturn(){
		return turn;
	}
	
	/**
	 * M�todo para obtener el n�mero del nivel actual
	 * @return level
	 */
	public static int getlevel(){
		return level;
	}
	
	/**
	 * M�todo para obtener el n�mero de teletransportaciones seguras disponibles
	 * @return safetele
	 */
	public static int getst(){
		return safetele;
	}
	
	/**
	 * M�todo para obtener el n�mero de robots vivos
	 * @return alivebot
	 */
	public static int getalivebot(){
		return alivebot;
	}
	
	/**
	 * M�todo para obtener el n�mero de pilas de basura activas
	 * @return scrap
	 */
	public static int getscraparr(){
		return scrap;
	}
	
	/**
	 * M�todo para fijar el n�mero de pilas de basura activas
	 * @param x; N�mero entero
	 */
	public static void setscraparr(int x){
		scrap = x;
	}
	
	/**
	 * M�todo para fijar el turno actual
	 * @param x; N�mero entero
	 */
	public static void setturn(int x){
		turn = x;
	}
	
	/**
	 * M�todo para fijar el n�mero del nivel actual
	 * @param x; N�mero entero
	 */
	public static void setlevel(int x){
		level = x;
	}
	
	/**
	 * M�todo para fijar el n�mero de teletransportaciones seguras
	 * @param x; N�mero entero
	 */
	public static void setst(int x){
		safetele = x;
	}
	
	/**
	 * M�todo para fijar el n�mero de robots vivos
	 * @param x; N�mero entero
	 */
	public static void setalivebot(int x){
		alivebot = x;
	}
	
}
