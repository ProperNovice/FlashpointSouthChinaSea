package model;

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
import utils.ShutDown;
import utils.Vector2;

public enum TensionManager {

	INSTANCE;

	private ArrayList<Tension> list = new ArrayList<>();
	private Tension tensionCurrent = null;
	private Vector2 vector2TensionLow = new Vector2(489, 117);
	private double gapBetweenTensions = 55.3;
	private Disk disk = new Disk();

	private TensionManager() {

		this.list.addLast(new TensionLow());
		this.list.addLast(new TensionMedium());
		this.list.addLast(new TensionHigh());
		this.list.addLast(new TensionCritical());

		setTensionRelocate(TensionLow.class);

	}

	public Tension getCurrentTension() {
		return this.tensionCurrent;
	}

	public void setTensionRelocate(Class<? extends Tension> classTension) {
		setTension(classTension);

		this.disk.getImageView().relocateCenter(getCurrentTensionCoordinates());

	}

	public void setTensionAnimate(Class<? extends Tension> classTension) {

		setTension(classTension);
		Animation.INSTANCE.animateCenter(this.disk, getCurrentTensionCoordinates(),
				AnimationSynchEnum.ASYNCHRONOUS);

	}

	private void setTension(Class<? extends Tension> classTension) {

		for (Tension tension : this.list) {

			if (!tension.getClass().equals(classTension))
				continue;

			this.tensionCurrent = tension;
			return;

		}

	}

	private Vector2 getCurrentTensionCoordinates() {

		for (Tension tension : this.list) {

			if (!tension.equals(tensionCurrent))
				continue;

			int currentTensionIndex = this.list.indexOf(tension);

			double x, y;

			x = Credentials.INSTANCE.cMap.x;
			x += this.vector2TensionLow.x;
			x += currentTensionIndex * this.gapBetweenTensions;

			y = Credentials.INSTANCE.cMap.y;
			y += this.vector2TensionLow.y;

			return new Vector2(x, y);

		}

		ShutDown.INSTANCE.execute();
		return null;

	}

}
