package gameStates;

import components.Cube;
import contestedIslands.ContestedIsland;
import countries.Country;
import gameStatesDefault.GameState;
import model.AddMoveInfluenceCubes;
import model.AddMoveInfluenceCubesManager;
import model.Influence;
import model.Map;
import model.NationsManager;
import nations.Nation;
import utils.ArrayList;
import utils.Flow;
import utils.ListImageViewAbles;
import utils.Logger;
import utils.SelectImageViewManager;

public abstract class AddMoveCube extends GameState {

	private boolean canAddPoliticalWarfare;
	private ArrayList<Country> countryEligibleForEconomicInfluence = new ArrayList<>();
	private ArrayList<Country> countryEligibleForDiplomaticInfluence = new ArrayList<>();
	private ArrayList<ContestedIsland> contestedIslandEligible = new ArrayList<>();

	@Override
	public void execute() {

		setUpEligibility();
		printEligibles();
		handleOptions();

	}

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

	private void handlePoliticalWarfarePressed() {

		AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst().cubeAddedPoliticalWarfare();

		Class<? extends Nation> classNation = AddMoveInfluenceCubesManager.INSTANCE.getList()
				.getFirst().getNationClass();
		Nation nation = NationsManager.INSTANCE.getNation(classNation);

		Cube cube = getNationCube();
		nation.getPoliticalWarfare().getArrayList().addLast(cube);
		nation.getPoliticalWarfare().animateSynchronousLock();

		Flow.INSTANCE.executeGameState(this.getClass());

	}

	protected final Cube getNationCube() {

		Class<? extends Nation> classNation = AddMoveInfluenceCubesManager.INSTANCE.getList()
				.getFirst().getNationClass();
		Nation nation = NationsManager.INSTANCE.getNation(classNation);

		Cube cube = nation.getAvailable().getArrayList().removeLast();
		nation.getAvailable().animateAsynchronous();

		return cube;

	}

	private void handleOptions() {

		int cubesLeftToPlace = AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst()
				.getCubesLeftToAdd();

		int differentOptions = this.countryEligibleForEconomicInfluence.size()
				+ this.countryEligibleForDiplomaticInfluence.size()
				+ this.contestedIslandEligible.size();

		if (this.canAddPoliticalWarfare)
			differentOptions++;

		if (cubesLeftToPlace == 0) {

			AddMoveInfluenceCubesManager.INSTANCE.getList().removeFirst();
			Flow.INSTANCE.proceed();

		} else if (differentOptions == 0) {

			AddMoveInfluenceCubesManager.INSTANCE.getList().removeFirst();
			Flow.INSTANCE.proceed();

		} else if (differentOptions == 1) {

			for (Country country : this.countryEligibleForEconomicInfluence)
				handleCountryInfluenceEconomicPressed(country);

			for (Country country : this.countryEligibleForDiplomaticInfluence)
				handleCountryInfluenceDiplomaticPressed(country);

			for (ContestedIsland contestedIsland : this.contestedIslandEligible)
				handleContestedIslandPressed(contestedIsland);

			if (this.canAddPoliticalWarfare)
				handlePoliticalWarfarePressed();

		} else {

			for (Country country : this.countryEligibleForEconomicInfluence)
				country.getMapPositionInfluenceEconomic().setSelected();

			for (Country country : this.countryEligibleForDiplomaticInfluence)
				country.getMapPositionInfluenceDiplomatic().setSelected();

			for (ContestedIsland contestedIsland : this.contestedIslandEligible)
				contestedIsland.getMapPosition().setSelected();

		}

	}

	private void printEligibles() {

		Logger.INSTANCE.logNewLine("* eligibles printing *");
		Logger.INSTANCE.logNewLine(AddMoveInfluenceCubesManager.INSTANCE.getList().getFirst()
				.getNationClass().getSimpleName());

		// country influence economic

		if (!this.countryEligibleForEconomicInfluence.isEmpty()) {

			Logger.INSTANCE.logNewLine("eligible economic");

			for (Country country : this.countryEligibleForEconomicInfluence)
				Logger.INSTANCE.log(country.getClass().getSimpleName());

			Logger.INSTANCE.newLine();

		}

		// country influence diplomatic

		if (!this.countryEligibleForDiplomaticInfluence.isEmpty()) {

			Logger.INSTANCE.logNewLine("eligible diplomatic");

			for (Country country : this.countryEligibleForDiplomaticInfluence)
				Logger.INSTANCE.log(country.getClass().getSimpleName());

			Logger.INSTANCE.newLine();

		}

		// contested islands

		if (!this.contestedIslandEligible.isEmpty()) {

			Logger.INSTANCE.logNewLine("eligible contested islands");

			for (ContestedIsland contestedIsland : this.contestedIslandEligible)
				Logger.INSTANCE.log(contestedIsland.getClass().getSimpleName());

			Logger.INSTANCE.newLine();

		}

		// political warfare

		if (this.canAddPoliticalWarfare)
			Logger.INSTANCE.logNewLine("can add political warfare");

		Logger.INSTANCE.logNewLine("*");

	}

	private void setUpEligibility() {

		AddMoveInfluenceCubes addInfluenceCubes = AddMoveInfluenceCubesManager.INSTANCE.getList()
				.getFirst();
		Class<? extends Nation> classNation = addInfluenceCubes.getNationClass();

		// country influence economic

		for (Class<? extends Country> classCountry : addInfluenceCubes
				.getCountriesToAddEconomicCube()) {

			Country country = Map.INSTANCE.getCountry(classCountry);
			Influence influence = country.getNationInfluenceEconomic().getValue(classNation);

			if (!isEligible(influence.getListCubes()))
				continue;

			this.countryEligibleForEconomicInfluence.addLast(country);

		}

		// country influence diplomatic

		for (Class<? extends Country> classCountry : addInfluenceCubes
				.getCountriesToAddDiplomaticCube()) {

			Country country = Map.INSTANCE.getCountry(classCountry);
			Influence influence = country.getNationInfluenceDiplomatic().getValue(classNation);

			if (!isEligible(influence.getListCubes()))
				continue;

			this.countryEligibleForDiplomaticInfluence.addLast(country);

		}

		// contested islands

		for (Class<? extends ContestedIsland> classContestedIsland : addInfluenceCubes
				.getContestedIslandsToAddCube()) {

			ContestedIsland contestedIsland = Map.INSTANCE.getContestedIsland(classContestedIsland);
			Influence influence = contestedIsland.getNationInfluence().getValue(classNation);

			if (!isEligible(influence.getListCubes()))
				continue;

			this.contestedIslandEligible.addLast(contestedIsland);

		}

		// political warfare

		if (addInfluenceCubes.canAddPoliticalWarfare() && !NationsManager.INSTANCE
				.getNation(classNation).getPoliticalWarfare().getArrayList().isMaxCapacity())
			this.canAddPoliticalWarfare = true;
		else
			this.canAddPoliticalWarfare = false;

	}

	protected abstract boolean isEligible(ListImageViewAbles<Cube> list);

}
