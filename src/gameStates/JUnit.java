package gameStates;

import java.lang.reflect.InvocationTargetException;

import cards.Card12;
import cards.CardEvent;
import components.Cube;
import components.CubeBlue;
import components.CubeRed;
import contestedIslands.ContestedIsland;
import contestedIslands.ScarboroughShoal;
import contestedIslands.SpratlyIslands;
import countries.Country;
import countries.Malaysia;
import countries.Vietnam;
import gameStatesDefault.GameState;
import javafx.scene.input.KeyCode;
import model.AddMoveInfluenceCubeBuilder;
import model.Map;
import model.NationsManager;
import model.TensionManager;
import nations.Nation;
import nations.NationChina;
import nations.NationUS;
import utils.Flow;

public class JUnit extends GameState {

	@Override
	public void execute() {

		addCubesCountry(Vietnam.class, NationChina.class, 2);
		addCubesCountry(Malaysia.class, NationUS.class, 2);

		addCubesContestedIsland(ScarboroughShoal.class, NationChina.class, 1);
		addCubesContestedIsland(SpratlyIslands.class, NationUS.class, 1);

		addCubesAvailable(NationChina.class, 7);
		addCubesAvailable(NationUS.class, 9);

		addCubesReserve(NationChina.class, 7);
		addCubesReserve(NationUS.class, 9);

//		addCubesPoliticalWarfare(NationChina.class, 3);
		addCubesPoliticalWarfare(NationUS.class, 1);

		increaseTension(1);

		new AddMoveInfluenceCubeBuilder().canAddMultipleCubesInTheSameTerritory(false)
				.addAllCountriesAndContestedIslands().setNationClass(NationUS.class)
				.setCubesToAdd(4).buildAndAdd();

//		Flow.INSTANCE.executeGameState(AddCube.class);
		Flow.INSTANCE.executeGameState(MoveCube.class);

	}

	@Override
	protected void handleKeyPressed(KeyCode keyCode) {

		resolveCardEvent(Card12.class);
		Flow.INSTANCE.proceed();

	}

	public void addCubesAvailable(Class<? extends Nation> classNation, int cubes) {

		Nation nation = NationsManager.INSTANCE.getNation(classNation);

		for (int counter = 1; counter <= cubes; counter++) {

			Cube cube = null;

			if (classNation.equals(NationChina.class))
				cube = new CubeRed();
			else if (classNation.equals(NationUS.class))
				cube = new CubeBlue();

			nation.getAvailable().getArrayList().addLast(cube);

		}

		nation.getAvailable().relocateImageViews();

	}

	public void addCubesReserve(Class<? extends Nation> classNation, int cubes) {

		Nation nation = NationsManager.INSTANCE.getNation(classNation);

		for (int counter = 1; counter <= cubes; counter++) {

			Cube cube = null;

			if (classNation.equals(NationChina.class))
				cube = new CubeRed();
			else if (classNation.equals(NationUS.class))
				cube = new CubeBlue();

			nation.getReserve().getArrayList().addLast(cube);

		}

		nation.getReserve().relocateImageViews();

	}

	public void addCubesPoliticalWarfare(Class<? extends Nation> classNation, int cubes) {

		Nation nation = NationsManager.INSTANCE.getNation(classNation);

		for (int counter = 1; counter <= cubes; counter++) {

			Cube cube = null;

			if (classNation.equals(NationChina.class))
				cube = new CubeRed();
			else if (classNation.equals(NationUS.class))
				cube = new CubeBlue();

			nation.getPoliticalWarfare().getArrayList().addLast(cube);

		}

		nation.getPoliticalWarfare().relocateImageViews();

	}

	public void addCubesContestedIsland(Class<? extends ContestedIsland> classContestedIsland,
			Class<? extends Nation> classNation, int cubes) {

		Class<? extends Cube> classCube = null;

		if (classNation.equals(NationChina.class))
			classCube = CubeRed.class;
		else if (classNation.equals(NationUS.class))
			classCube = CubeBlue.class;

		try {

			for (int counter = 1; counter <= cubes; counter++)
				Map.INSTANCE.getContestedIsland(classContestedIsland).getNationInfluence()
						.getValue(classNation).getListCubes().getArrayList()
						.addLast(classCube.getConstructor().newInstance());

			Map.INSTANCE.getContestedIsland(classContestedIsland).getNationInfluence()
					.getValue(classNation).getListCubes().relocateImageViews();

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}

	public void addCubesCountry(Class<? extends Country> classCountry,
			Class<? extends Nation> classNation, int cubes) {

		Class<? extends Cube> classCube = null;

		if (classNation.equals(NationChina.class))
			classCube = CubeRed.class;
		else if (classNation.equals(NationUS.class))
			classCube = CubeBlue.class;

		try {

			for (int counter = 1; counter <= cubes; counter++)
				Map.INSTANCE.getCountry(classCountry).getNationInfluenceEconomic()
						.getValue(classNation).getListCubes().getArrayList()
						.addLast(classCube.getConstructor().newInstance());

			for (int counter = 1; counter <= cubes; counter++)
				Map.INSTANCE.getCountry(classCountry).getNationInfluenceDiplomatic()
						.getValue(classNation).getListCubes().getArrayList()
						.addLast(classCube.getConstructor().newInstance());

			Map.INSTANCE.getCountry(classCountry).getNationInfluenceEconomic().getValue(classNation)
					.getListCubes().relocateImageViews();

			Map.INSTANCE.getCountry(classCountry).getNationInfluenceDiplomatic()
					.getValue(classNation).getListCubes().relocateImageViews();

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}

	public void increaseTension(int times) {
		for (int counter = 1; counter <= times; counter++)
			TensionManager.INSTANCE.increaseTension();
	}

	public void resolveCardEvent(Class<? extends CardEvent> cardEventClass) {

		try {

			CardEvent card = cardEventClass.getConstructor().newInstance();
			card.getImageView().relocateTopLeft(1620, 25);
			card.print();

			card.resolveEvent();

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}

}
