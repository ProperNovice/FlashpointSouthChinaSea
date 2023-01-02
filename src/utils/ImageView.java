package utils;

import enums.ELayerZ;
import gui.InstancesGui;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import utils.Enums.MapImageViews;
import utils.Interfaces.IImageViewAble;
import utils.Interfaces.IMouseEventAble;
import utils.Interfaces.INode;

public class ImageView implements INode {

	private javafx.scene.image.ImageView imageView = null;
	private Image imageFront = null, imageBack = null, imageShowing = null;
	private double widthOriginal, heightOriginal, scale = 1, xClip = 0, yClip = 0, rotateValue = 0,
			selectImageViewAbleRatioDimensions = 0.5;
	private IMouseEventAble mouseEventAble = null;
	private boolean isVisible = true;
	private Vector2 selectImageViewAbleRatioPosition = new Vector2(0.5, 0.5);
	private MouseEvent mouseEvent = null;
	private ImageView imageViewClone = null;

	public ImageView(String filePath, Object object) {
		this(new Image(filePath), ELayerZ.DEFAULT, object);
	}

	public ImageView(String filePath, ELayerZ eLayerZ, Object object) {
		this(new Image(filePath), eLayerZ, object);
	}

	public ImageView(Image image, Object object) {
		this(image, ELayerZ.DEFAULT, object);
	}

	public ImageView(Image image, ELayerZ eLayerZ, Object object) {

		this.imageFront = image;

		this.imageView = new javafx.scene.image.ImageView(this.imageFront.getImageFX());
		InstancesGui.INSTANCE.getParent().addNode(this.imageView);

		this.imageShowing = this.imageFront;

		this.widthOriginal = this.imageView.minWidth(0);
		this.heightOriginal = this.imageView.minHeight(0);

		LayerZ.INSTANCE.addImageViewAbleToLayer(this, eLayerZ, this.imageView);
		LayerZ.INSTANCE.toFrontImageview(this);

		if (!(object instanceof IImageViewAble))
			return;

		MapImageViews.INSTANCE.getImageViewsMap().put((IImageViewAble) object, this);

		if (object instanceof IMouseEventAble)
			setMouseEventHandler((IMouseEventAble) object);

	}

	@Override
	public void setVisible(boolean value) {
		this.isVisible = value;
		LayerZ.INSTANCE.setVisible(this.isVisible, this);
	}

	public final boolean isVisible() {
		return this.isVisible;
	}

	@Override
	public void toBack() {
		LayerZ.INSTANCE.toBackImageview(this);
	}

	@Override
	public void toFront() {
		LayerZ.INSTANCE.toFrontImageview(this);
	}

	@Override
	public final double getCoordinatesTopLeftX() {
		return this.imageView.getLayoutX();
	}

	@Override
	public final double getCoordinatesTopLeftY() {
		return this.imageView.getLayoutY();
	}

	@Override
	public final Vector2 getCoordinatesTopLeft() {
		return new Vector2(getCoordinatesTopLeftX(), getCoordinatesTopLeftY());
	}

	public final double getCoordinatesCenterX() {
		return getCoordinatesTopLeftX() + this.getWidth() / 2;
	}

	public final double getCoordinatesCenterY() {
		return getCoordinatesTopLeftY() + this.getHeight() / 2;
	}

	public final Vector2 getCoordinatesCenter() {
		return new Vector2(getCoordinatesCenterX(), getCoordinatesCenterY());
	}

	@Override
	public void relocateTopLeft(final double x, final double y) {
		this.imageView.relocate(x - this.xClip, y - this.yClip);
	}

	public void relocateCenter(final double x, final double y) {
		relocateTopLeft(x - this.getWidth() / 2, y - this.getHeight() / 2);
	}

	public void relocateBottomLeft(final double x, final double y) {
		relocateTopLeft(x, y - this.getHeight());
	}

	@Override
	public void relocateTopLeft(final Vector2 vector2) {
		relocateTopLeft(vector2.x, vector2.y);
	}

	public void relocateCenter(final Vector2 vector2) {
		relocateCenter(vector2.x, vector2.y);
	}

	public void relocateBottomLeft(final Vector2 vector2) {
		relocateBottomLeft(vector2.x, vector2.y);
	}

	public boolean contains(double x, double y) {

		if (x < this.getCoordinatesTopLeftX())
			return false;

		if (x > this.getCoordinatesTopLeftX() + this.getWidth())
			return false;

		if (y < this.getCoordinatesTopLeftY())
			return false;

		if (y > this.getCoordinatesTopLeftY() + this.getHeight())
			return false;

		return true;

	}

	public boolean contains(Vector2 vector2) {
		return contains(vector2.x, vector2.y);
	}

	public final void setClip(double x, double y, double width, double height) {

		this.xClip = x;
		this.yClip = y;

		Rectangle rectangle = new Rectangle(x / scale, y / scale, width / scale, height / scale);
		this.imageView.setClip(rectangle);

	}

	public final void setRotate(double value) {
		this.rotateValue = value;
		this.imageView.setRotate(value);
	}

	public final boolean isRotated() {
		return this.rotateValue != 0;
	}

	protected final void setMouseEventHandler(IMouseEventAble object) {

		this.mouseEventAble = object;

		this.imageView.setOnMousePressed(new EventHandler(() -> {

			if (this.mouseEvent.getButton().equals(MouseButton.PRIMARY))
				this.mouseEventAble.handleMousePressedPrimary();

			else if (this.mouseEvent.getButton().equals(MouseButton.SECONDARY))
				this.mouseEventAble.handleMousePressedSecondary();

		}));

		this.imageView.setOnMouseEntered(
				new EventHandler(() -> this.mouseEventAble.handleMouseEntered()));

		this.imageView
				.setOnMouseExited(new EventHandler(() -> this.mouseEventAble.handleMouseExited()));

		this.imageView.setOnMouseDragged(
				new EventHandler(() -> this.mouseEventAble.handleMouseDragged()));

		this.imageView.setOnMouseReleased(
				new EventHandler(() -> this.mouseEventAble.handleMouseReleased()));

	}

	public void dragImageView() {

		double x = this.mouseEvent.getSceneX();
		double y = this.mouseEvent.getSceneY();

		relocateCenter(x, y);

	}

	public final void setImage(final Image image) {

		this.imageView.setImage(image.getImageFX());
		this.imageFront = image;

	}

	public final Image getImageShowing() {
		return this.imageShowing;
	}

	private void setScale(double scale) {

		this.scale = scale;

		this.imageView.setScaleX(this.scale);
		this.imageView.setScaleY(this.scale);

		double widthTotal = this.widthOriginal;
		double heightTotal = this.heightOriginal;

		double widthNew = this.scale * widthTotal;
		double heightNew = this.scale * heightTotal;

		double translateX = (widthNew - widthTotal) / 2;
		double translateY = (heightNew - heightTotal) / 2;

		this.imageView.setTranslateX(translateX);
		this.imageView.setTranslateY(translateY);

	}

	public void setWidth(double width) {

		double scale = width / this.widthOriginal;
		setScale(scale);

	}

	public void setHeight(double height) {

		double scale = height / this.heightOriginal;
		setScale(scale);

	}

	public void setDimensions(Vector2 numbersPair) {
		setWidth(numbersPair.x);
	}

	public void setBack(String path) {
		setBack(new Image(path));
	}

	public void setBack(Image image) {
		this.imageBack = image;
	}

	public void setSelectImageViewAbleRatioPosition(double x, double y) {
		setSelectImageViewAbleRatioPosition(new Vector2(x, y));
	}

	public void setSelectImageViewAbleRatioPosition(Vector2 vector2) {
		this.selectImageViewAbleRatioPosition = vector2;
	}

	public void setSelectImageViewAbleRatioDimensions(double value) {
		this.selectImageViewAbleRatioDimensions = value;
	}

	public void flip() {

		if (this.imageShowing.equals(this.imageFront))
			this.imageShowing = this.imageBack;
		else
			this.imageShowing = this.imageFront;

		setImageShowing();

	}

	public void flipFront() {
		this.imageShowing = this.imageFront;
		setImageShowing();
	}

	public void flipBack() {
		this.imageShowing = this.imageBack;
		setImageShowing();
	}

	public boolean isFlippedFront() {
		return this.imageShowing.equals(this.imageFront);
	}

	public boolean isFlippedBack() {
		return this.imageShowing.equals(this.imageBack);
	}

	private void setImageShowing() {
		this.imageView.setImage(this.imageShowing.getImageFX());
	}

	@Override
	public double getWidth() {
		return this.imageView.minWidth(0) * this.scale;
	}

	@Override
	public double getHeight() {
		return this.imageView.minHeight(0) * this.scale;
	}

	public Vector2 getDimenions() {
		return new Vector2(getWidth(), getHeight());
	}

	public double getScale() {
		return this.scale;
	}

	public Vector2 getEventOriginalCoordinates() {
		return new Vector2(this.mouseEvent.getX(), this.mouseEvent.getY());
	}

	public Vector2 getEventScaledCoordinates() {
		return new Vector2(this.mouseEvent.getX() * this.scale,
				this.mouseEvent.getY() * this.scale);
	}

	public IMouseEventAble getEventHandlerAble() {
		return this.mouseEventAble;
	}

	public Vector2 getSelectImageViewAbleRatioPosition() {
		return this.selectImageViewAbleRatioPosition;
	}

	public double getSelectImageViewAbleRatioDimensions() {
		return this.selectImageViewAbleRatioDimensions;
	}

	public ImageView getCloneImageView() {

		if (this.imageViewClone == null) {

			this.imageViewClone = new ImageView(this.imageFront, ELayerZ.PANEL, this);
			this.imageViewClone.setWidth(getWidth());
			this.imageViewClone.setMouseEventHandler(this.mouseEventAble);

		}

		return this.imageViewClone;

	}

	private class EventHandler implements javafx.event.EventHandler<MouseEvent> {

		private Runnable runnable = null;

		public EventHandler(Runnable runnable) {
			this.runnable = runnable;
		}

		@Override
		public void handle(MouseEvent event) {

			if (Animation.INSTANCE.isAnimatingSynchronous())
				return;

			mouseEvent = event;
			this.runnable.run();

		}

	}

}
