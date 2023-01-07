package nations;

import components.Cube;
import managers.Credentials;
import utils.Enums.RearrangeTypeEnum;
import utils.Enums.RelocateTypeEnum;
import utils.ListImageViewAbles;
import utils.Vector2;

public abstract class Nation {

	protected ListImageViewAbles<Cube> available = new ListImageViewAbles<>();
	protected ListImageViewAbles<Cube> reserve = new ListImageViewAbles<>();
	protected ListImageViewAbles<Cube> politicalWarfare = new ListImageViewAbles<>();

	public Nation() {

		createLists();

	}

	private void createLists() {

		double x, y;

		// available

		x = getCoordinatesAvailable().x;
		y = getCoordinatesAvailable().y;
		x += Credentials.INSTANCE.cMap.x;
		y += Credentials.INSTANCE.cMap.y;

		this.available.getListCredentials().coordinatesList = new Vector2(x, y);
		this.available.getListCredentials().objectsPerRow = 3;
		this.available.getListCredentials().rearrangeTypeEnum = RearrangeTypeEnum.PIVOT;
		this.available.getListCredentials().relocateTypeEnum = RelocateTypeEnum.CENTER;

		// reserve

		x = getCoordinatesReserve().x;
		y = getCoordinatesReserve().y;
		x += Credentials.INSTANCE.cMap.x;
		y += Credentials.INSTANCE.cMap.y;

		this.reserve.getListCredentials().coordinatesList = new Vector2(x, y);
		this.reserve.getListCredentials().objectsPerRow = 3;
		this.reserve.getListCredentials().rearrangeTypeEnum = RearrangeTypeEnum.PIVOT;
		this.reserve.getListCredentials().relocateTypeEnum = RelocateTypeEnum.CENTER;

		// political warfare

		x = getCoordinatesPoliticalWarfare().x;
		y = getCoordinatesPoliticalWarfare().y;
		x += Credentials.INSTANCE.cMap.x;
		y += Credentials.INSTANCE.cMap.y;

		this.politicalWarfare.getListCredentials().coordinatesList = new Vector2(x, y);
		this.politicalWarfare.getListCredentials().gapBetweenComponents.x = 32;
		this.politicalWarfare.getArrayList().setCapacity(3);
		this.politicalWarfare.getListCredentials().relocateTypeEnum = RelocateTypeEnum.CENTER;

	}

	public final ListImageViewAbles<Cube> getAvailable() {
		return this.available;
	}

	public final ListImageViewAbles<Cube> getReserve() {
		return this.reserve;
	}

	public final ListImageViewAbles<Cube> getPoliticalWarfare() {
		return this.politicalWarfare;
	}

	protected abstract Class<? extends Cube> getClassCubeColor();

	protected abstract Vector2 getCoordinatesAvailable();

	protected abstract Vector2 getCoordinatesReserve();

	protected abstract Vector2 getCoordinatesPoliticalWarfare();

}
