package cards;

import enums.EMode;
import enums.EScoring;
import gameStates.ResolveCardAsOperation;
import gameStates.TensionToAnyLevel;
import model.OperationValue;
import nations.Nation;
import utils.Flow;

public class Card06 extends CardEvent {

	@Override
	public OperationValue getOperationValue() {
		return new OperationValue(1);
	}

	@Override
	public EMode getEMode() {
		return EMode.TRADE;
	}

	@Override
	public EScoring getEScoring() {
		return EScoring.INDONESIA;
	}

	@Override
	public Class<? extends Nation> getNation() {
		return null;
	}

	@Override
	public void resolveEvent() {
		Flow.INSTANCE.getFlow().addAllFirst(TensionToAnyLevel.class, ResolveCardAsOperation.class);
	}

}
