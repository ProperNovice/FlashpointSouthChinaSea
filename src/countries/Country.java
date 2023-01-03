package countries;

import enums.ENation;
import influence.InfluenceDiplomatic;
import influence.InfluenceEconomic;
import managers.Credentials;
import utils.Enums.DirectionEnum;
import utils.HashMap;
import utils.Vector2;

public abstract class Country {

	private HashMap<ENation, InfluenceEconomic> mapNationInfluenceEconomic = null;
	private HashMap<ENation, InfluenceDiplomatic> mapNationInfluenceDiplomatic = null;

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
		
		System.out.println(x + " " + y);

		y -= 22.5;

		System.out.println(x + " " + y);

		this.mapNationInfluenceEconomic.put(ENation.US, new InfluenceEconomic(getInfluenceSize(), 2,
				new Vector2(x - 56.5, y), DirectionEnum.LEFT));

	}

	public final HashMap<ENation, InfluenceEconomic> getMapNationInfluenceEconomic() {
		return this.mapNationInfluenceEconomic;
	}

	public final HashMap<ENation, InfluenceDiplomatic> getMapNationInfluenceDeplomatic() {
		return this.mapNationInfluenceDiplomatic;
	}

	protected abstract Vector2 getCenterCoordinates();

	protected abstract int getInfluenceSize();

}
