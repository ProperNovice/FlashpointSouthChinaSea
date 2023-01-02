package managers;

import interfaces.IObserver;
import utils.ArrayList;
import utils.Interfaces.IImageViewAble;
import utils.ListImageViewAbles;
import utils.ShutDown;

public enum ObserverManager {

	INSTANCE;

	private ArrayList<IObserver> observers = new ArrayList<>();
	public ArrayList<Runnable> observerStatusChange = new ArrayList<>();

	private ObserverManager() {

	}

	public void fireEventObjectAddedToList(ListImageViewAbles<IImageViewAble> list,
			IImageViewAble object) {

		for (IObserver iObserver : this.observers)
			iObserver.fireEventObjectAddedToList(list, object);

	}

	public void fireEventObjectRemovedFromList(ListImageViewAbles<IImageViewAble> list,
			IImageViewAble object) {

		for (IObserver iObserver : this.observers)
			iObserver.fireEventObjectRemovedFromList(list, object);

	}

	public void fireEventGameStateChange() {

		// game state change

		for (IObserver iObserver : this.observers)
			iObserver.fireEventGameStateChange();

		// is max/over capacity

		for (ListImageViewAbles<IImageViewAble> list : Lists.INSTANCE.lists) {

			if (list.getArrayList().isMaxCapacity())
				fireEventListIsMaxCapacity(list);
			else if (list.getArrayList().isOverCapacity())
				fireEventListIsOverCapacity(list);

		}

	}

	private void fireEventListIsMaxCapacity(ListImageViewAbles<IImageViewAble> list) {

		for (IObserver iObserver : this.observers)
			iObserver.fireEventListIsMaxCapacity(list);

	}

	private void fireEventListIsOverCapacity(ListImageViewAbles<IImageViewAble> list) {

		for (IObserver iObserver : this.observers)
			iObserver.fireEventListIsOverCapacity(list);

	}

	public void registerIObserver(IObserver iObserver) {

		this.observers.addLast(iObserver);
		this.observers.saveOriginal();

	}

	public void enableIObserver(IObserver iObserver) {

		if (this.observers.contains(iObserver))
			ShutDown.INSTANCE.execute();

		this.observers.addLast(iObserver);

	}

	public void disableIObserver(IObserver iObserver) {

		if (!this.observers.contains(iObserver))
			ShutDown.INSTANCE.execute();

		this.observers.remove(iObserver);

	}

	public void enableAllIObservers() {
		this.observers.loadOriginal();
	}

	public void disableAllIObservers() {
		this.observers.clear();
	}

}
