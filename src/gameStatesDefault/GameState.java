package gameStatesDefault;

import contestedIslands.ContestedIsland;
import countries.Country;
import enums.EText;
import javafx.scene.input.KeyCode;
import tensions.Tension;
import utils.ArrayList;
import utils.Flow;
import utils.KeyCodeHandler;
import utils.Logger;
import utils.Text;

public abstract class GameState {

	public abstract void execute();

	public final void handleTextOptionPressed(EText textEnum) {

		Logger.INSTANCE.log("text option executing");
		Logger.INSTANCE.logNewLine(textEnum);

		concealText();
		executeTextOption(textEnum);

	}

	public final void executeKeyPressed(KeyCode keyCode) {

		handleKeyPressed(keyCode);

		int keyCodeID = KeyCodeHandler.INSTANCE.getKeyCodeInt(keyCode);

		if (keyCodeID == -1)
			return;

		EText textEnumPressed = Text.INSTANCE.getTextEnumOptionPressed(keyCodeID);

		if (textEnumPressed == null)
			return;

		Logger.INSTANCE.log("executing key pressed -> " + keyCode);
		handleTextOptionPressed(textEnumPressed);

	}

	protected void handleKeyPressed(KeyCode keyCode) {

	}

	protected void executeTextOption(EText eText) {

	}

	protected final void concealText() {
		Text.INSTANCE.concealText();
	}

	protected final ArrayList<Class<? extends GameState>> getFlow() {
		return Flow.INSTANCE.getFlow();
	}

	public void handleTensionPressed(Tension tension) {

	}

	public void handleCountryInfluenceEconomicPressed(Country country) {

	}

	public void handleCountryInfluenceDiplomaticPressed(Country country) {

	}

	public void handleContestedIslandPressed(ContestedIsland contestedIsland) {

	}

}
