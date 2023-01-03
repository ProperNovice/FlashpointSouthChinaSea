package cubes;

import enums.ELayerZ;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;

public abstract class Cube implements IImageViewAble {

	public Cube() {

		String filePath = "cubes/";
		filePath += getFileName();
		filePath += ".png";

		new ImageView(filePath, ELayerZ.CUBES, this);

	}

	protected abstract String getFileName();

}
