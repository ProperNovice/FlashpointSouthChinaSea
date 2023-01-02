package managers;

import interfaces.IObserver;

public enum Observers implements IObserver {

	INSTANCE,

	;

	private Observers() {
		ObserverManager.INSTANCE.registerIObserver(this);
	}

	public final void enable() {
		ObserverManager.INSTANCE.enableIObserver(this);
	}

	public final void disable() {
		ObserverManager.INSTANCE.disableIObserver(this);
	}

}
