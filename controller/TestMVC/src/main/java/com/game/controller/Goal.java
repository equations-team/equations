package com.game.controller;

import java.util.Vector;

public class Goal extends Mat implements MatReader{
	private Vector<DiceFace> myGoal = new Vector<DiceFace>();
	private int myNumNormalDice;
	
	public void Read() {
		myGoal.clear();
		for(int i = 0;i < this.getMyMat().size();i++) {
			myGoal.add(myMat.get(i).getMyUpSide());
		}
	}
	
	public void addPerenthises() {
		myMat.add(new SpecialDie(0));
		myMat.add(new SpecialDie(1));
	}
	
	@Override
	public void addToMyMat(Die in) {
		myNumNormalDice++;
		myMat.add(in);
	}
	
	public boolean removeDieByIndex(int index) {
		return false;
	}
	
	public Vector<DiceFace> getMyGoal() {
		return myGoal;		
	}
	
	public int getMyNumNormalDice() {
		return myNumNormalDice;
	}
}
