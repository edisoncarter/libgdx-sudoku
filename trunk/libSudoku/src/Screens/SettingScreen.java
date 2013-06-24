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


public class SettingScreen implements Screen {

	SpriteBatch mBatch;
	MyMainGame mGame;
	TextWrapper txtSound;
	TextWrapper txtMusic;
	TextWrapper txtTitle;
	TextWrapper txtBack;
	Texture selector;
	Texture background;
	int selector_Y;
	Boolean isSelected;
	int menuIndex;
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
			isSelected = false;
			if(screenX > 65 && screenX < 200 && 
					screenY > 160 && screenY < 190) {
				// New Game
				menuIndex = 0;
				selector_Y = 600 - 185;
				isSelected = true;
			}
			
			
			// Solve
			if(screenX > 65 && screenX < 200 && 
					screenY > 210 && screenY < 240) {
				selector_Y = 600 - 235;
				menuIndex = 1;
				isSelected = true;
			}
			
			// Back
			if(screenX > 65 && screenX < 200 && 
					screenY > 260 && screenY < 290) {
				selector_Y = 600 - 285;
				menuIndex = 2;
				isSelected = true;
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
	
	public SettingScreen(MyMainGame game) {
		// TODO Auto-generated constructor stub
		this.mGame = game;
		mBatch = new SpriteBatch();
		String music = "";
		String sound = "";
		isSelected = false;
		GameUtils.setInputProcessor(mInput);
		if(GameUtils.getInstance().music) {
			music = "Music: ON";
		} else {
			music = "Music: OFF";
		}
		
		if(GameUtils.getInstance().sound) {
			sound = "Sound: ON";
		} else {
			sound = "Sound: OFF";	
		}
		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();
		background = TextureManager.getInstance().textureBackgroundOutGame;
		selector = TextureManager.getInstance().textureMainMenuSelector;
		txtTitle = new TextWrapper("Settings", new Vector2(120, 520));
		txtMusic = new TextWrapper(music, new Vector2(150, 450));
		txtSound = new TextWrapper(sound, new Vector2(150, 400));
		txtBack = new TextWrapper("Back", new Vector2(150, 350));
		selector_Y = (int)txtMusic.position.y - 35;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		GameUtils.clearScreen();
		mBatch.begin();
		mBatch.draw(background, 0, 0);
		txtTitle.Draw(mBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		txtMusic.Draw(mBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		txtSound.Draw(mBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		txtBack.Draw(mBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		mBatch.draw(selector, 25, selector_Y);
		mBatch.end();
		
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
		mBatch.dispose();
	}

	private void handleInput() {
		if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
			if(isSelected) {
				switch (menuIndex) {
				case 0:
					// Sound
					if(GameUtils.getInstance().music) {
						GameUtils.getInstance().music = false;
						txtMusic.content = "Music: OFF";
					} else {
						GameUtils.getInstance().music = true;
						txtMusic.content = "Music: ON";
					}
					break;
				case 1:
					// Music
					if(GameUtils.getInstance().sound) {
						GameUtils.getInstance().sound = false;
						txtSound.content = "Sound: OFF";
					} else {
						GameUtils.getInstance().sound = true;
						txtSound.content = "Sound: ON";
					}
					break;
				case 2:
					this.mGame.setScreen(new MainMenuScreen(mGame));
					break;
				default:
					break;
				}
			}
		}
	}
}
