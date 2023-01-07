package nations;

import components.Cube;
import components.CubeRed;
import utils.Vector2;

public class NationChina extends Nation {

	@Override
	protected Class<? extends Cube> getClassCubeColor() {
		return CubeRed.class;
	}

	@Override
	protected Vector2 getCoordinatesAvailable() {
		return new Vector2(110.5, getCoordinatesReserve().y);
	}

	@Override
	protected Vector2 getCoordinatesReserve() {
		return new Vector2(302.5, 125 + 10);
	}

	@Override
	protected Vector2 getCoordinatesPoliticalWarfare() {
		return new Vector2(254, 229);
	}

}
