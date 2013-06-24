package Screens;

import GameUtils.*;
import GameObjects.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.acumenvn.libsudoku.MyMainGame;;


public class GamePlayScreen implements Screen {

	enum GameMenuIndex {
		Solve, // 0
		Check, // 1
		Reset, // 2
		Exit, // 3
	};
	
	MyMainGame mGame;
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
	TextWrapper txtLevel;
	TextWrapper txtTime;
	TextWrapper selectedTW;
	CTimer time;
	int menuIndex;
	Boolean mFlagWrongMsg;
	int mouseX;
	int mouseY;
	SpriteBatch mSpriteBatch;
	int[][] mLockMatrixNumber = new int[9][9];
	int[][] mResultMatrix = new int[9][9];
	private CSudoku mSolveSudoku;
	TextWrapper[][] arrText = new TextWrapper[9][9];
	int count = 0;
	Boolean mIsPlayMode;
	int selectedIndexX;
	int selectedIndexY;
	int countToShowWrongMessage;
	Boolean menuSelected;
	Vector2 positionForBorder;
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
			Gdx.app.log("Mouse X: " + mouseX, "Mouse Y: " + mouseY);
			// Solve
			menuSelected = false;
			if(mouseX > 700 && mouseX < 760) {
				if(mouseY > 140 && mouseY < 200) {
					menuIndex = 0;
					menuSelected = true;
				}
			}
			// Checker
			if(mouseX > 590 && mouseX < 660) {
				if(mouseY > 150 && mouseY < 190) {
					menuIndex = 1;
					menuSelected = true;
				}
			}
			// Reset
			if(mouseX > 510 && mouseX < 750) {
				if(mouseY > 410 && mouseY < 460) {
					menuIndex = 2;
					menuSelected = true;
				}
			}
			// Exit
			if(mouseX > 600 && mouseX < 750) {
				if(mouseY > 480 && mouseY < 550) {
					menuIndex = 3;
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
					SoundManager.getInstance().playSound(SoundManager.getInstance().mButtonPush);
					mSolveSudoku.sudoku[selectedIndexX][8 - selectedIndexY] = no;
				}
			}
			return false;
		}
	};
	
	public GamePlayScreen(MyMainGame game) {
		// TODO Auto-generated constructor stub
		this.mGame = game;
		menuSelected = false;
		mSolveSudoku = new CSudoku();
		for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                mLockMatrixNumber[i][j] = mSolveSudoku.sudoku[i][j];
            }
        }
		countToShowWrongMessage = 0;
		mResultMatrix = mSolveSudoku.resultMatrix;
		mSpriteBatch = new SpriteBatch();
		GameUtils.setInputProcessor(mInput);
		mFlagWrongMsg = false;
		String levelStr = "";
		positionForBorder = new Vector2(0, 0);
		CTimer.getInstance().resetTimer();
		switch (GameUtils.getInstance().levelIndex) {
		case 0:
			levelStr = "Easy";
			break;
		case 1:
			levelStr = "Normal";
			break;
		case 2:
			levelStr = "Hard";
			break;
		default:
			break;
		}
		GameUtils.getInstance().systemFont.scale(-0.2f);
		initTextWrapper();
		txtLevel = new TextWrapper(levelStr, new Vector2(670, 365));
		txtTime = new TextWrapper("00:00:00", new Vector2(675, 287));
		time =  CTimer.getInstance();
		initTexture();
		Gdx.graphics.setContinuousRendering(true);
		Gdx.graphics.requestRendering();
	}
	
	private void initTextWrapper() {
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
		
		textureBtn_quit = textureBtn_quitOn;
		textureBtn_reset = textureBtn_resetOn;
		textureBorderSelect = TextureManager.getInstance().textureBorderSelect;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		GameUtils.clearScreen();
		mSpriteBatch.begin();
		mSpriteBatch.draw(textureBackgroundInGame, 0, 0);
		mSpriteBatch.draw(textureBtn_reset, 610, 150);
		mSpriteBatch.draw(textureBtn_quit, 610, 70);
		mSpriteBatch.draw(textureBtn_solve, 710, 410);
		mIsPlayMode = true;
		if(mFlagWrongMsg == true){
			mSpriteBatch.draw(textureWrongMessage, 590, 360);
			countToShowWrongMessage++;
			if(countToShowWrongMessage > 500) {
				mFlagWrongMsg = false;
				countToShowWrongMessage = 0;
			}
		} else {
			mSpriteBatch.draw(textureBtn_Check, 590, 400);
		}
		drawMatrix();
		txtLevel.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		updateTime(delta);
		mSpriteBatch.end();
		
		handleMouseEvent();
	}
	
	private void updateTime(float dt) {
		time.updateTime(dt);
		txtTime.content = time.toString();
		txtTime.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.WHITE);
	
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		SoundManager.getInstance().playMusic(SoundManager.getInstance().mSoundStartGame);
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
		mSpriteBatch.dispose();
	}
	
	private void handleMouseEvent() {
		if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
			if(menuSelected) {
				handleButtonsEvent();
			} else {
				if(mouseX > 30 && mouseX < 570 ) {
					if(mouseY > 30 && mouseY < 570) {
						int x = (mouseX - 30) / 60;
						int y = (mouseY - 30) / 60;
						if(mLockMatrixNumber[x][8-y] == 0) {
							positionForBorder.x = x*60 + 35;
							positionForBorder.y  = (8-y)*60 + 30;
						} else {
							positionForBorder.x = 0;
							positionForBorder.y = 0;
						}
					}		
				}
				if(positionForBorder.x != 0 || positionForBorder.y != 0) {
					mSpriteBatch.begin();
					mSpriteBatch.draw(textureBorderSelect, positionForBorder.x, positionForBorder.y);
					mSpriteBatch.end();
				}
			}
		} else {
			textureBtn_quit = textureBtn_quitOn;
			textureBtn_reset = textureBtn_resetOn;
		}
	}
	
	private void handleButtonsEvent() {
		switch (menuIndex) {
		case 0:
			// Solve
			mSolveSudoku.solve();
			mSolveSudoku.showSolve();
			break;
		case 1:
			// Checker
			checkResult();
			break;
		case 2:
			// Reset
			resetEvent();
			textureBtn_reset = textureBtn_resetOff;
			break;
		case 3:
			// Exit
			textureBtn_quit = textureBtn_quitOff;
			this.mGame.setScreen(new MainMenuScreen(mGame));
			break;
		default:
			break;
		}
	}
	
	private void checkResult() {
		if(mSolveSudoku.flagCheckResult() == true) {
			String timeStr = CTimer.getInstance().toString();
			GameOverScreen sc = new GameOverScreen(mGame);
			sc.timeStr = timeStr;
			this.mGame.setScreen(sc);
		} else {
			mFlagWrongMsg = true;
		}
	}
	
	private void resetEvent() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (mLockMatrixNumber[i][j] == 0)
                {
					mSolveSudoku.sudoku[i][j] = 0;
                }
			}
		}
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
                    tempTW.Draw(mSpriteBatch, GameUtils.getInstance().lockMatrixFont, Color.BLUE);
                }
                if (mLockMatrixNumber[i][j] == 0 && mSolveSudoku.sudoku[i][j] != 0)
                {
                	int tempNumber = mSolveSudoku.sudoku[i][j];
                	String tempStr = Integer.toString(tempNumber);
                    float x = (i * 60) + 60;
                    float y = (j * 60) + 80;
                    TextWrapper tempTW = arrText[i][j];
                    tempTW.content = tempStr;
                    tempTW.position = new Vector2(x,y);
                    tempTW.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.WHITE);
                }
            }
        }
	}
}
