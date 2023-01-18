package model;

import contestedIslands.ContestedIsland;
import countries.Country;
import nations.Nation;
import utils.ArrayList;

public class AddInfluenceCubes {

	private Class<? extends Nation> nationClass = null;
	private int cubesLeftToAdd = -1;
	private boolean canAddMultipleCubesInTheSameTerritory = true;
	private boolean canAddPoliticalWarfare = false;
	private ArrayList<Class<? extends Country>> countriesToAddEconomicCube = null;
	private ArrayList<Class<? extends Country>> countriesToAddDiplomaticCube = null;
	private ArrayList<Class<? extends ContestedIsland>> contestedIslandsToAddCube = null;

	public AddInfluenceCubes(Class<? extends Nation> nation, int cubesLeftToAdd,
			boolean canAddMultipleCubesInTheSameTerritory, boolean canAddPoliticalWarfare,
			ArrayList<Class<? extends Country>> countriesToAddEconomicCube,
			ArrayList<Class<? extends Country>> countriesToAddDiplomaticCube,
			ArrayList<Class<? extends ContestedIsland>> contestedIslandsToAddCube) {

		this.cubesLeftToAdd = cubesLeftToAdd;
		this.nationClass = nation;
		this.canAddMultipleCubesInTheSameTerritory = canAddMultipleCubesInTheSameTerritory;
		this.canAddPoliticalWarfare = canAddPoliticalWarfare;
		this.countriesToAddEconomicCube = countriesToAddEconomicCube;
		this.countriesToAddDiplomaticCube = countriesToAddDiplomaticCube;
		this.contestedIslandsToAddCube = contestedIslandsToAddCube;

	}

	public void cubeAddedEconomic(Class<? extends Country> classCountry) {

		this.cubesLeftToAdd--;

		if (!this.canAddMultipleCubesInTheSameTerritory)
			this.countriesToAddEconomicCube.remove(classCountry);

	}

	public void cubeAddedDiplomatic(Class<? extends Country> classCountry) {

		this.cubesLeftToAdd--;

		if (!this.canAddMultipleCubesInTheSameTerritory)
			this.countriesToAddDiplomaticCube.remove(classCountry);

	}

	public void cubeAddedContestedIsland(Class<? extends ContestedIsland> classContestedIsland) {

		this.cubesLeftToAdd--;

		if (!this.canAddMultipleCubesInTheSameTerritory)
			this.contestedIslandsToAddCube.remove(classContestedIsland);

	}

	public void cubeAddedPoliticalWarfare() {
		this.cubesLeftToAdd--;
	}

	public boolean canAddPoliticalWarfare() {
		return this.canAddPoliticalWarfare;
	}

	public int getCubesLeftToAdd() {
		return this.cubesLeftToAdd;
	}

	public Class<? extends Nation> getNationClass() {
		return this.nationClass;
	}

	public ArrayList<Class<? extends Country>> getCountriesToAddEconomicCube() {
		return this.countriesToAddEconomicCube;
	}

	public ArrayList<Class<? extends Country>> getCountriesToAddDiplomaticCube() {
		return this.countriesToAddDiplomaticCube;
	}

	public ArrayList<Class<? extends ContestedIsland>> getContestedIslandsToAddCube() {
		return this.contestedIslandsToAddCube;
	}

}
