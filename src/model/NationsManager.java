package model;

import nations.Nation;
import nations.NationChina;
import nations.NationUS;
import utils.ShutDown;

public enum NationsManager {

	INSTANCE;

	private NationUS nationUS = null;
	private NationChina nationChina = null;

	private NationsManager() {

		this.nationUS = new NationUS();
		this.nationChina = new NationChina();

	}

	public Nation getNation(Class<? extends Nation> classNation) {

		if (this.nationChina.getClass().equals(classNation))
			return this.nationChina;

		else if (this.nationUS.getClass().equals(classNation))
			return this.nationUS;

		ShutDown.INSTANCE.execute();
		return null;

	}

}
