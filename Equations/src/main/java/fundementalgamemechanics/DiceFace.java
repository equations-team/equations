package fundementalgamemechanics;

public enum DiceFace {
	ONE("1"),TWO("2"),THREE("3"),FOUR("4"),FIVE("5"),SIX("6"),SEVEN("7"),EIGHT("8"),NINE("9"),
	ZERO("0"),ADDITION("+"),SUBTRACTION("-"),MULTIPLICATION("*"),DIVISION("/"),POWER("^"),ROOT("?"),
	LEFT("("),RIGHT(")");

	private String value;

	public String getValue() {
		return value;
	}

	private DiceFace(String v) {
		value = v;
	}
}

// String Expectations for Solver
//	1,2,3,4,5,6,7,8,9,0
//	+,-,*,/,^,?
// (,)