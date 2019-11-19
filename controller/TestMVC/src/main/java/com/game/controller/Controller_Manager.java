package com.game.controller;

import org.springframework.ui.Model;

public interface Controller_Manager {
	
	boolean challengeNow(String playerInput);
	boolean challengeImpossible(String playerInput);
	void moveDie(Integer mat, Integer index, Model m);

}
