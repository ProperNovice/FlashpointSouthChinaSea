package gameStates;

import enums.EText;
import gameStatesDefault.GameState;
import model.TensionManager;
import tensions.Tension;
import utils.Text;

public abstract class TensionToLevelSet extends GameState {

	@Override
	public void execute() {

		getEText().show();
		EText.CONTINUE.show();

	}

	@Override
	protected void executeTextOption(EText eText) {
		
		Text.INSTANCE.concealText();
		TensionManager.INSTANCE.setTensionAnimate(getTensionClass());

	}

	protected abstract EText getEText();

	protected abstract Class<? extends Tension> getTensionClass();

}
