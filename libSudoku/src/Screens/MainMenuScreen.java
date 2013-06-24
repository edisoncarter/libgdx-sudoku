package Screens;

import GameUtils.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.acumenvn.libsudoku.MyMainGame;


public class MainMenuScreen implements Screen {

	// Variables
	MyMainGame mGame;
	SpriteBatch mSpriteBatch;
	Texture mTextureMainBackground;
	Texture mTextureMainMenu;
	Texture mTextureSelector;
	int menuIndex;
	int selector_X;
	int selector_Y;
	Boolean isSelected;
	Sound selectMenu;
	Sound switchMenu;
	TextWrapper mTxtTitle;
	TextWrapper mTxtNewGame;
	TextWrapper mTxtSolve;
	TextWrapper mTxtSetting;
	TextWrapper mTxtAbout;
	TextWrapper mTxtExit;
	int selectedIndex;
	
	InputProcessor mInputP = new InputProcessor() {
		
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
			// New Game
			isSelected = false;
			if(screenX > 65 && screenX < 200 && 
					screenY > 160 && screenY < 190) {
				// New Game
				menuIndex = 0;
				selector_Y = 600 - 185;
				isSelected = true;
				SoundManager.getInstance().playSound(SoundManager.getInstance().mSoundSwitchMenu);
			}
			
			// Solve
			if(screenX > 65 && screenX < 200 && 
					screenY > 210 && screenY < 240) {
				selector_Y = 600 - 235;
				menuIndex = 1;
				isSelected = true;
				SoundManager.getInstance().playSound(SoundManager.getInstance().mSoundSwitchMenu);
			}
			
			// Setting
			
			if(screenX > 65 && screenX < 200 && 
					screenY > 260 && screenY < 290) {
				selector_Y = 600 - 285;
				menuIndex = 2;
				isSelected = true;
				SoundManager.getInstance().playSound(SoundManager.getInstance().mSoundSwitchMenu);
			}
			// About
			
			if(screenX > 65 && screenX < 200 && 
					screenY > 310 && screenY < 340) {
				selector_Y  = 600 - 335;
				menuIndex = 3;
				isSelected = true;
				SoundManager.getInstance().playSound(SoundManager.getInstance().mSoundSwitchMenu);
			}
			// Exit
			if(screenX > 65 && screenX < 200 && 
					screenY > 360 && screenY < 390) {
				selector_Y = 600 - 385;
				menuIndex = 4;
				isSelected = true;
				SoundManager.getInstance().playSound(SoundManager.getInstance().mSoundSwitchMenu);
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
			return false;
		}
	};

	
	public MainMenuScreen(MyMainGame game) {
		this.mGame = game;
		isSelected = false;
		mTextureMainBackground = TextureManager.getInstance().textureBackgroundOutGame;
		mTextureSelector = TextureManager.getInstance().textureMainMenuSelector;
		mSpriteBatch = new SpriteBatch();
		selector_X = 30;
		
		GameUtils.setInputProcessor(mInputP);
//		selectMenu = SoundManager.getInstance().mSoundMenuClick;
//		switchMenu = SoundManager.getInstance().mSoundSelectMenu;
		mTxtTitle = new TextWrapper("TNT Sudoku", new Vector2(120,520));
	
		
		mTxtNewGame = new TextWrapper("New Game", new Vector2(150,450));
		mTxtSetting = new TextWrapper("Settings", new Vector2(150, 350));
		mTxtSolve = new TextWrapper("Solve", new Vector2(150,400));
		mTxtAbout = new TextWrapper("About", new Vector2(150, 300));
		mTxtExit = new TextWrapper("Exit", new Vector2(150, 250));
		selector_Y = (int)mTxtNewGame.position.y - 35;
		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		GameUtils.clearScreen();
		mSpriteBatch.begin();
		mSpriteBatch.draw(mTextureMainBackground, 0, 0);
		GameUtils.getInstance().systemFont.setColor(Color.RED);
		mTxtTitle.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.WHITE, 1.5f);
		
		GameUtils.getInstance().systemFont.setScale(1.2f);
		GameUtils.getInstance().systemFont.setColor(Color.RED);
		mTxtNewGame.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.RED);
		mTxtSolve.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.RED);
		mTxtSetting.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.RED);
		mTxtAbout.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.RED);
		mTxtExit.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.RED);
		
		mSpriteBatch.draw(mTextureSelector, selector_X, selector_Y);
		mSpriteBatch.end();
		
		handleInput();
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
		mTextureMainBackground.dispose();
		mSpriteBatch.dispose();
	}
	
	private void handleInput() {
		if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
			if(isSelected) {
				SoundManager.getInstance().playSound(SoundManager.getInstance().mSoundSelectMenu);
				switch (menuIndex) {
				case 0:
					// Start game
					this.mGame.setScreen(new ChooseLevel(this.mGame));
					break;
				case 1:
					// Solve Sudoku
					this.mGame.setScreen(new SolveSudokuScreen(this.mGame));
					break;
				case 2:
					// Setting
					this.mGame.setScreen(new SettingScreen(this.mGame));
					break;
				case 3:
					// About
					this.mGame.setScreen(new AboutScreen(mGame));
					break;
				case 4:
					// Exit
					TextureManager.getInstance().dispose();
					Gdx.app.exit();
					break;
				default:
					break;
				}
			}
		}
	}

}
