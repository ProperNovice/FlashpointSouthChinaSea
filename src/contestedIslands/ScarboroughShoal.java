package contestedIslands;

import utils.Vector2;

public class ScarboroughShoal extends ContestedIsland {

	@Override
	protected Vector2 getCenterCoordinates() {
		return new Vector2(601.5, 436);
	}

	@Override
	protected int getInfluenceSize() {
		return 2;
	}

}
