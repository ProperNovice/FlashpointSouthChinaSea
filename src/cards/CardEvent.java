package cards;

import enums.EMode;
import enums.EScoring;
import model.OperationValue;
import nations.Nation;

public abstract class CardEvent extends Card {

	public CardEvent() {

	}

	public abstract OperationValue getOperationValue();

	public abstract EMode getEMode();

	public abstract EScoring getEScoring();

	public abstract Class<? extends Nation> getNation();

	public abstract void resolveEvent();

}
