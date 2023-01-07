package utils;

public class Vector2 {

	public double x, y;

	public Vector2(double x, double y) {

		this.x = x;
		this.y = y;

	}

	public Vector2 addAndReturnNew(Vector2 vector2) {

		double x = this.x + vector2.x;
		double y = this.y + vector2.y;

		return new Vector2(x, y);

	}

	public void print() {

		Logger.INSTANCE.log("x -> " + this.x);
		Logger.INSTANCE.log("y -> " + this.y);
		Logger.INSTANCE.newLine();

	}

	public Vector2 clone() {
		return new Vector2(this.x, this.y);
	}

}
