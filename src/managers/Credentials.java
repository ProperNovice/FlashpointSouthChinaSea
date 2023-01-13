package managers;

import utils.Enums.RearrangeTypeEnum;
import utils.Vector2;

public enum Credentials {

	INSTANCE;

	public final String primaryStageTitle = "JavaFX", numbersImageViewColor = "black";
	public final boolean colliderVisibility = true;
	public final double gapBetweenBorders = 25, textHeight = 50,
			selectEventHandlerAbleDimension = 39, animationStep = 4;
	public Vector2 dFrame, dGapBetweenComponents, dGapBetweenComponentsLineCast;
	public Vector2 cTextPanel, cImageViewIndicator;
	public RearrangeTypeEnum rearrangeTypeEnumText = RearrangeTypeEnum.LINEAR;

	public Vector2 cMap;
	public double cardWidth;

	private Credentials() {

		double x = 0, y = 0;

		this.dFrame = new Vector2(1920, 1368);
		this.dGapBetweenComponents = new Vector2(4, 4);
		this.dGapBetweenComponentsLineCast = this.dGapBetweenComponents;

		// c text panel

		this.cTextPanel = new Vector2(1050, 25);

		// c image view indicator

		x = this.gapBetweenBorders;
		y = this.gapBetweenBorders;
		this.cImageViewIndicator = new Vector2(x, y);

		// c map

		x = this.gapBetweenBorders;
		this.cMap = new Vector2(x, x);

		// d card

		x = this.dFrame.x;
		x -= 2 * this.gapBetweenBorders;
		x -= 1018;
		x -= 3 * this.dGapBetweenComponents.x;
		x /= 3;
		this.cardWidth = x;

	}

}
