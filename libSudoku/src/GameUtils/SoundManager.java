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
	
	public Sound mSoundSwitchMenu;
	public Sound mSoundFinish;
	public Sound mSoundSelectMenu;
	public Sound mSoundStartGame;
	public Sound mButtonPush;
	
	
	public SoundManager() {
		// TODO Auto-generated constructor stub
		mButtonPush = loadSound("Sounds/buttonpush.mp3");
		mSoundFinish = loadSound("Sounds/finish.mp3");
		mSoundSelectMenu = loadSound("Sounds/SelectMenu.mp3");
		mSoundStartGame = loadSound("Sounds/startgame.mp3");
		mSoundSwitchMenu = loadSound("Sounds/switchMenu.mp3");
	}
	
	public Sound loadSound(String filePath) {
		return Gdx.audio.newSound(Gdx.files.internal(filePath));
	}
	
	public void playSound(Sound s) {
		if(GameUtils.getInstance().sound == true) {
			s.play(1.0f);
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
