package Screens;

import GameUtils.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
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


public class AboutScreen implements Screen {

	MyMainGame mGame;
	Texture mTextureBackground;
	Texture mTextureSelector;
	SpriteBatch mBatch;
	Boolean isSelected;
	int menuIndex;
	TextWrapper txtTitle;
	TextWrapper txtHPT;
	TextWrapper txtHau;
	TextWrapper txtTri;
	TextWrapper txtBack;
	
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
					screenY > 310 && screenY < 340) {
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
			if(keycode == Keys.ESCAPE) {
				mGame.setScreen(new MainMenuScreen(mGame));
			}
			return false;
		}
	};
	
	public AboutScreen(MyMainGame game) {
		// TODO Auto-generated constructor stub
		this.mGame = game;
		mBatch = new SpriteBatch();
		isSelected = false;
		GameUtils.setInputProcessor(mInput);
		mTextureBackground = TextureManager.getInstance().textureBackgroundOutGame;
		mTextureSelector = TextureManager.getInstance().textureMainMenuSelector;
		txtTitle = new TextWrapper("Settings", new Vector2(120, 520));
		txtHau = new TextWrapper("Le Huynh Trung Hau - 0952082", new Vector2(200, 450));
		txtTri = new TextWrapper("Vo Minh Tri - 09520319", new Vector2(200, 400));
		txtHPT = new TextWrapper("Hua Phuoc Truong - 09520382", new Vector2(200, 350));
		txtBack = new TextWrapper("Back", new Vector2(150, 300));
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		GameUtils.clearScreen();
		mBatch.begin();
		mBatch.draw(mTextureBackground, 0, 0);
		mBatch.draw(mTextureSelector, 25, 255);
		
		txtTitle.Draw(mBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		txtHau.Draw(mBatch, GameUtils.getInstance().aboutFont, Color.WHITE);
		txtTri.Draw(mBatch, GameUtils.getInstance().aboutFont, Color.WHITE);
		txtHPT.Draw(mBatch, GameUtils.getInstance().aboutFont, Color.WHITE);
		txtBack.Draw(mBatch, GameUtils.getInstance().systemFont, Color.WHITE);
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
				SoundManager.getInstance().playSound(SoundManager.getInstance().mSoundSelectMenu);
				this.mGame.setScreen(new MainMenuScreen(this.mGame));
			}
		}
		
	}

}
