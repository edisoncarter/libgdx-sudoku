package Screens;

import GameUtils.*;
import GameObjects.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.acumenvn.libsudoku.MyMainGame;;


public class GamePlayScreen implements Screen {

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
	
	private Texture textureBtn_settingOn;
	private Texture textureBtn_settingOff;
	private Texture textureBtn_setting;
	
	private Texture textureBtn_checker;
	private Texture textureBtn_solve;
	private Texture textureWrongMessage;
	TextWrapper txtLevel;
	TextWrapper txtTime;
	TextWrapper selectedTW;
	CTimer time;
	Boolean mFlagWrongMsg;
	SpriteBatch mSpriteBatch;
	int[][] mLockMatrixNumber = new int[9][9];
	private CSudoku mSolveSudoku;
	TextWrapper[][] arrText = new TextWrapper[9][9];
	
	Boolean mIsPlayMode;
	
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
		private Boolean mouseHover(int xPos, int yPos, int textureX, int textureY, Texture t) {
			if(xPos > textureX - 10 && xPos < textureX + 10 + t.getWidth() &&
			   yPos > textureY - 10 && yPos < textureY + 10 + t.getHeight()) {
				return true;
			}
			return false;
		}
		
		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			if(screenX > 30 && screenX < 570 &&
				screenY > 30 && screenY < 570) {
				int x = (screenX - 30) / 60;
				int y = (screenY - 30) / 60;
				
//				arrText[x][y].content = "1";
//				selectedTW = arrText[x-1][y-1];
//				selectedTW.content = "1";
				Gdx.app.log("X" + x, "Y" + y);
			}
			// Btn Quit
//			if(mouseHover(screenY, screenY, 610, 480, textureBtn_quit)) {
//				textureBtn_quit = textureBtn_quitOff;
//			} else {
//				textureBtn_quit = textureBtn_quitOn;
//			}
//			// Btn Reset	
//			if(mouseHover(screenY, screenY, 610, 400, textureBtn_reset)) {
//				textureBtn_reset = textureBtn_resetOff;
//			} else {
//				textureBtn_reset = textureBtn_resetOn;
//			}
//			// Btn Setting
//			if(mouseHover(screenY, screenY, 610, 400, textureBtn_setting)) {
//				textureBtn_setting = textureBtn_settingOff;
//			} else {
//				textureBtn_setting = textureBtn_settingOn;
//			}
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
				break;
			}
//			if(no > 0) {
//				selectedTW.content = noStr;
//			} else {
//				selectedTW.content = "";
//			}
			return false;
		}
	};
	
	public GamePlayScreen(MyMainGame game, Boolean isPlayMode) {
		// TODO Auto-generated constructor stub
		this.mGame = game;
		mSpriteBatch = new SpriteBatch();
		this.mIsPlayMode = isPlayMode;
		GameUtils.setInputProcessor(mInput);
		mFlagWrongMsg = false;
		String levelStr = "";
		switch (GameUtils.getInstance().levelIndex) {
		case 0:
			levelStr = "Hard";
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
		textureBtn_settingOn = TextureManager.getInstance().textureBtn_settingOn;
		textureBtn_settingOff = TextureManager.getInstance().textureBtn_settingOff;
		textureBtn_checker = TextureManager.getInstance().textureBtn_checker;
		textureBtn_solve = TextureManager.getInstance().textureBtn_checker;
		textureWrongMessage = TextureManager.getInstance().textureWrongMessage;
		
		textureBtn_quit = textureBtn_quitOn;
		textureBtn_reset = textureBtn_resetOn;
		textureBtn_setting = textureBtn_settingOn;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		GameUtils.clearScreen();
		mSpriteBatch.begin();
		mSpriteBatch.draw(textureBackgroundInGame, 0, 0);
		mSpriteBatch.draw(textureBtn_reset, 610, 150);
		mSpriteBatch.draw(textureBtn_setting, 610, 110);
		mSpriteBatch.draw(textureBtn_quit, 610, 70);
		mSpriteBatch.draw(textureBtn_solve, 710, 410);
		mIsPlayMode = true;
		if(mIsPlayMode == true) {
			if(mFlagWrongMsg == true){
				mSpriteBatch.draw(textureWrongMessage, 590, 360);
			} else {
				mSpriteBatch.draw(textureBtn_Check, 590, 400);
			}
		}
		drawMatrix();
		txtLevel.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		updateTime(delta);
		mSpriteBatch.end();
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
		
	}
	
	private void drawMatrix() {
		for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                // Draw lock matrix
            	int tempNumber = 9;
            	String tempStr = Integer.toString(tempNumber);
            	float x = (i * 60) + 60;
            	float y = (j * 60) + 80;
                TextWrapper tempTW = arrText[j][i];
                tempTW.content = tempStr;
                tempTW.position = new Vector2(x,y);
                tempTW.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.BLUE);
//                if (mLockMatrixNumber[j][i] != 0)
//                {
////                    String a = mLockMatrixNumber[j, i].ToString();
//                	int tempNumber = mLockMatrixNumber[j][i];
//                	String tempStr = Integer.toString(tempNumber);
//                    float x = (i * 60) + 50;
//                    float y = (j * 60) + 30;
//                    TextWrapper tempTW = arrText[j][i];
//                    tempTW.content = tempStr;
//                    tempTW.position = new Vector2(x,y);
//                    tempTW.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.BLUE);
//                }
//                if (mLockMatrixNumber[j][i] == 0 && mSolveSudoku.sudoku[j][i] != 0)
//                {
//                	int tempNumber = mSolveSudoku.sudoku[j][i];
//                	String tempStr = Integer.toString(tempNumber);
//                    float x = (i * 60) + 50;
//                    float y = (j * 60) + 30;
//                    TextWrapper tempTW = arrText[j][i];
//                    tempTW.content = tempStr;
//                    tempTW.position = new Vector2(x,y);
//                    tempTW.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.WHITE);
//                }
            }
        }
	}
}
