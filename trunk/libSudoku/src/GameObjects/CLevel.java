package GameObjects;

import java.util.Random;

public class CLevel {
	// Fields
	public int[][] emptyMatrix = new int[9][9];
	
	public int level;
	
	private Random rand = new Random();
	
	public CLevel() {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				emptyMatrix[i][j] = 1;
			}
		}
		
		fillEmpty();
	}

	private void fillEmpty() {
		// TODO Auto-generated method stub
		int qCells = 0;
		switch (level) {
		case 0:
			// Easy
            // 40 -> 45 empty cells
			qCells = rand.nextInt()*11 + 20;
			break;
		case 1:
			qCells = rand.nextInt()*16 + 30;
			break;
		case 2:
			qCells = rand.nextInt()*16 + 45;
			break;
		default:
			break;
		}
		
		if(qCells != 0) {
			makingEmptyCells(qCells);
		}
		
	}

	private void makingEmptyCells(int qCells) {
		// TODO Auto-generated method stub
		for (int i = 0; i < qCells; i++) {
			// check if cells already = 0
			Boolean isDuplicate = false;
			do {
				int x = rand.nextInt()*9 + 1;
				int y = rand.nextInt()*9 + 1;
				if(emptyMatrix[x][y] == 0) {
					isDuplicate = true;
				} else {
					emptyMatrix[x][y] = 0;
					isDuplicate = false;
				}
			} while (isDuplicate == true);
		}
	}
	
	
}
