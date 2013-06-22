package Screens;

import GameUtils.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

public class ChooseLevel implements Screen {

	MyMainGame mGame;
	SpriteBatch mSpriteBatch;
	Texture mTextureBackground;
	Texture mTextureMenu;
	Texture mTextureSelector;
	TextWrapper mTxtChooseTitle;
	TextWrapper mTxtEasy;
	TextWrapper mTxtNormal;
	TextWrapper mTxtHard;
	TextWrapper mTxtExit;
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
	
	
	
	public ChooseLevel(MyMainGame game) {
		this.mGame = game;
		mSpriteBatch = new SpriteBatch();
		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();
		GameUtils.setInputProcessor(mInputP);
		mTextureBackground = TextureManager.getInstance().textureBackgroundOutGame;
		mTextureSelector = TextureManager.getInstance().textureMainMenuSelector;
		mTxtChooseTitle = new TextWrapper("Choose level", new Vector2(120,520));
		mTxtEasy = new TextWrapper("Easy", new Vector2(150,450));
		mTxtNormal = new TextWrapper("Normal", new Vector2(150,400));
		mTxtHard = new TextWrapper("Hard", new Vector2(150, 350));
		mTxtExit = new TextWrapper("Exit", new Vector2(150, 300));
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		GameUtils.clearScreen();
		mSpriteBatch.begin();
		mSpriteBatch.draw(mTextureBackground, 0, 0);
		GameUtils.getInstance().systemFont.scale(1.5f);
		mTxtChooseTitle.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		mTxtEasy.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		mTxtNormal.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		mTxtHard.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		mTxtExit.Draw(mSpriteBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		mSpriteBatch.end();
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

}
