package countries;

import utils.Vector2;

public class Malaysia extends Country {

	@Override
	protected Vector2 getCenterCoordinates() {
		return new Vector2(213, 927);
	}

	@Override
	protected int getInfluenceSize() {
		return 2;
	}

}
