package components;

import utils.ImageView;
import utils.Interfaces.IImageViewAble;

public class Disk implements IImageViewAble {

	public Disk() {

		String filePath = "disk.png";
		new ImageView(filePath, this);

	}

}
