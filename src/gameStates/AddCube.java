package gameStates;

import components.Cube;
import contestedIslands.ContestedIsland;
import countries.Country;
import gameStatesDefault.AGameState;
import model.AddInfluenceCubes;
import model.AddInfluenceCubesManager;
import model.Influence;
import model.Map;
import model.NationsManager;
import nations.Nation;
import utils.ArrayList;
import utils.Flow;
import utils.Logger;
import utils.SelectImageViewManager;

public class AddCube extends AGameState {

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
		AddInfluenceCubesManager.INSTANCE.getList().getFirst()
				.cubeAddedEconomic(country.getClass());

		Cube cube = getNationCube();

		country.getNationInfluenceEconomic()
				.getValue(AddInfluenceCubesManager.INSTANCE.getList().getFirst().getNationClass())
				.getListCubes().getArrayList().addLast(cube);

		country.getNationInfluenceEconomic()
				.getValue(AddInfluenceCubesManager.INSTANCE.getList().getFirst().getNationClass())
				.getListCubes().animateSynchronousLock();

		Flow.INSTANCE.executeGameState(AddCube.class);

	}

	@Override
	public void handleCountryInfluenceDiplomaticPressed(Country country) {

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		AddInfluenceCubesManager.INSTANCE.getList().getFirst()
				.cubeAddedDiplomatic(country.getClass());

		Cube cube = getNationCube();

		country.getNationInfluenceDiplomatic()
				.getValue(AddInfluenceCubesManager.INSTANCE.getList().getFirst().getNationClass())
				.getListCubes().getArrayList().addLast(cube);

		country.getNationInfluenceDiplomatic()
				.getValue(AddInfluenceCubesManager.INSTANCE.getList().getFirst().getNationClass())
				.getListCubes().animateSynchronousLock();

		Flow.INSTANCE.executeGameState(AddCube.class);

	}

	@Override
	public void handleContestedIslandPressed(ContestedIsland contestedIsland) {

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		AddInfluenceCubesManager.INSTANCE.getList().getFirst()
				.cubeAddedContestedIsland(contestedIsland.getClass());

		Cube cube = getNationCube();

		contestedIsland.getNationInfluence()
				.getValue(AddInfluenceCubesManager.INSTANCE.getList().getFirst().getNationClass())
				.getListCubes().getArrayList().addLast(cube);

		contestedIsland.getNationInfluence()
				.getValue(AddInfluenceCubesManager.INSTANCE.getList().getFirst().getNationClass())
				.getListCubes().animateSynchronousLock();

		Flow.INSTANCE.executeGameState(AddCube.class);

	}

	private Cube getNationCube() {

		Class<? extends Nation> classNation = AddInfluenceCubesManager.INSTANCE.getList().getFirst()
				.getNationClass();
		Nation nation = NationsManager.INSTANCE.getNation(classNation);

		Cube cube = nation.getAvailable().getArrayList().removeFirst();
		nation.getAvailable().animateAsynchronous();

		return cube;

	}

	private void handleOptions() {

		int cubesLeftToPlace = AddInfluenceCubesManager.INSTANCE.getList().getFirst()
				.getCubesLeftToAdd();

		int differentOptions = this.countryEligibleForEconomicInfluence.size()
				+ this.countryEligibleForDiplomaticInfluence.size()
				+ this.contestedIslandEligible.size();

		if (cubesLeftToPlace == 0) {

			AddInfluenceCubesManager.INSTANCE.getList().removeFirst();
			proceedToNextGameState();

		} else if (differentOptions == 0) {

			AddInfluenceCubesManager.INSTANCE.getList().removeFirst();
			proceedToNextGameState();

		} else if (differentOptions == 1) {

			for (Country country : this.countryEligibleForEconomicInfluence)
				handleCountryInfluenceEconomicPressed(country);

			for (Country country : this.countryEligibleForDiplomaticInfluence)
				handleCountryInfluenceDiplomaticPressed(country);

			for (ContestedIsland contestedIsland : this.contestedIslandEligible)
				handleContestedIslandPressed(contestedIsland);

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
		Logger.INSTANCE.logNewLine(AddInfluenceCubesManager.INSTANCE.getList().getFirst()
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

		Logger.INSTANCE.logNewLine("*");

	}

	private void setUpEligibility() {

		AddInfluenceCubes addInfluenceCubes = AddInfluenceCubesManager.INSTANCE.getList()
				.getFirst();
		Class<? extends Nation> classNation = addInfluenceCubes.getNationClass();

		// country influence economic

		for (Class<? extends Country> classCountry : addInfluenceCubes
				.getCountriesToAddEconomicCube()) {

			Country country = Map.INSTANCE.getCountry(classCountry);
			Influence influence = country.getNationInfluenceEconomic().getValue(classNation);

			if (influence.getListCubes().getArrayList().isMaxCapacity())
				continue;

			this.countryEligibleForEconomicInfluence.addLast(country);

		}

		// country influence diplomatic

		for (Class<? extends Country> classCountry : addInfluenceCubes
				.getCountriesToAddDiplomaticCube()) {

			Country country = Map.INSTANCE.getCountry(classCountry);
			Influence influence = country.getNationInfluenceDiplomatic().getValue(classNation);

			if (influence.getListCubes().getArrayList().isMaxCapacity())
				continue;

			this.countryEligibleForDiplomaticInfluence.addLast(country);

		}

		// contested islands

		for (Class<? extends ContestedIsland> classContestedIsland : addInfluenceCubes
				.getContestedIslandsToAddCube()) {

			ContestedIsland contestedIsland = Map.INSTANCE.getContestedIsland(classContestedIsland);
			Influence influence = contestedIsland.getNationInfluence().getValue(classNation);

			if (influence.getListCubes().getArrayList().isMaxCapacity())
				continue;

			this.contestedIslandEligible.addLast(contestedIsland);

		}

	}

}
