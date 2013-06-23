package GameUtils;

import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
	private static TextureManager instance = null;
	
	public static TextureManager getInstance() {
		if(instance == null) {
			instance = new TextureManager();
		}
		return instance;
	}
	
	// Variables
	public Texture textureBackgroundOutGame;
	public Texture textureMainMenuContext;
	public Texture textureMainMenuSelector;
	public Texture textureAboutContext;
	public Texture textureBackgroundInGame;
	
	public Texture textureBtn_Check;
	public Texture textureBtn_Wrong;
	public Texture textureBtn_quitOn;
	public Texture textureBtn_quitOff;
	public Texture textureBtn_resetOn;
	public Texture textureBtn_resetOff;
	public Texture textureBtn_settingOn;
	public Texture textureBtn_settingOff;
	public Texture textureBtn_checker;
	public Texture textureBtn_solve;
	public Texture textureWrongMessage;
	public Texture textureBorderSelect;
	public Texture textureVictory;
	
	public Texture textureBtnSelected;
	public Texture textureBtnUnselected;
	
	// Constructor
	public TextureManager() {
		// TODO Auto-generated constructor stub
		textureBackgroundOutGame = new Texture("Images/background_MainMenu.png");
		textureMainMenuSelector = new Texture("Images/mainMenuSelector.png");
		textureBackgroundInGame = new Texture("Images/gamescreenBG.png");
		textureVictory = new Texture("Images/Victory.png");
		// Buttons
		textureBtn_Check = new Texture("Images/Check.png");
		textureBtn_Wrong = new Texture("Images/wrong.png");
		textureBtn_quitOn = new Texture("Images/quiton.png");
		textureBtn_quitOff = new Texture("Images/quitoff.png");
		textureBtn_resetOn = new Texture("Images/reseton.png");
		textureBtn_resetOff = new Texture("Images/resetoff.png");
		textureBtn_settingOn = new Texture("Images/settingon.png");
		textureBtn_settingOff = new Texture("Images/settingoff.png");
		textureBtn_checker = new Texture("Images/Solve.png");
		textureBtn_solve = new Texture("Images/wrong.png");
		textureWrongMessage = new Texture("Images/wrongmess.png");
		textureBorderSelect = new Texture("Images/BorderSelect.png");
		
		textureBtnSelected = new Texture("Images/btnSelected.png");
		textureBtnUnselected = new Texture("Images/btnUnselected.png");
	}
	
	// Methods
	
}
