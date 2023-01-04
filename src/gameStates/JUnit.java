package gameStates;

import java.lang.reflect.InvocationTargetException;

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

		add(Vietnam.class, NationChina.class);
		add(Vietnam.class, NationUS.class);
		add(Malaysia.class, NationChina.class);
		add(Malaysia.class, NationUS.class);
		add(Philippines.class, NationChina.class);
		add(Philippines.class, NationUS.class);
		add(Brunei.class, NationChina.class);
		add(Brunei.class, NationUS.class);
		add(Indonesia.class, NationChina.class);
		add(Indonesia.class, NationUS.class);

	}

	public void add(Class<? extends Country> classCountry, Class<? extends Nation> classNation) {

		Country country = Map.INSTANCE.getCountry(classCountry);
		int size = country.getMapNationInfluenceEconomic().getValue(classNation).getListCubes()
				.getArrayList().getCapacity();

		Class<? extends Cube> classCube = null;

		if (classNation.equals(NationChina.class))
			classCube = CubeRed.class;
		else if (classNation.equals(NationUS.class))
			classCube = CubeBlue.class;

		try {

			for (int counter = 1; counter <= size; counter++)
				Map.INSTANCE.getCountry(classCountry).getMapNationInfluenceEconomic()
						.getValue(classNation).getListCubes().getArrayList()
						.addLast(classCube.getConstructor().newInstance());

			for (int counter = 1; counter <= size; counter++)
				Map.INSTANCE.getCountry(classCountry).getMapNationInfluenceDiplomatic()
						.getValue(classNation).getListCubes().getArrayList()
						.addLast(classCube.getConstructor().newInstance());

			Map.INSTANCE.getCountry(classCountry).getMapNationInfluenceEconomic()
					.getValue(classNation).getListCubes().animateAsynchronous();

			Map.INSTANCE.getCountry(classCountry).getMapNationInfluenceDiplomatic()
					.getValue(classNation).getListCubes().animateAsynchronous();

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}

}
