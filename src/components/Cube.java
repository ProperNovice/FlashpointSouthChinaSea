package components;

import enums.ELayerZ;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;
import utils.Vector2;

public abstract class Cube implements IImageViewAble {

	public Cube() {

		String filePath = "cubes/";
		filePath += getFileName();
		filePath += ".png";

		new ImageView(filePath, ELayerZ.CUBES, this);
		getImageView().setDimensions(new Vector2(30, 30));

	}

	protected abstract String getFileName();

}
