package model;

import contestedIslands.ContestedIsland;
import contestedIslands.ParacelIslands;
import contestedIslands.ScarboroughShoal;
import contestedIslands.SpratlyIslands;
import countries.Brunei;
import countries.Country;
import countries.Indonesia;
import countries.Malaysia;
import countries.Philippines;
import countries.Vietnam;
import nations.Nation;
import utils.ArrayList;
import utils.ShutDown;

public class AddMoveInfluenceCubeBuilder {

	private Class<? extends Nation> nationClass = null;
	private int cubesToAdd = -1;
	private boolean canAddMultipleCubesInTheSameTerritory = true;
	private boolean canAddPoliticalWarfare = false;
	private ArrayList<Class<? extends Country>> countriesToAddEconomicCube = new ArrayList<>();
	private ArrayList<Class<? extends Country>> countriesToAddDiplomaticCube = new ArrayList<>();
	private ArrayList<Class<? extends ContestedIsland>> contestedIslandsToAddCube = new ArrayList<>();
	private ArrayList<Class<? extends Country>> allCountries = new ArrayList<>();
	private ArrayList<Class<? extends ContestedIsland>> allContestedIslands = new ArrayList<>();

	public AddMoveInfluenceCubeBuilder() {

		// add all countries

		this.allCountries.addLast(Brunei.class);
		this.allCountries.addLast(Indonesia.class);
		this.allCountries.addLast(Malaysia.class);
		this.allCountries.addLast(Philippines.class);
		this.allCountries.addLast(Vietnam.class);

		// add all contested islands

		this.allContestedIslands.addLast(ParacelIslands.class);
		this.allContestedIslands.addLast(ScarboroughShoal.class);
		this.allContestedIslands.addLast(SpratlyIslands.class);

	}

	public AddMoveInfluenceCubeBuilder setNationClass(Class<? extends Nation> nationClass) {
		this.nationClass = nationClass;
		return this;
	}

	public AddMoveInfluenceCubeBuilder setCubesToAdd(int cubesToAdd) {
		this.cubesToAdd = cubesToAdd;
		return this;
	}

	public AddMoveInfluenceCubeBuilder canAddMultipleCubesInTheSameTerritory(
			boolean canAddMultipleCubesInTheSameTerritory) {
		this.canAddMultipleCubesInTheSameTerritory = canAddMultipleCubesInTheSameTerritory;
		return this;
	}

	public AddMoveInfluenceCubeBuilder canAddPoliticalWarfare(boolean canAddPoliticalWarfare) {
		this.canAddPoliticalWarfare = canAddPoliticalWarfare;
		return this;
	}

	@SuppressWarnings("unchecked")
	public AddMoveInfluenceCubeBuilder addCountryForEconomicCube(Class<? extends Country>... classes) {
		this.countriesToAddEconomicCube.addAllLast(classes);
		return this;
	}

	@SuppressWarnings("unchecked")
	public AddMoveInfluenceCubeBuilder addCountryForDiplomaticCube(
			Class<? extends Country>... classes) {
		this.countriesToAddDiplomaticCube.addAllLast(classes);
		return this;
	}

	@SuppressWarnings("unchecked")
	public AddMoveInfluenceCubeBuilder addContestedIslandCube(
			Class<? extends ContestedIsland>... classes) {
		this.contestedIslandsToAddCube.addAllLast(classes);
		return this;
	}

	public AddMoveInfluenceCubeBuilder addAllCountriesForEconomicCubes() {
		this.countriesToAddEconomicCube.addAllLast(this.allCountries);
		return this;
	}

	public AddMoveInfluenceCubeBuilder addAllCountriesForDiplomaticCubes() {
		this.countriesToAddDiplomaticCube.addAllLast(this.allCountries);
		return this;
	}

	public AddMoveInfluenceCubeBuilder addAllContestedIslands() {
		this.contestedIslandsToAddCube.addAllLast(this.allContestedIslands);
		return this;
	}

	public AddMoveInfluenceCubeBuilder addAllCountriesForBothEconomicAndDiplomaticCubes() {
		addAllCountriesForEconomicCubes();
		addAllCountriesForDiplomaticCubes();
		return this;
	}

	public AddMoveInfluenceCubeBuilder addAllCountriesAndContestedIslands() {
		addAllCountriesForEconomicCubes();
		addAllCountriesForDiplomaticCubes();
		addAllContestedIslands();
		return this;
	}

	public void buildAndAdd() {

		if (this.nationClass == null || this.cubesToAdd == -1)
			ShutDown.INSTANCE.execute();

		AddMoveInfluenceCubes addInfluenceCubes = new AddMoveInfluenceCubes(this.nationClass,
				this.cubesToAdd, this.canAddMultipleCubesInTheSameTerritory,
				this.canAddPoliticalWarfare, this.countriesToAddEconomicCube,
				this.countriesToAddDiplomaticCube, contestedIslandsToAddCube);

		AddMoveInfluenceCubesManager.INSTANCE.addInfluenceCubeAction(addInfluenceCubes);

	}

}
