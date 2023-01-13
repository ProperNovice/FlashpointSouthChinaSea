package model;

import java.lang.reflect.InvocationTargetException;

import components.Disk;
import managers.Credentials;
import tensions.Tension;
import tensions.TensionCritical;
import tensions.TensionHigh;
import tensions.TensionLow;
import tensions.TensionMedium;
import utils.Animation;
import utils.ArrayList;
import utils.Enums.AnimationSynchEnum;
import utils.Flow;
import utils.HashMap;
import utils.Vector2;

public enum TensionManager {

	INSTANCE;

	private ArrayList<Tension> list = new ArrayList<>();
	private HashMap<Class<? extends Tension>, Vector2> tensionCoordinates = new HashMap<>();
	private HashMap<Tension, MapPosition> tensionMapPositions = new HashMap<>();
	private Tension tensionCurrent = null;
	private Vector2 vector2TensionLow = new Vector2(489, 117);
	private double gapBetweenTensions = 55.3;
	private Disk disk = new Disk();

	private TensionManager() {

		createTensionCoordinates(TensionLow.class);
		createTensionCoordinates(TensionMedium.class);
		createTensionCoordinates(TensionHigh.class);
		createTensionCoordinates(TensionCritical.class);

		setTensionRelocate(TensionLow.class);

	}

	public void showTensionMapPositions() {

		for (Tension tension : this.tensionMapPositions)
			this.tensionMapPositions.getValue(tension).setSelected();

	}

	public void hideTensionMapPositions() {

		for (Tension tension : this.tensionMapPositions)
			this.tensionMapPositions.getValue(tension).releaseSelected();

	}

	private void handleTensionMapPositionPressed(Tension tension) {
		Flow.INSTANCE.getGameStateCurrent().handleTensionPressed(tension);
	}

	public Tension getCurrentTension() {
		return this.tensionCurrent;
	}

	private void setTensionRelocate(Class<? extends Tension> classTension) {

		setTensionObject(classTension);
		this.disk.getImageView().relocateCenter(this.tensionCoordinates.getValue(classTension));

	}

	public void setTensionAnimate(Class<? extends Tension> classTension) {

		setTensionObject(classTension);
		Animation.INSTANCE.animateCenter(this.disk, this.tensionCoordinates.getValue(classTension),
				AnimationSynchEnum.ASYNCHRONOUS);

	}

	private void setTensionObject(Class<? extends Tension> classTension) {

		for (Tension tension : this.list) {

			if (!tension.getClass().equals(classTension))
				continue;

			this.tensionCurrent = tension;
			return;

		}

	}

	private void createTensionCoordinates(Class<? extends Tension> classTension) {

		try {

			final Tension tension = classTension.getConstructor().newInstance();
			this.list.addLast(tension);

			int index = this.list.size() - 1;

			double x, y;

			x = Credentials.INSTANCE.cMap.x;
			x += this.vector2TensionLow.x;
			x += index * this.gapBetweenTensions;

			y = Credentials.INSTANCE.cMap.y;
			y += this.vector2TensionLow.y;

			Vector2 vector2 = new Vector2(x, y);

			this.tensionCoordinates.put(classTension, vector2);
			this.tensionMapPositions.put(tension,
					new MapPosition(vector2, () -> handleTensionMapPositionPressed(tension)));

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}

}
