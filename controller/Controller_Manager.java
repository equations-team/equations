package controller;

import org.springframework.ui.Model;

public interface Controller_Manager {
	
	boolean challengeNow(String playerInput);
	boolean challengeImpossible(String playerInput);
	boolean moveDie(int mat, int index, Model m);

}
