package GameObjects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

public class CSudoku {
	
	Random rand = new Random();
	
	// Public Variables
	public static Vector3[][] mV3;
	public int[][] sudoku = new int[9][9];
	public int[][] resultMatrix = new int[9][9];
	// Private variables
	private int[][] mEmptryMatrix = new int[9][9];
	CLevel mLevel;
	private int[][] mSubquare = new int[][] {
			{0,0,0,1,1,1,2,2,2},
			{0,0,0,1,1,1,2,2,2},
			{0,0,0,1,1,1,2,2,2},
			{3,3,3,4,4,4,5,5,5},
			{3,3,3,4,4,4,5,5,5},
			{3,3,3,4,4,4,5,5,5},
			{6,6,6,7,7,7,8,8,8},
			{6,6,6,7,7,7,8,8,8},
			{6,6,6,7,7,7,8,8,8}
	};
	
	public CSudoku() {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = 0;
			}
		}
		mV3 = new Vector3[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				mV3[i][j] = new Vector3();
			}
		}
		createSubRegions();
		mLevel = new CLevel();
		copyToV3();
		
		if(solve()){
			showSolve();
		}
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				resultMatrix[i][j] = sudoku[i][j];
			}
		}
		merge();
	}
	
	public  CSudoku(int type) {
		mV3 = new Vector3[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				mV3[i][j] = new Vector3();
			}
		}
		copyToV3();
	}

	private void merge() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				if(mLevel.emptyMatrix[i][j] == 0) {
//					Gdx.app.log("i " + i, "j " + j);
					sudoku[i][j] = 0;
				}
			}
		}
	}

	public void showSolve() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				sudoku[i][j] = (int)mV3[i][j].z;
			}
		}
	}

	public boolean solve() {
		// TODO Auto-generated method stub
		int a = 0;
		int b = 0;
		int allElements = 10;
		int[] tempArray = null;
		if (!flagCheckMap())
		{
			return false;
		}
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				//browse empty cells or discordant cells
				if (mV3[i][j].z == 0)             
				{
					int[] M = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
					// Remove M's cells that match with m_v3's cells
					for (int k = 0; k < 9; k++)
					{
						
						M[(int)mV3[i][k].z] = 0;
					}
					for (int l = 0; l < 9; l++)
					{
						M[(int)mV3[l][j].z] = 0;
					}
					// Delete M's cell that matchs with m_v3's cell's position
					checkField(M, i, j);
					// count unused elements
					int unusedElements = 0;
					for (int h = 1; h < 10; h++)
					{
						if (M[h] != 0)
						{
							unusedElements++;
						}
					}
					// check other cases if there is one of them suit
					if (unusedElements < allElements)
					{
						allElements = unusedElements;
						tempArray = M;
						a = i;
						b = j;
					}
				}
			}
		}
		// if there are no numbers in m_v3  = 0
		if (allElements == 10)
		{
			return true;
		}
		// Do not have elements to use
		if (allElements == 0)
		{
			return false;
		}
		// Try other solutions to find the most suitable one
		for (int n = 1; n < 10; n++)
		{
			if (tempArray[n] != 0)
			{
				mV3[a][b].z = (float)tempArray[n];
				if (solve())
				{
					return true;
				}
			}
		}
		// Remove position if not suitable
		mV3[a][b].z = 0;
		return false;
	}

	public boolean flagCheckMap() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				if (mV3[i][j].z != 0)
				{
					for (int l = 0; l < 9; l++)
					{
						if ((l != j) && (mV3[i][l].z == mV3[i][j].z))
						{
							//  Wrong row
							return false;
						}
						if ((l != i) && (mV3[l][j].z == mV3[i][j].z))
						{
							// Wrong column
							return false;
						}
					}
					// defined-region with index
					int squareIndex = mSubquare[i][j];   
					for (int x = 0; x < 9; x++)
					{
						for (int y = 0; y < 9; y++)
						{
							if (mSubquare[x][y] == squareIndex)
							{
								if (((x != i) || (y != j)) && (mV3[x][y].z == mV3[i][j].z))
								{
									// Wrong region
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	public Boolean flagCheckResult()
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				if (sudoku[i][j]==0)
				{
					Gdx.app.log("1", "");
					return false;
				}
			}
		}
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				if (sudoku[i][j] != 0)
				{
					for (int l = 0; l < 9; l++)
					{
						if ((l != j) && (sudoku[i][l] == sudoku[i][j]))
						{
							// Wrong row
							Gdx.app.log("2", "");
							return false;
						}
						if ((l != i) && (sudoku[l][j] == sudoku[i][j]))
						{
							// Wrong column
							Gdx.app.log("3", "");
							return false;
						}
					}
					int squareIndex = mSubquare[i][j];
					for (int x = 0; x < 9; x++)
					{
						for (int y = 0; y < 9; y++)
						{
							if (mSubquare[x][y] == squareIndex)
							{
								if (((x != i) || (y != j)) 
									&& (sudoku[x][y] == sudoku[i][j]))
								{
									// Wrong region
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}

	public void checkField(int[] m, int i, int j) {
		// TODO Auto-generated method stub
		int squareIndex = mSubquare[i][j];
		for (int x = 0; x < 9; x++)
		{
			for (int y = 0; y < 9; y++)
			{
				if (mSubquare[x][y] == squareIndex)
				{
					m[(int)mV3[x][y].z] = 0;
				}
			}
		}
	}

	public void copyToV3() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				mV3[i][j].z = sudoku[i][j];
			}
		}
	}
	
	private int getCase(int n)
	{
		int no = 0;
		switch(n)
		{
			case 0:
			case 1:
			case 2:
				no = 0;
				break;
			case 3:
			case 4:
			case 5:
				no = 3;
				break;
			case 6:
			case 7:
			case 8:
				no = 6;
				break;
		}
		return no;
	}
	
	private void createSubRegions() {
		int x = 0;
		int y = 0;
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				if (i == 0 && j==0)
				{
					// Get case for x
					x = getCase(i);
					// Get case for y
					y = getCase(j);
					// Generate a random number with random position in this region
					generateRegion(x, x + 3, y, y + 3);
					i = 2;
					j = 2;
				}
				if (i == 2 && j == 2)
				{
					x = getCase(i);
					y = getCase(j);
					generateRegion(x, x + 3, y, y + 3);
					i = 5;
					j = 5;
				}
				if (i == 5 && j == 5)
				{
					x = getCase(i);
					y = getCase(j);
					generateRegion(x, x + 3, y, y + 3);
				}
			}
		}
	}

	private void generateRegion(int xStart,int xEnd,int yStart,int yEnd) {
		// TODO Auto-generated method stub
//		Gdx.app.log("xStart" + xStart + " xEnd: " + xEnd , "yStart: " + yStart + " yEnd: " + yEnd);
		int x = rand.nextInt(xEnd - xStart + 1) + xStart;
		int y = rand.nextInt(yEnd - yStart + 1) + yStart;
		int number = rand.nextInt(9) + 1;
//		Gdx.app.log("X" + x, "Y" + y);
		sudoku[x][y] = number;
	}
}
