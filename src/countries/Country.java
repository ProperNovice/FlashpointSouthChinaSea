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
	private MapPosition mapPositionInfluenceEconomic, mapPositionInfluenceDiplomatic;

	public Country() {
		setUpInfluence();
	}

	public void enableInfluenceEconomicMapPosition() {
		this.mapPositionInfluenceEconomic.setSelected();
	}

	public void enableInfluenceDiplomaticMapPosition() {
		this.mapPositionInfluenceDiplomatic.setSelected();
	}

	public void disableInfluenceEconomicMapPosition() {
		this.mapPositionInfluenceEconomic.releaseSelected();
	}

	public void disableInfluenceDiplomaticMapPosition() {
		this.mapPositionInfluenceDiplomatic.releaseSelected();
	}

	private void setUpInfluence() {

		double x, y;

		// influence economic

		x = getCenterCoordinates().x;
		y = getCenterCoordinates().y;

		x += Credentials.INSTANCE.cMap.x;
		y += Credentials.INSTANCE.cMap.y;

		y -= 22.5;

		this.mapPositionInfluenceEconomic = new MapPosition(new Vector2(x, y),
				() -> handleNationInfluenceEconomicPressed());

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

		this.mapPositionInfluenceDiplomatic = new MapPosition(new Vector2(x, y),
				() -> handleNationInfluenceDiplomaticPressed());

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

	private void handleNationInfluenceEconomicPressed() {

	}

	private void handleNationInfluenceDiplomaticPressed() {

	}

	protected abstract Vector2 getCenterCoordinates();

	protected abstract int getInfluenceSize();

}
