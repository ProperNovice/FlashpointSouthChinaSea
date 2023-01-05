package model;

import nations.NationChina;
import nations.NationUS;

public enum NationsManager {

	INSTANCE;

	private NationUS nationUS = null;
	private NationChina nationChina = null;

	private NationsManager() {

		this.nationUS = new NationUS();
		this.nationChina = new NationChina();

	}

	public NationUS getNationUS() {
		return this.nationUS;
	}

	public NationChina getNationChina() {
		return this.nationChina;
	}

}
