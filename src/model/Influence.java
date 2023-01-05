package model;

import cubes.Cube;
import utils.Enums.DirectionEnum;
import utils.Enums.RelocateTypeEnum;
import utils.ListImageViewAbles;
import utils.Vector2;

public class Influence {

	private ListImageViewAbles<Cube> listCubes = new ListImageViewAbles<>();

	public Influence(int size, Vector2 coordinatesFirstCube,
			DirectionEnum directionEnumHorizontal) {

		this.listCubes.getArrayList().setCapacity(size);
		this.listCubes.getListCredentials().coordinatesList = coordinatesFirstCube;
		this.listCubes.getListCredentials().directionEnumHorizontal = directionEnumHorizontal;
		this.listCubes.getListCredentials().relocateTypeEnum = RelocateTypeEnum.CENTER;
		this.listCubes.getListCredentials().gapBetweenComponents.x = 31;

	}

	public final ListImageViewAbles<Cube> getListCubes() {
		return this.listCubes;
	}

}
