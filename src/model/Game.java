package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import view.FichasPanel;
import view.GamePanel;
import exceptions.GameException;

import java.util.HashMap;

public class Game{
    
	public final static int DIM_BOARD = 20;
	
	private boolean primeraRonda;
	private boolean juegoTerminado;
    private List<Jugador> jugadores;  
    private HashMap<String, Integer> mapaCasillas;
    private List<GameObserver> go;

    // TODO Borrar 
    // private Jugador currentPlayer;
    // private Ficha currentFicha;
    
    
    private int currentPlayer = 0;
    private int currentFicha = 0;

    private int turno;
    private int dificultad;

    
    

    public Game() {
    	// Inicializo los atributos de la clase en el constructor
    	this.primeraRonda = true;
    	this.juegoTerminado = false;
    	this.jugadores = new ArrayList<Jugador>();
    	this.mapaCasillas = new HashMap<String, Integer>();
    	this.go = new ArrayList<GameObserver>();  	
    	this.turno=0;
    	this.dificultad=2;//1-A;2M;3B
    	
    }
    
       
    public void initJugadores(int p, List<IAType> iaList) {
    	for(int i = 0; i < p; i++) {
    		jugadores.add(new Jugador(i));
    	}
    	for(int i = p; i < p+iaList.size(); i++) {
    		IAType ia = iaList.get(i-p); 
    		
    		jugadores.add(new JugadorIA(i, ia.getLevel()));    		
    	}
    	
    }
    
     
    public void update() {  
    	
    	
    	currentPlayer++;
    	if(currentPlayer > jugadores.size()-1) {
    		
    		primeraRonda = false;
			currentPlayer = 0;
			turno++;
		}
    	
    	if(jugadores.get(currentPlayer).computerAction(this)) {  
    		update();
    	} 	
       
    	for(GameObserver o : go) {
    		o.update(jugadores.get(currentPlayer));
			//o.update(null);
		}
    	
    	
    	
    }
  
    
    
    public boolean casillaVacia(int x, int y) {
    	boolean ok=true;
    	Integer[] posicion = {0,0};
    	posicion[0]=x;
    	posicion[1]=y;
    	if(mapaCasillas.containsKey(Arrays.toString(posicion))) { //Si estÃ¡ ocupada
			ok=false;
		}
    	return ok;
    	
    }
    
    
    
    public boolean jugarPrimeraRonda(int f, int y, int x) {
    	boolean fichaAnadida = false;
    	Ficha ficha;
    	ficha = jugadores.get(currentPlayer).getFicha(f);
    	ficha.moverFicha(x, y);
    	Integer[] posicion = {0,0};
    	
    	
    	if(jugadorPuedeColocar(currentPlayer) && checkEsquinas(ficha)) {
    		for(int i = 0; i < ficha.getNumCasillas(); i++) {    		
    			posicion[0] = Integer.valueOf(ficha.getFichaX(i));
    			posicion[1] = Integer.valueOf(ficha.getFichaY(i));
        		
        		if(posicion[0] < 0 || posicion[0] > DIM_BOARD || 
        				posicion[1] < 0 || posicion[1] > DIM_BOARD) {
        	    	
        	    	throw new GameException("Posición no válida.\n");
        		}
        		
        		if(mapaCasillas.containsKey(Arrays.toString(posicion))) { //Si esta ocupada
        			throw new GameException("Posición no válida.\n");
        		}
        	}
    		
    		for(int i = 0; i < ficha.getNumCasillas(); i++){
    			posicion[0] = ficha.getFichaX(i);
    			posicion[1] = ficha.getFichaY(i);
    			mapaCasillas.put(Arrays.toString(posicion), Integer.valueOf(currentPlayer));
    			for(GameObserver o : go) {// TODO ficha.getEquipo()
    				o.onFichaAnadida(ficha.getFichaX(i), ficha.getFichaY(i), f, jugadores.get(currentPlayer));

    			}
    		}
    		jugadores.get(currentPlayer).borrarPieza(f);
    		fichaAnadida = true;
    	}
    	else {
    		throw new GameException("La primera ficha debe colocarse en una de las esquinas\n");
    	}

    	return fichaAnadida;
    }
    
    
    /**
     * checkEsquinas:
     * LLama a cada casilla de la ficha f y comprueba si la posición de al menos una de
     * las casillas se corresponda con la posición de alguna de las esquinas.
     * @param ficha
     * @return
     */
    
    
    public boolean checkEsquinas(Ficha ficha) {// comprueba que la ficha se coloca en alguna de las esquinas
		Integer[] esquina1 = { 0, 0 }, esquina2 = { DIM_BOARD - 1, 0 }, esquina3 = { 0, DIM_BOARD - 1 },
				esquina4 = { DIM_BOARD - 1, DIM_BOARD - 1 };
		Integer[] casilla = { 0, 0 };
		for (int i = 0; i < ficha.getNumCasillas(); ++i) {
			casilla[0] = ficha.getFichaX(i);
			casilla[1] = ficha.getFichaY(i);
			if (casilla[0] == esquina1[0] && casilla[1] == esquina1[1]) {
				return true;
			} else if (casilla[0] == esquina2[0] && casilla[1] == esquina2[1]) {
				return true;
			} else if (casilla[0] == esquina3[0] && casilla[1] == esquina3[1]) {
				return true;
			} else if (casilla[0] == esquina4[0] && casilla[1] == esquina4[1]) {
				return true;
			}
		}
		return false;
	}
    
    //------------
 
    /**
     * jugadorPuedeColocar:
     * llama a jugador para ver si tiene piezas y ver si puede colocar
     * @param jugador
     * @return jugador.puedeJugar()
     */
    
    public boolean jugadorPuedeColocar(int jugador) {
    	//llama a jugador para ver si tiene piezas y ver si puede colocar
    	return jugadores.get(jugador).puedeJugar();
    }
    
    public boolean anadirFicha(int f, int y, int x) {
    	System.out.println("Entra en añadirficha");
    	Ficha ficha;
    	ficha = jugadores.get(currentPlayer).getFicha(f);
    	ficha.moverFicha(x, y);
    	Integer[] posicion = {0,0};
    	boolean fichaAnadida = false;
    	    	
    	if(fichaAnadida = cumpleReglas(ficha)) {   
    		
    		for(int i = 0; i < ficha.getNumCasillas(); i++){
    			
    			posicion[0] = ficha.getFichaX(i);
    			posicion[1] = ficha.getFichaY(i);
    			mapaCasillas.put(Arrays.toString(posicion), Integer.valueOf(currentPlayer));
    			for(GameObserver o : go) {
    				o.onFichaAnadida(ficha.getFichaX(i), ficha.getFichaY(i), f, jugadores.get(currentPlayer));
    				
    			   				
    			}
    		}
    		//Llamar a jugador para quitarle la ficha que acaba de colocar
    		if ((jugadores.get(currentPlayer).getNumFichas() == 1) && (ficha.getNumCasillas() == 1)) {
    			jugadores.get(currentPlayer).puntUltimoCuadrado();
    		}
			jugadores.get(currentPlayer).borrarPieza(f);
			

    	} 
    	else {
    		throw new GameException("Posición no válida.\n");
    	}
			
    	return fichaAnadida;
    }
    
    public boolean cumpleReglas(Ficha ficha) {
    	Integer[] pos = {0,0};
    	boolean casillaValida = false;
    	//checkea si vacia y contiguas
    	
    	for(int i = 0; i < ficha.getNumCasillas(); i++) {    		
    		
    		pos[0] = Integer.valueOf(ficha.getFichaX(i));
    		pos[1] = Integer.valueOf(ficha.getFichaY(i));
    		
    		if(pos[0] < 0 || pos[0] > DIM_BOARD-1 || pos[1] < 0 || pos[1] > DIM_BOARD-1) {    			    			
    			
    			return false;
    		}
    		
    		if(mapaCasillas.containsKey(Arrays.toString(pos))) { //Si estÃ¡ ocupada    			    			
    			
    			return false;
    		}
    		
    		if(checkDiagonal(pos, currentPlayer)) { //Si no tiene casillas del equipo en diagonal    			
    			casillaValida = true;    			
    		}
    		
    		if(!checkContiguaNoDiagonal(pos, currentPlayer)) { //Si tiene casillas del equipo contiguas no diagonales    		
    			
    			return false;
    		}
    		
    		
    	}
    	
		return casillaValida;
    }
    
    private boolean checkDiagonal(Integer[]pos, int equipo) {
    	boolean casillaValida = false;

    	
    	//	|1| |2|
    	//	| |P| |
    	//	|3| |4|
    	
    	Integer[] auxPos = {0,0};auxPos[0] = pos[0];auxPos[1] = pos[1];
    	auxPos[0] += 1; auxPos[1] += 1;
    	
    	if(mapaCasillas.get(Arrays.toString(auxPos)) == Integer.valueOf(equipo)) { //1
    		casillaValida = true;    		
    	}//else {System.out.println("\n1: "+ equipo+mapaCasillas.get(Arrays.toString(auxPos)));}
    	
    	auxPos[0] = pos[0];auxPos[1] = pos[1];
    	auxPos[0] += 1; auxPos[1] -= 1;    	
    	if(mapaCasillas.get(Arrays.toString(auxPos)) == Integer.valueOf(equipo)) { //2
    		casillaValida = true;    		
    	}
    	
    	auxPos[0] = pos[0];auxPos[1] = pos[1];
    	auxPos[0] -= 1; auxPos[1] += 1;
    	if(mapaCasillas.get(Arrays.toString(auxPos)) == Integer.valueOf(equipo)) { //3
    		casillaValida = true;    		
    	}
    	
    	auxPos[0] = pos[0];auxPos[1] = pos[1];
    	auxPos[0] -= 1; auxPos[1] -= 1; 	
    	if(mapaCasillas.get(Arrays.toString(auxPos)) == Integer.valueOf(equipo)) { //4
    		casillaValida = true;    		
    	}
    	return casillaValida;
    }

    private boolean checkContiguaNoDiagonal(Integer[]pos, int equipo) {
    	boolean casillaValida = true;
   	
    	//	| |1| |
    	//	|4|P|2|
    	//	| |3| |
    	
    	Integer[] auxPos = {0,0};auxPos[0] = pos[0];auxPos[1] = pos[1];
    	auxPos[1] += 1;
    	if(mapaCasillas.get(Arrays.toString(auxPos)) == Integer.valueOf(equipo)) { //1
    		casillaValida = false;
    	}
    	
    	auxPos[0] = pos[0];auxPos[1] = pos[1];
    	auxPos[0] += 1;
    	if(mapaCasillas.get(Arrays.toString(auxPos)) == Integer.valueOf(equipo)) { //2
    		casillaValida = false;
    	}
    	
    	auxPos[0] = pos[0];auxPos[1] = pos[1];
    	auxPos[1] -= 1;
    	if(mapaCasillas.get(Arrays.toString(auxPos)) == Integer.valueOf(equipo)) { //3
    		casillaValida = false;
    	}
    	
    	auxPos[0] = pos[0];auxPos[1] = pos[1];
    	auxPos[0] -= 1; 	
    	if(mapaCasillas.get(Arrays.toString(auxPos)) == Integer.valueOf(equipo)) { //4
    		casillaValida = false;
    	}
    	return casillaValida;
    	
    }
    
    public void obtenerPuntuacion(int jugador) {
    	//Llama a player getPuntuacion
    	int punt = jugadores.get(jugador).getPuntuacion();
    	System.out.println(punt);
    }

    public boolean getJuegoTerminado() {
    	//bucle lista jugadores puedeColocar() 	
    	for(int i = 0; i < jugadores.size(); i++) {// si el bucle termina y ninguno puede colocar el juego ha terminado
    		if(!jugadores.get(i).puedeJugar()) {
    			juegoTerminado = true;
    		}
    	}
    	return juegoTerminado;
    }

	public String positionToString(int x, int y) {
		
		Integer[] pos = {x,y};
		return ""; //arreglar
		//return (mapaCasillas.containsKey(Arrays.toString(pos))) ? mapaCasillas.get(Arrays.toString(pos)) : " ";
		
	}

	public int getNumJugadores() {
		return jugadores.size();
	}
	
	public boolean getPrimeraRonda() {
		return primeraRonda;
	}
	
	public void exit() {
		juegoTerminado = true;
	}
		
	public void reset() {
		//TODO Falta resetear las fichas de los jugadores
		jugadores.clear();
		currentPlayer=0;
		mapaCasillas.clear();
		
	}
	public int remaining() {
		return jugadores.get(currentPlayer).getNumFichas();
	}
	
	public void rotate(int numFicha, int rotacion) {
		jugadores.get(currentPlayer).getFicha(numFicha).rotar(rotacion);
	}
	
	public int getdificultad() {
		return this.dificultad;
	}
	
	public int getTurno() {
		return this.turno;
	}
	
	public int dim (int i) {
		return jugadores.get(currentPlayer).getNumCasillas(i);
	}

	public void addObserver(GameObserver o) {
		go.add(o);
	}
	
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void setCurrentPlayer(int p) {
		this.currentPlayer = p;
	}
	
	public int getCurrentFicha() {
		return currentFicha;
	}
	
	public void setCurrentFicha(int f) {
		this.currentFicha = f;
	}

}
