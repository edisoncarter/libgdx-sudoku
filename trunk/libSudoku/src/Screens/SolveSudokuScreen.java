package Screens;

import GameObjects.CSudoku;
import GameUtils.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.acumenvn.libsudoku.MyMainGame;

public class SolveSudokuScreen implements Screen {

	private MyMainGame mGame;
	private SpriteBatch mBatch;
	// Texture
	private Texture textureBackgroundInGame;
	private Texture textureBtn_Check;
	private Texture textureBtn_Wrong;
	
	private Texture textureBtn_quitOn;
	private Texture textureBtn_quitOff;
	private Texture textureBtn_quit;
	
	private Texture textureBtn_resetOn;
	private Texture textureBtn_resetOff;
	private Texture textureBtn_reset;
	
	private Texture textureBtn_solve;
	private Texture textureWrongMessage;
	private Texture textureBorderSelect;
	private Texture textureWrongInput;
	Sound mButtonPush;
	int menuIndex;
	CSudoku mSolveSudoku;
	TextWrapper[][] arrText = new TextWrapper[9][9];
	int[][] mFirstSudoku = new int[][] {
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}	
	};
	int[][] m_sudoku =
            new int[][]
			{
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0}
			};
	int[][] mLockMatrixNumber = new int[9][9];
	Boolean m_flagChangeNumber;
    Boolean m_flagSound = true; 
    Boolean m_flagSolve = false;
	Boolean m_flagWrongInput = false;
    int selectedIndexX;
    int selectedIndexY;
    int mouseX;
    int mouseY;
    Boolean menuSelected;
    int countShowWrongInputDialog;
	InputProcessor mInput = new InputProcessor() {
		
		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			mouseX = screenX;
			mouseY = screenY;
			// Solve
			menuSelected = false;
			if(mouseX > 700 && mouseX < 760) {
				if(mouseY > 140 && mouseY < 200) {
					menuIndex = 0;
					menuSelected = true;
				}
			}
			// Reset
			if(mouseX > 510 && mouseX < 750) {
				if(mouseY > 410 && mouseY < 460) {
					menuIndex = 1;
					menuSelected = true;
				}
			}
			// Exit
			if(mouseX > 600 && mouseX < 750) {
				if(mouseY > 480 && mouseY < 550) {
					menuIndex = 2;
					menuSelected = true;
				}
			}
			
			// Checker
			if(screenX > 30 && screenX < 570 &&
				screenY > 30 && screenY < 570) {
				int x = (screenX - 30) / 60;
				int y = (screenY - 30) / 60;
				selectedIndexX = x;
				selectedIndexY = y;
			}
			return false;
		}
		
		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean keyDown(int keycode) {
			// TODO Auto-generated method stub
			int no = 0;
			String noStr = "";
			Boolean isNumberPress = true;
			switch (keycode) {
			case Keys.NUM_1:
				no = 1;
				noStr = "1";
				break;
			case Keys.NUM_2:
				no = 2;
				noStr = "2";
				break;
			case Keys.NUM_3:
				no = 3;
				noStr = "3";
				break;
			case Keys.NUM_4:
				no = 4;
				noStr = "4";
				break;
			case Keys.NUM_5:
				no = 5;
				noStr = "5";
				break;
			case Keys.NUM_6:
				no = 6;
				noStr = "6";
				break;
			case Keys.NUM_7:
				no = 7;
				noStr =  "7";
				break;
			case Keys.NUM_8:
				no = 8;
				noStr = "8";
				break;
			case Keys.NUM_9:
				no = 9;
				noStr = "9";
				break;
			default:
				isNumberPress = false;
				break;
			}
			if(isNumberPress == true) {
				if(no > 0) {
					SoundManager.getInstance().playSound(mButtonPush);
					mSolveSudoku.sudoku[selectedIndexX][8 - selectedIndexY] = no;
					mFirstSudoku[selectedIndexX][8 - selectedIndexY] = no;
				}
			}
			return false;
		}
	};
	
	public SolveSudokuScreen(MyMainGame game) {
		// TODO Auto-generated constructor stub
		this.mGame = game;
		mBatch = new SpriteBatch();
		menuSelected = false;
		GameUtils.setInputProcessor(mInput);
		countShowWrongInputDialog = 0;
		mSolveSudoku = new CSudoku(1);
		initTexture();
		initTextWrappers();
		mButtonPush = SoundManager.getInstance().mButtonPush;
		Gdx.graphics.setContinuousRendering(true);
		Gdx.graphics.requestRendering();
	}
	
	private void initTextWrappers() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				arrText[i][j] = new TextWrapper();
			}
		}
	}
	
	private void initTexture() {
		textureBackgroundInGame = TextureManager.getInstance().textureBackgroundInGame;
		textureBtn_Check = TextureManager.getInstance().textureBtn_Check;
		textureBtn_Wrong = TextureManager.getInstance().textureBtn_Wrong;
		textureBtn_quitOn = TextureManager.getInstance().textureBtn_quitOn;
		textureBtn_quitOff = TextureManager.getInstance().textureBtn_quitOff;
		textureBtn_resetOn = TextureManager.getInstance().textureBtn_resetOn;
		textureBtn_resetOff = TextureManager.getInstance().textureBtn_resetOff;
		textureBtn_solve = TextureManager.getInstance().textureBtn_checker;
		textureWrongMessage = TextureManager.getInstance().textureWrongMessage;
		textureWrongInput = TextureManager.getInstance().textureWrongInput;
		textureBtn_quit = textureBtn_quitOn;
		textureBtn_reset = textureBtn_resetOn;
		textureBorderSelect = TextureManager.getInstance().textureBorderSelect;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		GameUtils.clearScreen();
		
		mBatch.begin();
		mBatch.draw(textureBackgroundInGame, 0, 0);
		mBatch.draw(textureBtn_reset, 610, 150);
		mBatch.draw(textureBtn_quit, 610, 70);
		mBatch.draw(textureBtn_solve, 710, 410);
		if(m_flagWrongInput == true) {
			countShowWrongInputDialog++;
			mBatch.draw(textureWrongInput, 590, 360);
			if(countShowWrongInputDialog > 500) {
				m_flagWrongInput = false;
			}
		}
		drawMatrix();
		mBatch.end();
		
		handleMouseEvent();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		mBatch.dispose();
	}
	
	private void drawMatrix() {
		for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                // Draw lock matrix
                if (mLockMatrixNumber[i][j] != 0)
                {
                	int tempNumber = mLockMatrixNumber[i][j];
                	String tempStr = Integer.toString(tempNumber);
                    float x = (i * 60) + 60;
                    float y = (j * 60) + 80;
                    TextWrapper tempTW = arrText[i][j];
                    tempTW.content = tempStr;
                    tempTW.position = new Vector2(x,y);
                    tempTW.Draw(mBatch, GameUtils.getInstance().lockMatrixFont, Color.BLUE);
                }
                if (mLockMatrixNumber[i][j] == 0 &&  mSolveSudoku.sudoku[i][j] != 0)
                {
                	int tempNumber = mSolveSudoku.sudoku[i][j];
                	String tempStr = Integer.toString(tempNumber);
                    float x = (i * 60) + 60;
                    float y = (j * 60) + 80;
                    TextWrapper tempTW = arrText[i][j];
                    tempTW.content = tempStr;
                    tempTW.position = new Vector2(x,y);
                    tempTW.Draw(mBatch, GameUtils.getInstance().systemFont, Color.WHITE);
                }
            }
        }
	}
	
	private void handleMouseEvent() {
		if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
			if(menuSelected) {
				switch (menuIndex) {
				case 0:
					// Solve
					solveSudoku();
					break;
				case 1:
					// Reset
					textureBtn_reset = textureBtn_resetOff;
					resetSudoku();
					break;
				case 2:
					// Exit
					textureBtn_quit = textureBtn_quitOff;
					this.mGame.setScreen(new MainMenuScreen(this.mGame));
					break;
				default:
					break;
				}
			}
		} else {
			textureBtn_reset = textureBtn_resetOn;
			textureBtn_quit = textureBtn_quitOn;
		}
	}

	private void solveSudoku() {
		//copy cells from m_sudoku to m_lockMatrix
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++) {
//				if(mSolveSudoku.sudoku[i][j] != 0) {
//					mLockMatrixNumber[i][j] = mSolveSudoku.sudoku[i][j];
//				}
//			}
//		}
		mSolveSudoku.copyToV3();
		if(mSolveSudoku.solve() == true) {
			mSolveSudoku.showSolve();
			m_flagSolve = true;
		} else {
			// if player's answer is wrong, then draw wrong message dialog
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					mLockMatrixNumber[i][j] = 0;
				}
			}
			m_flagWrongInput = true;
			countShowWrongInputDialog = 0;
		}
	}
	
	private void resetSudoku() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				mSolveSudoku.sudoku[i][j] = 0;
			}
		}
	}
	
}
