package countries;

import utils.Vector2;

public class Philippines extends Country {

	@Override
	protected Vector2 getCenterCoordinates() {
		return new Vector2(834, 723);
	}

	@Override
	protected int getInfluenceSize() {
		return 3;
	}

}
