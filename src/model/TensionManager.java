package model;

import tensions.Tension;
import tensions.TensionCritical;
import tensions.TensionHigh;
import tensions.TensionLow;
import tensions.TensionMedium;
import utils.ArrayList;

public enum TensionManager {

	INSTANCE;

	private ArrayList<Tension> list = new ArrayList<>();
	private Tension tensionCurrent = null;

	private TensionManager() {

		this.list.addLast(new TensionLow());
		this.list.addLast(new TensionMedium());
		this.list.addLast(new TensionHigh());
		this.list.addLast(new TensionCritical());

		this.tensionCurrent = this.list.get(0);

	}

}
