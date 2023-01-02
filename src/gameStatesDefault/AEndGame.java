package gameStatesDefault;

import enums.EText;
import utils.Flow;
import utils.SelectImageViewManager;

public abstract class AEndGame extends AGameState {

	@Override
	public void execute() {

		concealText();
		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		Flow.INSTANCE.getFlow().clear();

		getEText().show();
		EText.RESTART.show();

	}

	@Override
	protected void executeTextOption(EText eText) {
		Flow.INSTANCE.executeGameState(RestartGame.class);
	}

	protected abstract EText getEText();

}
