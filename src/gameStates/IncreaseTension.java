package gameStates;

import enums.EText;
import gameStatesDefault.GameState;
import model.TensionManager;
import utils.Flow;

public class IncreaseTension extends GameState {

	@Override
	public void execute() {

		EText.INCREASE_TENSION.show();
		EText.CONTINUE.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		TensionManager.INSTANCE.increaseTension();
		Flow.INSTANCE.proceed();

	}

}
