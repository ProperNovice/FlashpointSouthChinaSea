package cards;

import enums.EMode;
import enums.EScoring;
import gameStates.ExecuteOperation;
import gameStates.TensionToAnyLevel;
import model.OperationValue;
import nations.Nation;
import utils.Flow;

public class Card01 extends CardEvent {

	@Override
	public OperationValue getOperationValue() {
		return new OperationValue(1);
	}

	@Override
	public EMode getEMode() {
		return EMode.TERRITORIAL;
	}

	@Override
	public EScoring getEScoring() {
		return EScoring.VIETNAM;
	}

	@Override
	public Class<? extends Nation> getNation() {
		return null;
	}

	@Override
	public void resolveEvent() {
		Flow.INSTANCE.getFlow().addAllFirst(TensionToAnyLevel.class, ExecuteOperation.class);
	}

}
