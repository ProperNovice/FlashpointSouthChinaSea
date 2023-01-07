package nations;

import components.Cube;
import components.CubeBlue;
import utils.Vector2;

public class NationUS extends Nation {

	@Override
	protected Class<? extends Cube> getClassCubeColor() {
		return CubeBlue.class;
	}

	@Override
	protected Vector2 getCoordinatesAvailable() {
		return new Vector2(802.5, getCoordinatesReserve().y);
	}

	@Override
	protected Vector2 getCoordinatesReserve() {
		return new Vector2(923, 319.5);
	}

	@Override
	protected Vector2 getCoordinatesPoliticalWarfare() {
		return new Vector2(897, 504);
	}

}
