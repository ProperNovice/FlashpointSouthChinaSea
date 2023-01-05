package gameStates;

import java.lang.reflect.InvocationTargetException;

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
import cubes.Cube;
import cubes.CubeBlue;
import cubes.CubeRed;
import gameStatesDefault.AGameState;
import javafx.scene.input.KeyCode;
import model.Map;
import nations.Nation;
import nations.NationChina;
import nations.NationUS;

public class JUnit extends AGameState {

	private boolean run = false;

	@Override
	public void execute() {

	}

	@Override
	protected void handleKeyPressed(KeyCode keyCode) {

		if (this.run)
			return;

		this.run = true;

		addCubesCountry(Vietnam.class, NationChina.class);
		addCubesCountry(Vietnam.class, NationUS.class);
		addCubesCountry(Malaysia.class, NationChina.class);
		addCubesCountry(Malaysia.class, NationUS.class);
		addCubesCountry(Philippines.class, NationChina.class);
		addCubesCountry(Philippines.class, NationUS.class);
		addCubesCountry(Brunei.class, NationChina.class);
		addCubesCountry(Brunei.class, NationUS.class);
		addCubesCountry(Indonesia.class, NationChina.class);
		addCubesCountry(Indonesia.class, NationUS.class);

		addCubesContestedIsland(ScarboroughShoal.class, NationChina.class);
		addCubesContestedIsland(ScarboroughShoal.class, NationUS.class);
		addCubesContestedIsland(ParacelIslands.class, NationChina.class);
		addCubesContestedIsland(ParacelIslands.class, NationUS.class);
		addCubesContestedIsland(SpratlyIslands.class, NationChina.class);
		addCubesContestedIsland(SpratlyIslands.class, NationUS.class);

	}

	public void addCubesContestedIsland(Class<? extends ContestedIsland> classContestedIsland,
			Class<? extends Nation> classNation) {

		ContestedIsland country = Map.INSTANCE.getContestedIsland(classContestedIsland);
		int size = country.getNationInfluence().getValue(classNation).getListCubes().getArrayList()
				.getCapacity();

		Class<? extends Cube> classCube = null;

		if (classNation.equals(NationChina.class))
			classCube = CubeRed.class;
		else if (classNation.equals(NationUS.class))
			classCube = CubeBlue.class;

		try {

			for (int counter = 1; counter <= size; counter++)
				Map.INSTANCE.getContestedIsland(classContestedIsland).getNationInfluence()
						.getValue(classNation).getListCubes().getArrayList()
						.addLast(classCube.getConstructor().newInstance());

			Map.INSTANCE.getContestedIsland(classContestedIsland).getNationInfluence()
					.getValue(classNation).getListCubes().animateAsynchronous();

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}

	public void addCubesCountry(Class<? extends Country> classCountry,
			Class<? extends Nation> classNation) {

		Country country = Map.INSTANCE.getCountry(classCountry);
		int size = country.getNationInfluenceEconomic().getValue(classNation).getListCubes()
				.getArrayList().getCapacity();

		Class<? extends Cube> classCube = null;

		if (classNation.equals(NationChina.class))
			classCube = CubeRed.class;
		else if (classNation.equals(NationUS.class))
			classCube = CubeBlue.class;

		try {

			for (int counter = 1; counter <= size; counter++)
				Map.INSTANCE.getCountry(classCountry).getNationInfluenceEconomic()
						.getValue(classNation).getListCubes().getArrayList()
						.addLast(classCube.getConstructor().newInstance());

			for (int counter = 1; counter <= size; counter++)
				Map.INSTANCE.getCountry(classCountry).getNationInfluenceDiplomatic()
						.getValue(classNation).getListCubes().getArrayList()
						.addLast(classCube.getConstructor().newInstance());

			Map.INSTANCE.getCountry(classCountry).getNationInfluenceEconomic().getValue(classNation)
					.getListCubes().animateAsynchronous();

			Map.INSTANCE.getCountry(classCountry).getNationInfluenceDiplomatic()
					.getValue(classNation).getListCubes().animateAsynchronous();

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}

}
