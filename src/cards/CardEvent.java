package cards;

import enums.EMode;
import enums.EScoring;
import model.OperationValue;
import nations.Nation;
import utils.Logger;

public abstract class CardEvent extends Card {

	public CardEvent() {

	}

	public abstract OperationValue getOperationValue();

	public abstract EMode getEMode();

	public abstract EScoring getEScoring();

	public abstract Class<? extends Nation> getNation();

	public abstract void resolveEvent();

	@Override
	protected final void printCredentials() {

		Logger.INSTANCE.log("card type -> event");
		Logger.INSTANCE.log("operation value -> " + getOperationValue().getValue());
		Logger.INSTANCE.log("mode -> " + getEMode());
		Logger.INSTANCE.log("scoring -> " + getEScoring());
		Logger.INSTANCE.log("nation -> " + getNation());
		Logger.INSTANCE.newLine();

	}

}
