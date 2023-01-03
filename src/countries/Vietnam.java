package countries;

import utils.Vector2;

public class Vietnam extends Country {

	@Override
	protected Vector2 getCenterCoordinates() {
		return new Vector2(213, 721);
	}

	@Override
	protected int getInfluenceSize() {
		return 3;
	}

}
