package cards;

import enums.EMode;
import enums.EScoring;
import gameStates.AddMoveCube;
import gameStates.IncreaseTension;
import model.AddMoveInfluenceCubeBuilder;
import model.OperationValue;
import nations.Nation;
import nations.NationChina;
import utils.Flow;

public class Card10 extends CardEvent {

	@Override
	public OperationValue getOperationValue() {
		return new OperationValue(3);
	}

	@Override
	public EMode getEMode() {
		return EMode.MILITARY;
	}

	@Override
	public EScoring getEScoring() {
		return EScoring.ECONOMICS;
	}

	@Override
	public Class<? extends Nation> getNation() {
		return NationChina.class;
	}

	@Override
	public void resolveEvent() {

		new AddMoveInfluenceCubeBuilder().setNationClass(NationChina.class).setCubesToAdd(3)
				.canAddPoliticalWarfare(true).buildAndAdd();

		Flow.INSTANCE.getFlow().addAllFirst(AddMoveCube.class, IncreaseTension.class);

	}

}
