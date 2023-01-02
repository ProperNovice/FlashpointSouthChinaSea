package gameStatesDefault;

import enums.EText;

public class EndGameLost extends AEndGame {

	@Override
	protected EText getEText() {
		return EText.YOU_LOST;
	}

}
