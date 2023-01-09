package utils;

public class Vector2 {

	public double x, y;

	public Vector2(double x, double y) {

		this.x = x;
		this.y = y;

	}

	public Vector2 addVector2(Vector2 vector2) {

		double x = this.x + vector2.x;
		double y = this.y + vector2.y;

		return new Vector2(x, y);

	}

	public Vector2 addX(double x) {

		x += this.x;
		return new Vector2(x, this.y);

	}

	public Vector2 addY(double y) {

		y += this.y;
		return new Vector2(this.x, y);

	}

	public Vector2 substractVector2(Vector2 vector2) {

		double x = this.x - vector2.x;
		double y = this.y - vector2.y;

		return new Vector2(x, y);

	}

	public Vector2 substractX(double x) {

		x = this.x - x;
		return new Vector2(x, this.y);

	}

	public Vector2 substractY(double y) {

		y = this.y - y;
		return new Vector2(this.x, y);

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
