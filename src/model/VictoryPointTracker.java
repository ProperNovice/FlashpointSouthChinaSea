package model;

import components.Disk;
import managers.Credentials;
import nations.Nation;
import nations.NationChina;
import nations.NationUS;
import utils.Enums.DirectionEnum;
import utils.HashMap;
import utils.Vector2;

public enum VictoryPointTracker {

	INSTANCE;

	private Disk disk = new Disk();
	private Vector2 zero = null;
	private HashMap<Class<? extends Nation>, HashMap<Integer, Vector2>> coordinates = new HashMap<>();
	private int currentScore = 0;
	private Class<? extends Nation> currentWinner = null;
	private double gap = 46;

	private VictoryPointTracker() {

		this.coordinates.put(NationChina.class, new HashMap<>());
		this.coordinates.put(NationUS.class, new HashMap<>());

		createTracker();

	}

	private void createTracker() {

		this.zero = new Vector2(510, 1264).addVector2(Credentials.INSTANCE.cMap);
		this.coordinates.getValue(NationChina.class).put(1, this.zero.addX(this.gap));
		this.coordinates.getValue(NationChina.class).put(1, this.zero.addX(this.gap));

	}

	private void addCoordinates(Class<? extends Nation> classNation, int lastKnownCoordinate,
			int coortinateNumberUpToCreate, DirectionEnum directionEnum) {

	}

}
