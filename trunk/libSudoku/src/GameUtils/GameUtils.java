package GameUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameUtils {
	public static void clearScreen() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	}
	
	public static void setInputProcessor(InputProcessor input) {
		Gdx.input.setInputProcessor(input);
	}
	
	public static Boolean mouseHover(int xPos, int yPos, int textureX, int textureY, Texture t) {
		if(xPos > textureX - 10 && xPos < textureX + 10 + t.getWidth() &&
		   yPos > textureY - 10 && yPos < textureY + 10 + t.getHeight()) {
			return true;
		}
		return false;
	}
	
	
	public Boolean sound;
	public Boolean music;
	public BitmapFont systemFont;
	
	public int levelIndex;
	
	private static GameUtils instance = null;
	

	
	public static GameUtils getInstance() {
		if(instance == null) {
			instance = new GameUtils();
		}
		return instance;
	}
	
	public GameUtils() {
		// TODO Auto-generated constructor stub
		sound = true;
		music = true;
		systemFont = new BitmapFont(Gdx.files.internal("Fonts/systemFont.fnt"), false);
	}
	
}
