package utils;

import java.lang.reflect.InvocationTargetException;

import gameStatesDefault.AGameState;
import managers.ObserverManager;

public enum Flow {

	INSTANCE;

	private ArrayList<Class<? extends AGameState>> flow = new ArrayList<>();
	private AGameState gameStateCurrent = null;

	private Flow() {

	}

	public void proceed() {

		if (this.flow.isEmpty()) {
			Logger.INSTANCE.logNewLine("flow is empty");
			return;
		}

		ObserverManager.INSTANCE.fireEventGameStateChange();

		Class<? extends AGameState> aGameStateClass = this.flow.removeFirst();

		Logger.INSTANCE.log("executing gamestate");
		Logger.INSTANCE.logNewLine(aGameStateClass.getSimpleName());

		this.gameStateCurrent = getGameState(aGameStateClass);
		this.gameStateCurrent.execute();

	}

	public void executeGameState(Class<? extends AGameState> gameStateClass) {

		this.flow.addFirst(gameStateClass);
		proceed();

	}

	public ArrayList<Class<? extends AGameState>> getFlow() {
		return this.flow;
	}

	public AGameState getGameStateCurrent() {
		return this.gameStateCurrent;
	}

	public void print() {

		Logger.INSTANCE.logNewLine("-> printing flow started");

		for (Class<? extends AGameState> classGameState : this.flow)
			Logger.INSTANCE.log(classGameState.getSimpleName());

		Logger.INSTANCE.newLine();
		Logger.INSTANCE.logNewLine("<- printing flow ended");

	}

	private AGameState getGameState(Class<? extends AGameState> gameStateClass) {

		try {

			return gameStateClass.getConstructor().newInstance();

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {

			e.printStackTrace();
			ShutDown.INSTANCE.execute();
			return null;

		}

	}

}
