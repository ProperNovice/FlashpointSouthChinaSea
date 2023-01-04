package model;

import java.lang.reflect.InvocationTargetException;

import countries.Brunei;
import countries.Country;
import countries.Indonesia;
import countries.Malaysia;
import countries.Philippines;
import countries.Vietnam;
import enums.ELayerZ;
import managers.Credentials;
import utils.ArrayList;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;
import utils.Interfaces.IMouseEventAble;
import utils.ShutDown;

public enum Map implements IImageViewAble, IMouseEventAble {

	INSTANCE;

	private ArrayList<Country> listCountries = new ArrayList<>();

	private Map() {

		new ImageView("map.png", ELayerZ.MAP, this);
		getImageView().relocateTopLeft(Credentials.INSTANCE.cMap);
		createCountries();

	}

	public Country getCountry(Class<? extends Country> classCountry) {

		for (Country country : this.listCountries)
			if (country.getClass().equals(classCountry))
				return country;

		ShutDown.INSTANCE.execute();
		return null;

	}

	private void createCountries() {

		addCountry(Vietnam.class);
		addCountry(Malaysia.class);
		addCountry(Philippines.class);
		addCountry(Brunei.class);
		addCountry(Indonesia.class);

	}

	private void addCountry(Class<? extends Country> classCountry) {

		try {

			this.listCountries.addLast(classCountry.getConstructor().newInstance());

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}

}
