package contestedIslands;

import managers.Credentials;
import model.Influence;
import model.MapPosition;
import nations.Nation;
import nations.NationChina;
import nations.NationUS;
import utils.Enums.DirectionEnum;
import utils.HashMap;
import utils.Vector2;

public abstract class ContestedIsland {

	private HashMap<Class<? extends Nation>, Influence> nationInfluence = new HashMap<>();
	private MapPosition mapPosition = null;

	public ContestedIsland() {
		setUpInfluence();
	}

	private void setUpInfluence() {

		double x = getCenterCoordinates().x;
		double y = getCenterCoordinates().y;

		x += Credentials.INSTANCE.cMap.x;
		y += Credentials.INSTANCE.cMap.y;

		this.mapPosition = new MapPosition(new Vector2(x, y), () -> handleMapPositionPressed());

		this.nationInfluence.put(NationUS.class,
				new Influence(getInfluenceSize(), new Vector2(x - 34.5, y), DirectionEnum.LEFT));

		this.nationInfluence.put(NationChina.class,
				new Influence(getInfluenceSize(), new Vector2(x + 34.5, y), DirectionEnum.RIGHT));

	}

	public final HashMap<Class<? extends Nation>, Influence> getNationInfluence() {
		return this.nationInfluence;
	}

	private void handleMapPositionPressed() {

	}

	public final MapPosition getMapPosition() {
		return this.mapPosition;
	}

	protected abstract Vector2 getCenterCoordinates();

	protected abstract int getInfluenceSize();

}
