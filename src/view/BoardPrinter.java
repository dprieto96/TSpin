package view;

import model.Game;

public class BoardPrinter extends GamePrinter {
	
	private int numRows;
	private int numCols;
	private String[][] board;
	
	private final String space = " ";
	// TODO final of cellSize, marginSize, vDelimiter and hDelimiter
	//
	
	public BoardPrinter() {
		this.numRows = Game.DIM_BOARD;
		this.numCols = Game.DIM_BOARD;
	}
	
	// Creates the following board
	
	private void encodeGame(Game game) {
		
		board = new String[numRows][numCols];

		for(int i = 0; i < numRows; i++)			// X
		{
			for(int j = 0; j < numCols; j++)		// Y
			{
				board[i][j] = game.positionToString(i, j);		// TODO Check logic order of i and j
			}
		}
	}
	
	@Override
	public String toString(Game game) {
		
		encodeGame(game);
		
		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (numCols * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		
		str.append(lineDelimiter);
		
		for(int x = 0; x < numRows; x++)
		{
			str.append(margin).append(vDelimiter);

			for (int y = 0; y < numCols; y++)
			{
				str.append(MyStringUtils.center(board[x][y], cellSize)).append(vDelimiter); //center: centrar el string de i, j poniendolo en la mitad de la celda
			}

			str.append(lineDelimiter);
		}

		return str.toString();
	}

	
}
