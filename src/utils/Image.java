package utils;

public class Image {

	private javafx.scene.image.Image image = null;
	private String filePath = "";

	public Image(String filePath) {

		this.filePath = filePath;
		this.image = new javafx.scene.image.Image("/images/" + this.filePath);

	}

	public String getFilePath() {
		return this.filePath;
	}

	public javafx.scene.image.Image getImageFX() {
		return this.image;
	}

}
