package components;

import enums.ELayerZ;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;

public class Disk implements IImageViewAble {

	public Disk() {

		String filePath = "disk.png";
		new ImageView(filePath, ELayerZ.DISK, this);

	}

}
