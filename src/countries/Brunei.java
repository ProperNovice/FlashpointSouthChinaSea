package countries;

import utils.Vector2;

public class Brunei extends Country {

	@Override
	protected Vector2 getCenterCoordinates() {
		return new Vector2(730, 929);
	}

	@Override
	protected int getInfluenceSize() {
		return 1;
	}

}
