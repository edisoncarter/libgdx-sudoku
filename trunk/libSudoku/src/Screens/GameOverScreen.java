package Screens;

import GameUtils.*;
import GameObjects.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.acumenvn.libsudoku.MyMainGame;;


public class GameOverScreen implements Screen {

	private MyMainGame mGame;
	private SpriteBatch mBatch;
	private Texture mTextureBackground;
	private TextWrapper txtCongrats;
	private TextWrapper txtTime;
	private TextWrapper txtEnterToComplete;
	private TextWrapper txtGameMode;
	Sound mSoundFinish;
	public String timeStr;
	
	
	private InputProcessor mInput = new InputProcessor() {
		
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
			if(keycode == Keys.ENTER) {
				SoundManager.getInstance().playSound(SoundManager.getInstance().mSoundSelectMenu);
				mGame.setScreen(new MainMenuScreen(mGame));
			}
			return false;
		}
	};
	
	public GameOverScreen(MyMainGame game) {
		// TODO Auto-generated constructor stub
		this.mGame = game;
		GameUtils.setInputProcessor(mInput);
		mBatch = new SpriteBatch();
		mSoundFinish = SoundManager.getInstance().mSoundFinish;
		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();
		mTextureBackground = TextureManager.getInstance().textureVictory;
		txtEnterToComplete = new TextWrapper("Press enter to go to Main Menu", new Vector2(400, 100));
		txtTime = new TextWrapper("", new Vector2(400,150));
		txtCongrats = new TextWrapper("Congratulation! You have complete a sudoku", new Vector2(0,0));
		String levelStr = "";
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
		txtGameMode = new TextWrapper("Level: " + levelStr, new Vector2(400, 180));
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		GameUtils.clearScreen();
		
		mBatch.begin();
		mBatch.draw(mTextureBackground, 0, 0);
		txtEnterToComplete.Draw(mBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		txtTime.Draw(mBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		txtGameMode.Draw(mBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		txtCongrats.Draw(mBatch, GameUtils.getInstance().systemFont, Color.WHITE);
		mBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		txtTime.content = "In: " + timeStr;
		SoundManager.getInstance().playMusic(mSoundFinish);
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

}
