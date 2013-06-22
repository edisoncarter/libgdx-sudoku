package Screens;

import GameUtils.GameUtils;
import GameUtils.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.acumenvn.libsudoku.MyMainGame;;


public class AboutScreen implements Screen {

	MyMainGame mGame;
	Texture mTextureBackground;
	Texture mTextureContext;
	SpriteBatch mBatch;
	Boolean isSelected;
	
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
		mTextureBackground = TextureManager.getInstance().textureBackgroundOutGame;
		mTextureContext = TextureManager.getInstance().textureAboutContext;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		GameUtils.clearScreen();
		mBatch.begin();
		mBatch.draw(mTextureBackground, 0, 0);
		mBatch.end();
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
		mTextureBackground.dispose();
		
	}
	
	private void handleInput() {
		if(isSelected) {
			this.mGame.setScreen(new MainMenuScreen(this.mGame));
		}
	}

}
