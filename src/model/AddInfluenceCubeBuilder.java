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

public class AddInfluenceCubeBuilder {

	private Class<? extends Nation> nationClass = null;
	private int cubesToAdd = -1;
	private boolean canAddMultipleCubesInTheSameTerritory = true;
	private ArrayList<Class<? extends Country>> countriesToAddEconomicCube = new ArrayList<>();
	private ArrayList<Class<? extends Country>> countriesToAddDiplomaticCube = new ArrayList<>();
	private ArrayList<Class<? extends ContestedIsland>> contestedIslandsToAddCube = new ArrayList<>();
	private ArrayList<Class<? extends Country>> allCountries = new ArrayList<>();
	private ArrayList<Class<? extends ContestedIsland>> allContestedIslands = new ArrayList<>();

	public AddInfluenceCubeBuilder() {

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

	public AddInfluenceCubeBuilder setNationClass(Class<? extends Nation> nationClass) {
		this.nationClass = nationClass;
		return this;
	}

	public AddInfluenceCubeBuilder setCubesToAdd(int cubesToAdd) {
		this.cubesToAdd = cubesToAdd;
		return this;
	}

	public AddInfluenceCubeBuilder canAddMultipleCubesInTheSameTerritory(
			boolean canAddMultipleCubesInTheSameTerritory) {
		this.canAddMultipleCubesInTheSameTerritory = canAddMultipleCubesInTheSameTerritory;
		return this;
	}

	@SuppressWarnings("unchecked")
	public AddInfluenceCubeBuilder addCountryForEconomicCube(Class<? extends Country>... classes) {
		this.countriesToAddEconomicCube.addAllLast(classes);
		return this;
	}

	@SuppressWarnings("unchecked")
	public AddInfluenceCubeBuilder addCountryForDiplomaticCube(
			Class<? extends Country>... classes) {
		this.countriesToAddDiplomaticCube.addAllLast(classes);
		return this;
	}

	@SuppressWarnings("unchecked")
	public AddInfluenceCubeBuilder addContestedIslandCube(
			Class<? extends ContestedIsland>... classes) {
		this.contestedIslandsToAddCube.addAllLast(classes);
		return this;
	}

	public AddInfluenceCubeBuilder addAllCountriesForEconomicCubes() {
		this.countriesToAddEconomicCube.addAllLast(this.allCountries);
		return this;
	}

	public AddInfluenceCubeBuilder addAllCountriesForDiplomaticCubes() {
		this.countriesToAddDiplomaticCube.addAllLast(this.allCountries);
		return this;
	}

	public AddInfluenceCubeBuilder addAllContestedIslands() {
		this.contestedIslandsToAddCube.addAllLast(this.allContestedIslands);
		return this;
	}

	public AddInfluenceCubeBuilder addAllCountriesForBothEconomicAndDiplomaticCubes() {
		addAllCountriesForEconomicCubes();
		addAllCountriesForDiplomaticCubes();
		return this;
	}

	public AddInfluenceCubeBuilder addAllCountriesAndContestedIslands() {
		addAllCountriesForEconomicCubes();
		addAllCountriesForDiplomaticCubes();
		addAllContestedIslands();
		return this;
	}

	public void buildAndAdd() {

		if (this.nationClass == null || this.cubesToAdd == -1)
			ShutDown.INSTANCE.execute();

		AddInfluenceCubes addInfluenceCubes = new AddInfluenceCubes(this.nationClass,
				this.cubesToAdd, this.canAddMultipleCubesInTheSameTerritory,
				this.countriesToAddEconomicCube, this.countriesToAddDiplomaticCube,
				contestedIslandsToAddCube);

		AddInfluenceCubesManager.INSTANCE.addInfluenceCubeAction(addInfluenceCubes);

	}

}
