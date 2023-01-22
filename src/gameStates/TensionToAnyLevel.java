package gameStates;

import enums.EText;
import gameStatesDefault.GameState;
import model.TensionManager;
import tensions.Tension;
import utils.Flow;

public class TensionToAnyLevel extends GameState {

	@Override
	public void execute() {

		EText.SELECT_TENSION_LEVEL.show();
		TensionManager.INSTANCE.showTensionMapPositions();

	}

	@Override
	public void handleTensionPressed(Tension tension) {

		concealText();
		TensionManager.INSTANCE.hideTensionMapPositions();
		TensionManager.INSTANCE.setTensionAnimate(tension);
		Flow.INSTANCE.proceed();

	}

}
