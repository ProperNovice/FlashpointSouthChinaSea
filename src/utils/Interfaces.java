package utils;

import managers.Credentials;
import utils.Enums.MapImageViews;

public class Interfaces {

	public interface IMouseEventAble {

		public default void handleMousePressedPrimary() {

		};

		public default void handleMousePressedSecondary() {

		};

		public default void handleMouseEntered() {

		};

		public default void handleMouseExited() {

		};

		public default void handleMouseDragged() {

		};

		public default void handleMouseReleased() {

		};

	}

	public interface ISaveLoadStateAble {

		public default void saveOriginal() {
			Logger.INSTANCE.logNewLine("save original not implemented");
		}

		public default void loadOriginal() {
			Logger.INSTANCE.logNewLine("load original not implemented");
		}

		public default void saveState() {
			Logger.INSTANCE.logNewLine("save state not implemented");
		}

		public default void loadState() {
			Logger.INSTANCE.logNewLine("load state not implemented");
		}

	}

	public interface IImageViewAble {

		public default ImageView getImageView() {
			return MapImageViews.INSTANCE.getImageViewsMap().getValue(this);
		}

		public default void reverseSelectImageView() {
			SelectImageViewManager.INSTANCE.reverseSelectImageViewAble(this);
		}

		public default boolean isSelected() {
			return SelectImageViewManager.INSTANCE.isSelectedImageViewAble(this);
		}

		public default void setSelected() {
			SelectImageViewManager.INSTANCE.addSelectImageViewAble(this);
		}

	}

	public interface ISelectCoordinatesAble {

		public Vector2 getSelectCoordinates();

		public default double getSelectDimension() {
			return Credentials.INSTANCE.selectEventHandlerAbleDimension;
		}

		public default void reverseSelectCoordinates() {
			SelectImageViewManager.INSTANCE.reverseSelectCoordinates((IMouseEventAble) this);
		}

		public default boolean isSelected() {
			return SelectImageViewManager.INSTANCE.isSelectedCoordinates((IMouseEventAble) this);
		}

		public default void setSelected() {
			SelectImageViewManager.INSTANCE.addSelectCoordinates((IMouseEventAble) this);
		}

	}

	public interface IUpdateAble {

		public void update();

	}

	public interface INode {

		public void relocateTopLeft(double x, double y);

		public void relocateTopLeft(Vector2 numbersPair);

		public double getCoordinatesTopLeftX();

		public double getCoordinatesTopLeftY();

		public void toFront();

		public void toBack();

		public Vector2 getCoordinatesTopLeft();

		public double getWidth();

		public double getHeight();

		public void setVisible(boolean value);

		public boolean isVisible();

	}

}
