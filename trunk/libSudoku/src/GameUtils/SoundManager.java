package GameUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	private static SoundManager instance = null;
	
	public static SoundManager getInstance() {
		if(instance == null) {
			instance = new SoundManager();
		}
		return instance;
	}
	
	public Sound mSoundSelectMenu;
	public Sound mSoundMenuClick;
	
	
	public SoundManager() {
		// TODO Auto-generated constructor stub
		mSoundSelectMenu = loadSound("Sounds/switchMenu.mp3"); // switch
		mSoundMenuClick = loadSound("Sounds/buttonpush.wav"); // select
	}
	
	public Sound loadSound(String filePath) {
		return Gdx.audio.newSound(Gdx.files.internal(filePath));
	}
	
	public void playSound(Sound s) {
		if(GameUtils.getInstance().sound == true) {
			s.play();
		}
	}
	
	
	public void stopSound(Sound s) {
		s.stop();
	}
	
	public void playSoundWithLoop(Sound s) {
		if(GameUtils.getInstance().sound == true) {
			s.loop();
		}
	}
	
	public void playMusic(Sound s) {
		if(GameUtils.getInstance().music == true) {
			s.play();
		}
	}
}
