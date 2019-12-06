package localhostgame;

import java.awt.Image;

import javax.swing.ImageIcon;

import dice.DiceFace;

public class DieIcon extends ImageIcon{
	
	private DiceFace myDieFace;
	
	private ImageIcon myIcon;
	
	
	public DieIcon(ImageIcon image, DiceFace face) {
		myDieFace = face;
		myIcon = image;
	}

	public DiceFace getDieFace() {
		return myDieFace;
	}

	public void setDieFace(DiceFace d) {
		this.myDieFace = d;
	}

	public ImageIcon getIcon() {
		return myIcon;
	}

	public void setIcon(ImageIcon ico) {
		this.myIcon = ico;
	}

}
