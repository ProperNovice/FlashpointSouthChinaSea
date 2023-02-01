package cards;

import countries.Brunei;
import countries.Indonesia;
import countries.Malaysia;
import countries.Philippines;
import countries.Vietnam;
import enums.EMode;
import enums.EScoring;
import gameStates.AddMoveCube;
import model.AddMoveInfluenceCubeBuilder;
import model.OperationValue;
import nations.Nation;
import nations.NationChina;
import utils.Flow;

public class Card11 extends CardEvent {

	@Override
	public OperationValue getOperationValue() {
		return new OperationValue(2);
	}

	@Override
	public EMode getEMode() {
		return EMode.MILITARY;
	}

	@Override
	public EScoring getEScoring() {
		return EScoring.MALAYSIA;
	}

	@Override
	public Class<? extends Nation> getNation() {
		return NationChina.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void resolveEvent() {

		new AddMoveInfluenceCubeBuilder().setNationClass(NationChina.class).setCubesToAdd(1)
				.addCountryForDiplomaticCube(Malaysia.class).buildAndAdd();

		new AddMoveInfluenceCubeBuilder().setNationClass(NationChina.class).setCubesToAdd(2)
				.addCountryForDiplomaticCube(Brunei.class, Indonesia.class, Philippines.class,
						Vietnam.class)
				.canAddMultipleCubesInTheSameTerritory(false).buildAndAdd();

		Flow.INSTANCE.getFlow().addAllFirst(AddMoveCube.class, AddMoveCube.class);

	}

}
