package com.acumenvn.libsudoku;

import Screens.MainMenuScreen;

import com.badlogic.gdx.Game;

public class MyMainGame extends Game {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		setScreen(new MainMenuScreen(this));
	}
}
