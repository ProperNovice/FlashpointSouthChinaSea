package countries;

import utils.Vector2;

public class Indonesia extends Country {

	@Override
	protected Vector2 getCenterCoordinates() {
		return new Vector2(540, 1107);
	}

	@Override
	protected int getInfluenceSize() {
		return 2;
	}

}
