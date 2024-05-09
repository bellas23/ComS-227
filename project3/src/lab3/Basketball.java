package lab3;

public class Basketball {
	
	//instance variables
	private double diameter;
	private boolean isInflated;
	
	//constructer
	public Basketball(double givenDiameter) {
		diameter = givenDiameter;
		isInflated = false;
	}

	public boolean isDribbleable() {
		return isInflated;
	}

	public double getDiameter() {
		return diameter;
	}

	public double getCircumference() {
		return 0;
	}

	public void inflate() {
		isInflated = true;
	}

}
