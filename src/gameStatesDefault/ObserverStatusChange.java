package gameStatesDefault;

import managers.ObserverManager;
import utils.Flow;
import utils.ShutDown;

public class ObserverStatusChange extends GameState {

	@Override
	public void execute() {

		if (ObserverManager.INSTANCE.observerStatusChange.isEmpty())
			ShutDown.INSTANCE.execute();

		while (!ObserverManager.INSTANCE.observerStatusChange.isEmpty())
			ObserverManager.INSTANCE.observerStatusChange.removeFirst().run();

		Flow.INSTANCE.proceed();

	}

}
