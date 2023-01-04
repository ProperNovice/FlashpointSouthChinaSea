package countries;

import influence.InfluenceDiplomatic;
import influence.InfluenceEconomic;
import managers.Credentials;
import nations.Nation;
import nations.NationChina;
import nations.NationUS;
import utils.Enums.DirectionEnum;
import utils.HashMap;
import utils.Vector2;

public abstract class Country {

	private HashMap<Class<? extends Nation>, InfluenceEconomic> mapNationInfluenceEconomic = null;
	private HashMap<Class<? extends Nation>, InfluenceDiplomatic> mapNationInfluenceDiplomatic = null;

	public Country() {

		this.mapNationInfluenceEconomic = new HashMap<>();
		this.mapNationInfluenceDiplomatic = new HashMap<>();

		setUpInfluence();

	}

	private void setUpInfluence() {

		double x, y;

		// influence economic

		x = getCenterCoordinates().x;
		y = getCenterCoordinates().y;

		x += Credentials.INSTANCE.cMap.x;
		y += Credentials.INSTANCE.cMap.y;

		y -= 22.5;

		this.mapNationInfluenceEconomic.put(NationUS.class, new InfluenceEconomic(
				getInfluenceSize(), new Vector2(x - 56.5, y), DirectionEnum.LEFT));

		this.mapNationInfluenceEconomic.put(NationChina.class, new InfluenceEconomic(
				getInfluenceSize(), new Vector2(x + 56.5, y), DirectionEnum.RIGHT));

		// influence diplomatic

		x = getCenterCoordinates().x;
		y = getCenterCoordinates().y;

		x += Credentials.INSTANCE.cMap.x;
		y += Credentials.INSTANCE.cMap.y;

		y += 22.5;

		this.mapNationInfluenceDiplomatic.put(NationUS.class, new InfluenceDiplomatic(
				getInfluenceSize(), new Vector2(x - 56.5, y), DirectionEnum.LEFT));

		this.mapNationInfluenceDiplomatic.put(NationChina.class, new InfluenceDiplomatic(
				getInfluenceSize(), new Vector2(x + 56.5, y), DirectionEnum.RIGHT));

	}

	public final HashMap<Class<? extends Nation>, InfluenceEconomic> getMapNationInfluenceEconomic() {
		return this.mapNationInfluenceEconomic;
	}

	public final HashMap<Class<? extends Nation>, InfluenceDiplomatic> getMapNationInfluenceDiplomatic() {
		return this.mapNationInfluenceDiplomatic;
	}

	protected abstract Vector2 getCenterCoordinates();

	protected abstract int getInfluenceSize();

}
