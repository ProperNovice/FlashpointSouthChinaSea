package contestedIslands;

import utils.Vector2;

public class ParacelIslands extends ContestedIsland {

	@Override
	protected Vector2 getCenterCoordinates() {
		return new Vector2(446.5, 547);
	}

	@Override
	protected int getInfluenceSize() {
		return 2;
	}

}
