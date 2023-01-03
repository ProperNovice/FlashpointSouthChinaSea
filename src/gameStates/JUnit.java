package gameStates;

import countries.Vietnam;
import cubes.CubeBlue;
import enums.ENation;
import gameStatesDefault.AGameState;
import model.Map;

public class JUnit extends AGameState {

	@Override
	public void execute() {

		for (int counter = 1; counter <= 3; counter++)
			Map.INSTANCE.getCountry(Vietnam.class).getMapNationInfluenceEconomic()
					.getValue(ENation.US).getListCubes().getArrayList().addLast(new CubeBlue());

		Map.INSTANCE.getCountry(Vietnam.class).getMapNationInfluenceEconomic().getValue(ENation.US)
				.getListCubes().animateAsynchronous();

	}

}
