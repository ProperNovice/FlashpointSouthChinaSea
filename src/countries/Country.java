package countries;

import managers.Credentials;
import model.Influence;
import nations.Nation;
import nations.NationChina;
import nations.NationUS;
import utils.Enums.DirectionEnum;
import utils.HashMap;
import utils.Vector2;

public abstract class Country {

	private HashMap<Class<? extends Nation>, Influence> nationInfluenceEconomic = new HashMap<>();
	private HashMap<Class<? extends Nation>, Influence> nationInfluenceDiplomatic = new HashMap<>();

	public Country() {
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

		this.nationInfluenceEconomic.put(NationUS.class,
				new Influence(getInfluenceSize(), new Vector2(x - 56.5, y), DirectionEnum.LEFT));

		this.nationInfluenceEconomic.put(NationChina.class,
				new Influence(getInfluenceSize(), new Vector2(x + 56.5, y), DirectionEnum.RIGHT));

		// influence diplomatic

		x = getCenterCoordinates().x;
		y = getCenterCoordinates().y;

		x += Credentials.INSTANCE.cMap.x;
		y += Credentials.INSTANCE.cMap.y;

		y += 22.5;

		this.nationInfluenceDiplomatic.put(NationUS.class,
				new Influence(getInfluenceSize(), new Vector2(x - 56.5, y), DirectionEnum.LEFT));

		this.nationInfluenceDiplomatic.put(NationChina.class,
				new Influence(getInfluenceSize(), new Vector2(x + 56.5, y), DirectionEnum.RIGHT));

	}

	public final HashMap<Class<? extends Nation>, Influence> getNationInfluenceEconomic() {
		return this.nationInfluenceEconomic;
	}

	public final HashMap<Class<? extends Nation>, Influence> getNationInfluenceDiplomatic() {
		return this.nationInfluenceDiplomatic;
	}

	protected abstract Vector2 getCenterCoordinates();

	protected abstract int getInfluenceSize();

}
