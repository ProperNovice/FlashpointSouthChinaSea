package model;

import contestedIslands.ContestedIsland;
import countries.Country;
import nations.Nation;
import utils.ArrayList;

public class AddInfluenceCubes {

	private int cubesLeftToAdd = -1;
	private Class<? extends Nation> nationClass = null;
	private boolean canAddMultipleCubesInTheSameCountry = true;
	private ArrayList<Class<? extends Country>> countriesToAddEconomicCube = null;
	private ArrayList<Class<? extends Country>> countriesToAddDiplomaticCube = null;
	private ArrayList<Class<? extends ContestedIsland>> contestedIslandsToAddCube = null;

	public AddInfluenceCubes(int cubesLeftToAdd, Class<? extends Nation> nation,
			boolean canAddMultipleCubesInTheSameCountry,
			ArrayList<Class<? extends Country>> countriesToAddEconomicCube,
			ArrayList<Class<? extends Country>> countriesToAddDiplomaticCube,
			ArrayList<Class<? extends ContestedIsland>> contestedIslandsToAddCube) {

		this.cubesLeftToAdd = cubesLeftToAdd;
		this.nationClass = nation;
		this.canAddMultipleCubesInTheSameCountry = canAddMultipleCubesInTheSameCountry;
		this.countriesToAddEconomicCube = countriesToAddEconomicCube;
		this.countriesToAddDiplomaticCube = countriesToAddDiplomaticCube;
		this.contestedIslandsToAddCube = contestedIslandsToAddCube;

	}

	public void cubeAddedEconomic(Class<? extends Country> classCountry) {
		this.cubesLeftToAdd--;
	}

	public void cubeAddedDiplomatic(Class<? extends Country> classCountry) {
		this.cubesLeftToAdd--;
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
