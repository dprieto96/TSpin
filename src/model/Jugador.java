package model;
import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private int puntuacion = 0;
    private boolean ultimoCuadrado = false;
    private List<Ficha> arrayFichas = new ArrayList<Ficha> ();
    private int id;

    public Jugador(int id) {    	
    	this.id = id;
    	cargarFichas();
    }
    
    public void cargarFichas() {
    	int [][][] fichas = Ficha.getAllFormas();	
		for (int i = 0; i < fichas.length; i++) {
			arrayFichas.add(new Ficha(fichas[i],id));
	}
    }
    public Ficha getFicha(int i) {
    	return arrayFichas.get(i);
    }
    
    public int getPuntuacion() {
    	// Buscamos si faltan casillas o no. 
    	// Si faltan restamos, sino sumamos puntos
    	
    	if (getNumFichas() > 0) {
    		for (int i = 0; i < getNumFichas(); i++) {
    			for (int j = 0; j < getNumCasillas(i); j++) {
    				puntuacion--;
    			}
    		}
    	}
    	else {
    		puntuacion += 15;
    		
    		if (ultimoCuadrado) {
    			puntuacion += 5;
    		}
    	}
    	
    	return Math.abs(puntuacion);
    }
    
    public void puntUltimoCuadrado() {
    	ultimoCuadrado = true;
    }

    public void borrarPieza(int pieza) {
    	arrayFichas.remove(pieza);
    }

    public boolean puedeJugar() { 
    	if (arrayFichas.size() > 0) return true;
    	return false;
    }
    
    public int getNumFichas() {
    	return arrayFichas.size();
    }
    
    public int getNumCasillas(int ficha) {
    	return arrayFichas.get(ficha).getNumCasillas();
    }

	public int getId() {
		return id;
	}

	public boolean computerAction(Game game) {
		return false;
	}

    
    
    
}
