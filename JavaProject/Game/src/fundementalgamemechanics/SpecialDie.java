package fundementalgamemechanics;

//A special die to be used by the system for functionalities.

public class SpecialDie extends Die {
	
//	~ is used as an unsused string space this may be replaced at a later time if another character is needed.
	public final static String[] SPECIALSIDES = new String[] {"(",")","=","~","~","~"};
	
	public SpecialDie(int index){
		
	}
	
	public void selectSide(int index) {
		this.setMyUpSide(SPECIALSIDES[index]);
	}
	
	@Override
	public void roll() {
		
	}

}
