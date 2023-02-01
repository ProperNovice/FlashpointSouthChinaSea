package cards;

import enums.EMode;
import enums.EScoring;
import gameStates.AddMoveCube;
import gameStates.TensionToLow;
import model.AddMoveInfluenceCubeBuilder;
import model.OperationValue;
import nations.Nation;
import nations.NationChina;
import utils.Flow;

public class Card12 extends CardEvent {

	@Override
	public OperationValue getOperationValue() {
		return new OperationValue(2);
	}

	@Override
	public EMode getEMode() {
		return EMode.TRADE;
	}

	@Override
	public EScoring getEScoring() {
		return EScoring.PHILIPPINES;
	}

	@Override
	public Class<? extends Nation> getNation() {
		return NationChina.class;
	}

	@Override
	public void resolveEvent() {

		new AddMoveInfluenceCubeBuilder().setNationClass(NationChina.class).setCubesToAdd(2)
				.addAllCountriesForEconomicCubes().canAddMultipleCubesInTheSameTerritory(false)
				.buildAndAdd();

		Flow.INSTANCE.getFlow().addAllFirst(AddMoveCube.class, TensionToLow.class);

	}

}
