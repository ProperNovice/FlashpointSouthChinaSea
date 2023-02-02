package gameStates;

import components.Cube;
import utils.ListImageViewAbles;

public class MoveCube extends AddMoveCube {

	@Override
	protected boolean isEligible(ListImageViewAbles<Cube> list) {
		return !list.getArrayList().isEmpty();
	}

}
