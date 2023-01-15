package gameStates;

import java.lang.reflect.InvocationTargetException;

import cards.Card09;
import cards.CardEvent;
import components.Cube;
import components.CubeBlue;
import components.CubeRed;
import contestedIslands.ContestedIsland;
import contestedIslands.ScarboroughShoal;
import countries.Country;
import countries.Vietnam;
import gameStatesDefault.AGameState;
import model.Map;
import model.NationsManager;
import model.TensionManager;
import nations.Nation;
import nations.NationChina;
import nations.NationUS;

public class JUnit extends AGameState {

	@Override
	public void execute() {

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

		increaseTension(1);

		resolveCardEvent(Card09.class);

//		proceedToNextGameState();

	}

	public void addCubesAvailable(Class<? extends Nation> classNation, int cubes) {

		Nation nation = null;

		if (classNation.equals(NationUS.class))
			nation = NationsManager.INSTANCE.getNationUS();
		else if (classNation.equals(NationChina.class))
			nation = NationsManager.INSTANCE.getNationChina();

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

		Nation nation = null;

		if (classNation.equals(NationUS.class))
			nation = NationsManager.INSTANCE.getNationUS();
		else if (classNation.equals(NationChina.class))
			nation = NationsManager.INSTANCE.getNationChina();

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

		Nation nation = null;

		if (classNation.equals(NationUS.class))
			nation = NationsManager.INSTANCE.getNationUS();
		else if (classNation.equals(NationChina.class))
			nation = NationsManager.INSTANCE.getNationChina();

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
					.getValue(classNation).getListCubes().relocateImageViews();

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
