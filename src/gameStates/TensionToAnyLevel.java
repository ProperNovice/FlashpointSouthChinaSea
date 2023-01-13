package gameStates;

import enums.EText;
import gameStatesDefault.AGameState;
import model.TensionManager;
import tensions.Tension;

public class TensionToAnyLevel extends AGameState {

	@Override
	public void execute() {

		EText.SELECT_TENSION_LEVEL.show();
		TensionManager.INSTANCE.showTensionMapPositions();

	}

	@Override
	public void handleTensionPressed(Tension tension) {

		TensionManager.INSTANCE.hideTensionMapPositions();
		TensionManager.INSTANCE.setTensionAnimate(tension.getClass());
		proceedToNextGameState();

	}

}
