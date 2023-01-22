package gameStatesDefault;

import gameStates.StartGame;
import utils.Flow;
import utils.SelectImageViewManager;

public class RestartGame extends GameState {

	@Override
	public void execute() {

		concealText();
		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		Flow.INSTANCE.getFlow().clear();

		Flow.INSTANCE.executeGameState(StartGame.class);

	}

}
