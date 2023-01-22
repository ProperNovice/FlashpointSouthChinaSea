package cards;

import enums.EMode;
import enums.EScoring;
import gameStates.AddCube;
import gameStates.IncreaseTension;
import model.AddInfluenceCubeBuilder;
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

		new AddInfluenceCubeBuilder().setNationClass(NationChina.class).setCubesToAdd(3)
				.canAddPoliticalWarfare(true).buildAndAdd();

		Flow.INSTANCE.getFlow().addAllFirst(AddCube.class, IncreaseTension.class);

	}

}
