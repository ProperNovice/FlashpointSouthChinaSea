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

public class MoveCube extends AddMoveCube {

	@Override
	public void handleCountryInfluenceEconomicPressed(Country country) {

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst()
				.cubeAddedEconomic(country.getClass());

		Class<? extends Nation> classNation = AddMoveInfluenceCubesManager.INSTANCE.getList()
				.getFirst().getNationClass();

		Cube cube = country.getNationInfluenceEconomic().getValue(classNation).getListCubes()
				.getArrayList().removeLast();

		NationsManager.INSTANCE.getNation(classNation).getAvailable().getArrayList().addLast(cube);
		NationsManager.INSTANCE.getNation(classNation).getAvailable().animateSynchronousLock();

		Flow.INSTANCE.executeGameState(this.getClass());

	}

	@Override
	public void handleCountryInfluenceDiplomaticPressed(Country country) {

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst()
				.cubeAddedDiplomatic(country.getClass());

		Class<? extends Nation> classNation = AddMoveInfluenceCubesManager.INSTANCE.getList()
				.getFirst().getNationClass();

		Cube cube = country.getNationInfluenceDiplomatic().getValue(classNation).getListCubes()
				.getArrayList().removeLast();

		NationsManager.INSTANCE.getNation(classNation).getAvailable().getArrayList().addLast(cube);
		NationsManager.INSTANCE.getNation(classNation).getAvailable().animateSynchronousLock();

		Flow.INSTANCE.executeGameState(this.getClass());

	}

	@Override
	public void handleContestedIslandPressed(ContestedIsland contestedIsland) {

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst()
				.cubeAddedContestedIsland(contestedIsland.getClass());

		Class<? extends Nation> classNation = AddMoveInfluenceCubesManager.INSTANCE.getList()
				.getFirst().getNationClass();

		Cube cube = contestedIsland.getNationInfluence().getValue(classNation).getListCubes()
				.getArrayList().removeLast();

		NationsManager.INSTANCE.getNation(classNation).getAvailable().getArrayList().addLast(cube);
		NationsManager.INSTANCE.getNation(classNation).getAvailable().animateSynchronousLock();

		Flow.INSTANCE.executeGameState(this.getClass());

	}

	@Override
	protected boolean isEligible(ListImageViewAbles<Cube> list) {
		return !list.getArrayList().isEmpty();
	}

}
