package model;

import components.Disk;
import managers.Credentials;
import nations.Nation;
import nations.NationChina;
import nations.NationUS;
import utils.Animation;
import utils.Enums.AnimationSynchEnum;
import utils.Enums.DirectionEnum;
import utils.HashMap;
import utils.Vector2;

public enum VictoryPointTracker {

	INSTANCE;

	private Disk disk = new Disk();
	private Vector2 zero = null;
	private HashMap<Class<? extends Nation>, HashMap<Integer, Vector2>> coordinates = new HashMap<>();
	private int score = 0;
	private Class<? extends Nation> nationWinning = null;
	private double gap = 45.35;

	private VictoryPointTracker() {

		this.coordinates.put(NationChina.class, new HashMap<>());
		this.coordinates.put(NationUS.class, new HashMap<>());

		createTracker();

		this.disk.getImageView().relocateCenter(this.zero);

	}

	public void addScore(Class<? extends Nation> classNation, int points) {

		// calculating score and nation winning

		if (this.score == 0 || this.nationWinning.equals(classNation))
			this.score += points;
		else
			this.score -= points;

		if (this.score == 0)
			this.nationWinning = null;

		else if (this.score < 0) {

			this.score = Math.abs(this.score);
			this.nationWinning = classNation;

		} else
			this.nationWinning = classNation;

		this.score = Math.min(this.score, 15);

		System.out.println(this.nationWinning);
		System.out.println(this.score);

		// relocation disk

		Vector2 vector2 = null;

		if (this.score == 0)
			vector2 = this.zero;
		else
			vector2 = this.coordinates.getValue(this.nationWinning).getValue(this.score);

		Animation.INSTANCE.animateCenter(this.disk, vector2, AnimationSynchEnum.ASYNCHRONOUS);

	}

	private void createTracker() {

		this.zero = new Vector2(510, 1264).addVector2(Credentials.INSTANCE.cMap);
		this.coordinates.getValue(NationUS.class).put(1, this.zero.addX(this.gap));
		this.coordinates.getValue(NationChina.class).put(1, this.zero.substractX(this.gap));

		addCoordinates(NationChina.class, 1, 10, DirectionEnum.LEFT);
		addCoordinates(NationChina.class, 10, 15, DirectionEnum.UP);
		addCoordinates(NationUS.class, 1, 10, DirectionEnum.RIGHT);
		addCoordinates(NationUS.class, 10, 15, DirectionEnum.UP);

	}

	private void addCoordinates(Class<? extends Nation> classNation, int lastKnownCoordinate,
			int coordinateNumberUpToCreate, DirectionEnum directionEnum) {

		HashMap<Integer, Vector2> map = this.coordinates.getValue(classNation);

		for (int counter = lastKnownCoordinate
				+ 1; counter <= coordinateNumberUpToCreate; counter++) {

			Vector2 lastCoordinate = map.getValue(counter - 1);
			Vector2 newCoordinate = null;

			if (directionEnum.equals(DirectionEnum.RIGHT))
				newCoordinate = lastCoordinate.addX(this.gap);
			else if (directionEnum.equals(DirectionEnum.LEFT))
				newCoordinate = lastCoordinate.substractX(this.gap);
			else if (directionEnum.equals(DirectionEnum.UP))
				newCoordinate = lastCoordinate.substractY(this.gap);

			map.put(counter, newCoordinate);

		}

	}

}
