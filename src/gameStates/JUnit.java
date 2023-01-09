package gameStates;

import java.lang.reflect.InvocationTargetException;

import components.Cube;
import components.CubeBlue;
import components.CubeRed;
import contestedIslands.ContestedIsland;
import contestedIslands.ScarboroughShoal;
import countries.Country;
import countries.Vietnam;
import gameStatesDefault.AGameState;
import javafx.scene.input.KeyCode;
import model.Map;
import model.NationsManager;
import model.TensionManager;
import nations.Nation;
import nations.NationChina;
import nations.NationUS;
import tensions.Tension;
import tensions.TensionHigh;

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

		addCubesContestedIsland(ScarboroughShoal.class, NationChina.class);
		addCubesContestedIsland(ScarboroughShoal.class, NationUS.class);

		addCubesAvailable(NationChina.class, 7);
		addCubesAvailable(NationUS.class, 9);

		addCubesReserve(NationChina.class, 7);
		addCubesReserve(NationUS.class, 9);

		addCubesPoliticalWarfare(NationChina.class, 2);
		addCubesPoliticalWarfare(NationUS.class, 1);

		setTension(TensionHigh.class);

	}

	public void addCubesAvailable(Class<? extends Nation> classNation, int cubes) {

		Nation nation = null;

		if (classNation.equals(NationUS.class))
			nation = NationsManager.INSTANCE.getNationUS();
		else if (classNation.equals(NationChina.class))
			nation = NationsManager.INSTANCE.getNationChina();

		for (int counter = 1; counter <= cubes; counter++)
			nation.getAvailable().getArrayList().addLast(new CubeRed());

		nation.getAvailable().animateAsynchronous();

	}

	public void addCubesReserve(Class<? extends Nation> classNation, int cubes) {

		Nation nation = null;

		if (classNation.equals(NationUS.class))
			nation = NationsManager.INSTANCE.getNationUS();
		else if (classNation.equals(NationChina.class))
			nation = NationsManager.INSTANCE.getNationChina();

		for (int counter = 1; counter <= cubes; counter++)
			nation.getReserve().getArrayList().addLast(new CubeRed());

		nation.getReserve().animateAsynchronous();

	}

	public void addCubesPoliticalWarfare(Class<? extends Nation> classNation, int cubes) {

		Nation nation = null;

		if (classNation.equals(NationUS.class))
			nation = NationsManager.INSTANCE.getNationUS();
		else if (classNation.equals(NationChina.class))
			nation = NationsManager.INSTANCE.getNationChina();

		for (int counter = 1; counter <= cubes; counter++)
			nation.getPoliticalWarfare().getArrayList().addLast(new CubeRed());

		nation.getPoliticalWarfare().animateAsynchronous();

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

	public void setTension(Class<? extends Tension> classTension) {
		TensionManager.INSTANCE.setTensionAnimate(classTension);
	}

}
