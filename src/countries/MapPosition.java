package countries;

import utils.Interfaces.IMouseEventAble;
import utils.Interfaces.ISelectCoordinatesAble;
import utils.Vector2;

public class MapPosition implements IMouseEventAble, ISelectCoordinatesAble {

	private Runnable runnable = null;
	private Vector2 vector2 = null;

	public MapPosition(Vector2 vector2, Runnable runnable) {
		this.vector2 = vector2;
		this.runnable = runnable;
	}

	@Override
	public void handleMousePressedPrimary() {
		this.runnable.run();
	}

	@Override
	public Vector2 getSelectCoordinates() {
		return this.vector2;
	}

}
