package gameStates;

import components.Cube;
import contestedIslands.ContestedIsland;
import countries.Country;
import model.AddMoveInfluenceCubesManager;
import model.NationsManager;
import nations.Nation;
import utils.Flow;
import utils.ListImageViewAbles;
import utils.SelectImageViewManager;

public class AddCube extends AddMoveCube {

	@Override
	public void handleCountryInfluenceEconomicPressed(Country country) {

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst()
				.cubeAddedEconomic(country.getClass());

		Cube cube = getNationCube();

		country.getNationInfluenceEconomic()
				.getValue(
						AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst().getNationClass())
				.getListCubes().getArrayList().addLast(cube);

		country.getNationInfluenceEconomic()
				.getValue(
						AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst().getNationClass())
				.getListCubes().animateSynchronousLock();

		Flow.INSTANCE.executeGameState(this.getClass());

	}

	@Override
	public void handleCountryInfluenceDiplomaticPressed(Country country) {

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst()
				.cubeAddedDiplomatic(country.getClass());

		Cube cube = getNationCube();

		country.getNationInfluenceDiplomatic()
				.getValue(
						AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst().getNationClass())
				.getListCubes().getArrayList().addLast(cube);

		country.getNationInfluenceDiplomatic()
				.getValue(
						AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst().getNationClass())
				.getListCubes().animateSynchronousLock();

		Flow.INSTANCE.executeGameState(this.getClass());

	}

	@Override
	public void handleContestedIslandPressed(ContestedIsland contestedIsland) {

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst()
				.cubeAddedContestedIsland(contestedIsland.getClass());

		Cube cube = getNationCube();

		contestedIsland.getNationInfluence()
				.getValue(
						AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst().getNationClass())
				.getListCubes().getArrayList().addLast(cube);

		contestedIsland.getNationInfluence()
				.getValue(
						AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst().getNationClass())
				.getListCubes().animateSynchronousLock();

		Flow.INSTANCE.executeGameState(this.getClass());

	}

	protected void handlePoliticalWarfarePressed() {

		AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst().cubeAddedPoliticalWarfare();

		Class<? extends Nation> classNation = AddMoveInfluenceCubesManager.INSTANCE.getList()
				.getFirst().getNationClass();
		Nation nation = NationsManager.INSTANCE.getNation(classNation);

		Cube cube = getNationCube();
		nation.getPoliticalWarfare().getArrayList().addLast(cube);
		nation.getPoliticalWarfare().animateSynchronousLock();

		Flow.INSTANCE.executeGameState(this.getClass());

	}

	private Cube getNationCube() {

		Class<? extends Nation> classNation = AddMoveInfluenceCubesManager.INSTANCE.getList()
				.getFirst().getNationClass();
		Nation nation = NationsManager.INSTANCE.getNation(classNation);

		Cube cube = nation.getAvailable().getArrayList().removeLast();
		nation.getAvailable().animateAsynchronous();

		return cube;

	}

	@Override
	protected boolean isEligible(ListImageViewAbles<Cube> list) {
		return !list.getArrayList().isMaxCapacity();
	}

}
