package cards;

import enums.ELayerZ;
import managers.Credentials;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;
import utils.Interfaces.IMouseEventAble;

public abstract class Card implements IImageViewAble, IMouseEventAble {

	public Card() {

		String cardNumber = this.getClass().getSimpleName();
		cardNumber = cardNumber.substring(4);

		String fileName = "cards/";
		fileName += cardNumber;
		fileName += ".png";

		new ImageView(fileName, ELayerZ.CARDS, this);
		getImageView().setWidth(Credentials.INSTANCE.cardWidth);

	}

	public void print() {

	}

}
