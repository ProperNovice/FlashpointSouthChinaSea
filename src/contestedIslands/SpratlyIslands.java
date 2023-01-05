package contestedIslands;

import utils.Vector2;

public class SpratlyIslands extends ContestedIsland {

	@Override
	protected Vector2 getCenterCoordinates() {
		return new Vector2(487.5, 795);
	}

	@Override
	protected int getInfluenceSize() {
		return 2;
	}

}
