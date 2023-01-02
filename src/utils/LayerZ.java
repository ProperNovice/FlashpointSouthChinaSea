package utils;

import enums.ELayerZ;
import utils.Interfaces.IImageViewAble;
import utils.Interfaces.IUpdateAble;

public enum LayerZ implements IUpdateAble {

	INSTANCE;

	private HashMap<ELayerZ, ArrayList<ImageView>> mapLayerZ = new HashMap<>();
	private HashMap<ImageView, javafx.scene.image.ImageView> listImageViewsFX = new HashMap<>();
	private ArrayList<TextIndicator> textIndicators = new ArrayList<>();

	private LayerZ() {

		for (ELayerZ eLayerZ : ELayerZ.values())
			this.mapLayerZ.put(eLayerZ, new ArrayList<ImageView>());

	}

	public void addImageViewAbleToLayer(ImageView imageView, ELayerZ eLayerZ,
			javafx.scene.image.ImageView imageViewFX) {

		this.mapLayerZ.getValue(eLayerZ).addLast(imageView);
		this.listImageViewsFX.put(imageView, imageViewFX);

	}

	public void addTextIndicator(TextIndicator textIndicator) {
		this.textIndicators.addLast(textIndicator);
	}

	public void toFrontImageview(ImageView imageView) {

		ArrayList<ImageView> list = getListContainingImageViewAble(imageView);
		list.remove(imageView);
		list.addLast(imageView);
		toFront();

	}

	public void toBackImageview(ImageView imageView) {

		ArrayList<ImageView> list = getListContainingImageViewAble(imageView);
		list.remove(imageView);
		list.addFirst(imageView);
		toFront();

	}

	private ArrayList<ImageView> getListContainingImageViewAble(ImageView imageView) {

		for (ELayerZ eLayerZ : ELayerZ.values()) {

			if (eLayerZ.equals(ELayerZ.VISIBILITY_FALSE))
				continue;

			ArrayList<ImageView> list = this.mapLayerZ.getValue(eLayerZ);

			if (!list.contains(imageView))
				continue;

			return list;

		}

		ShutDown.INSTANCE.execute();

		return null;

	}

	private void toFront() {
		AnimationTimerFX.INSTANCE.updateNextFrame(this);
	}

	public void setVisible(boolean value, ImageView imageView) {

		ArrayList<ImageView> list = this.mapLayerZ.getValue(ELayerZ.VISIBILITY_FALSE);

		if (value)
			if (list.contains(imageView))
				list.remove(imageView);

		if (!value)
			if (!list.contains(imageView))
				list.addFirst(imageView);

		toFront();

	}

	public ELayerZ getImageViewELayerZ(IImageViewAble imageViewAble) {

		ELayerZ eLayerZ = null;

		ImageView imageView = imageViewAble.getImageView();

		for (ELayerZ eLayerZTemp : this.mapLayerZ)
			if (this.mapLayerZ.getValue(eLayerZTemp).contains(imageView))
				eLayerZ = eLayerZTemp;

		return eLayerZ;

	}

	@Override
	public void update() {

		for (ELayerZ eLayerZ : ELayerZ.values())

			if (eLayerZ.equals(ELayerZ.TEXT))
				for (TextIndicator textIndicator : this.textIndicators)
					textIndicator.toFront();

			else
				for (ImageView imageView : this.mapLayerZ.getValue(eLayerZ)) {

					if (this.mapLayerZ.getValue(ELayerZ.VISIBILITY_FALSE).contains(imageView))
						continue;

					this.listImageViewsFX.getValue(imageView).toFront();

				}

	}

}
