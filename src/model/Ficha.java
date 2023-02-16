package model;
import java.util.ArrayList;
import java.util.List;

public class Ficha {

	private int equipo;
	private int[][] forma;
	private List<Casilla> listaCasillas = new ArrayList<Casilla>();

//	public Ficha(int[][] forma, List<Casilla> arrayCasillas, int equipo) {
//		this.forma = forma;
//		this.equipo = equipo;
//		this.listaCasillas = arrayCasillas;
//	}

	public Ficha(int[][] forma, int equipo) {
		this.forma = forma;
		this.equipo = equipo;
		for(int i = 0; i < forma.length; i++) {
			listaCasillas.add(new Casilla(0, 0));
		}
	}

//	public void rotar(int rotacion) {
//		switch (rotacion) {
//		case 270:
//			for (int i = 0; i < listaCasillas.size() - 1; i++) {
//				if (forma[i][0] == 0) {
//					forma[i][0] = -1;
//				} else {
//					forma[i][0] = 0;
//				}
//				if (forma[i][1] == 0) {
//					forma[i][1] = -1;
//				} else {
//					forma[i][1] = 0;
//				}
//				if (forma[i][0] == 0 && forma[i][1] == 0) {
//					forma[i][0] = 1;
//					forma[i][1] = -1;
//				}
//			}
//			break;
//		case 180:
//			for (int i = 0; i < listaCasillas.size() - 1; i++) {
//				forma[i][0] = -forma[i][0];
//				forma[i][1] = -forma[i][1];
//			}
//			break;
//		case 90:
//			for (int i = 0; i < listaCasillas.size() - 1; i++) {
//				if (forma[i][0] == 0) {
//					forma[i][0] = 1;
//				} else {
//					forma[i][0] = 0;
//				}
//				if (forma[i][1] == 0) {
//					forma[i][1] = 1;
//				} else {
//					forma[i][1] = 0;
//				}
//			}
//			break;
//		}
//
//		setForma();
//	}
	
	public void rotar(int rotacion) {
		switch (rotacion) {
		case 90:
			for (int i = 0; i < listaCasillas.size(); i++) {
					forma[i][0] = -forma[i][0];
					System.out.println(forma[i][0] +" | " + -forma[i][0]);
			}
			break;
		case 180:
			for (int i = 0; i < listaCasillas.size(); i++) {
				forma[i][0] = -forma[i][0];
				forma[i][1] = -forma[i][1];
			}
			break;
		case 270:
			for (int i = 0; i < listaCasillas.size(); i++) {
				forma[i][1] = -forma[i][1];
			}
			break;
		}
		
		setForma();
	}

	public void moverFicha(int x, int y) {		
		listaCasillas.get(0).setX(x);
		listaCasillas.get(0).setY(y);
		
		setForma();
	}

	public void setForma() {		
		for (int i = 1; i < listaCasillas.size(); i++) {
			listaCasillas.get(i).setX(listaCasillas.get(i - 1).getX() - forma[i - 1][1]);
			listaCasillas.get(i).setY(listaCasillas.get(i - 1).getY() + forma[i - 1][0]);
		}
	}

	public int[][] getForma() {
		return forma;
	}

	public int getEquipo() {
		return equipo;
	}

	public int getFichaX(int i) {

		return listaCasillas.get(i).getX();
	}

	public int getFichaY(int i) {

		return listaCasillas.get(i).getY();
	}

	public int getNumCasillas() {
		return listaCasillas.size();
	}
	
	public static int [][][] getAllFormas(){
		int[][][] fichas = new int[21][][];
		int i=0;
		
		fichas[i++] = new int [][] {
			{0,0}
		};
		
		fichas[i++] = new int [][] {
			{ 0, 1 }
		};
		
		fichas[i++] = new int [][] {
			{ 0, 1 }, 
			{ 0, 1 }
		};
		
		fichas[i++] = new int [][] {
			{ 1, 0 }, 
			{ -1, 1 }
		};
		
		fichas[i++] = new int [][] {
			 { 0, 1 }, 
			 { 0, 1 }, 
			 { 0, 1 } 
		};
		
		fichas[i++] = new int [][] {
			{ 1, 0 }, 
			{ 0, 1 }, 
			{ 0, 1 }
		};
		
		fichas[i++] = new int [][] {
			{ 0, 1 }, 
			{ 0, 1 }, 
			{ 1, -1 }
		};
		
		fichas[i++] = new int [][] {
			{ 0, 1 }, 
			{ 1, 0 }, 
			{ 0, -1 }
		};
		
		fichas[i++] = new int [][] {
			{ 1, 0 }, 
			{ 0, -1 }, 
			{ 1, 0 }
		};
		
		fichas[i++] = new int [][] {
			 { 0, 1 }, 
			 { 0, 1 }, 
			 { 0, 1 }, 
			 { 0, 1 }
		};
		
		fichas[i++] = new int [][] {
			{ 1, 0 }, 
			{ 0, 1 }, 
			{ 0, 1 }, 
			{ 0, 1 }
		};
		
		fichas[i++] = new int [][] {
			{ 0, 1 }, 
			{ 1, 0 }, 
			{ 0, 1 }, 
			{ 0, 1 }
		};
		
		fichas[i++] = new int [][] {
			{ 0, 1 }, 
			{ 1, 0 }, 
			{ 0, 1 }, 
			{ 0, -2 }
		};
		
		fichas[i++] = new int [][] {
			{ 1, 0 }, 
			{ 0, 1 }, 
			{ 0, 1 }, 
			{ -1, 0 }
		};
		
		fichas[i++] = new int [][] {
			{ 0, 1 }, 
			{ 0, 1 }, 
			{ 0, 1 }, 
			{ 1, -1 }
		};
		
		
		fichas[i++] = new int [][] {
			 { 1, 0 }, 
			 { 0, 1 }, 
			 { 0, 1 }, 
			 { 1, -2 } 
		};
		
		fichas[i++] = new int [][] {
			{ 0, 1 }, 
			{ 0, 1 }, 
			{ 1, -2 }, 
			{ 1, 0 }
		};
		
		fichas[i++] = new int [][] {
			{ +1, 0 }, 
			{ 0, -1 }, 
			{ -1, +2 }, 
			{ -1, 0 }
		};
		
		fichas[i++] = new int [][] {
			{ 0, -1 }, 
			{ 1, 0 }, 
			{ 1, 0 }, 
			{ 0, -1 }
		};
		
		fichas[i++] = new int [][] {
			 { 0, -1 }, 
			 { 1, 0 }, 
			 { 1, 0 }, 
			 { -1, -1 }
		};
		
		
		fichas[i++] = new int [][] {
			{ 0, 1 }, 
			{ -1, -1 }, 
			{ 1, -1 }, 
			{ 1, 1 }
		};
		
		return fichas;
	}

}
