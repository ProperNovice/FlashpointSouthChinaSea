package interfaces;

import managers.ObserverManager;
import utils.Interfaces.IImageViewAble;
import utils.ListImageViewAbles;

public interface IObserver {

	public default void fireEventObjectAddedToList(ListImageViewAbles<IImageViewAble> list,
			IImageViewAble object) {

	}

	public default void fireEventObjectRemovedFromList(ListImageViewAbles<IImageViewAble> list,
			IImageViewAble object) {

	}

	public default void fireEventGameStateChange() {

	}

	public default void fireEventListIsMaxCapacity(ListImageViewAbles<IImageViewAble> list) {

	}

	public default void fireEventListIsOverCapacity(ListImageViewAbles<IImageViewAble> list) {

	}

	public default void enable() {
		ObserverManager.INSTANCE.enableIObserver(this);
	}

	public default void disable() {
		ObserverManager.INSTANCE.disableIObserver(this);
	}

}
