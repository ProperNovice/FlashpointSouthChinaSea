package enums;

import utils.Enums.TextTypeEnum;
import utils.Text;

public enum EText {

	CANCEL("Cancel", TextTypeEnum.OPTION),
	CONTINUE("Continue", TextTypeEnum.OPTION),
	RESTART("Restart", TextTypeEnum.OPTION),
	START_GAME("Start game", TextTypeEnum.OPTION),
	VOID("", TextTypeEnum.INDICATOR),
	YOU_LOST("You lost", TextTypeEnum.INDICATOR),
	YOU_WON("You won", TextTypeEnum.INDICATOR),
	SELECT_TENSION_LEVEL("Select tension level", TextTypeEnum.INDICATOR),

	;

	private TextTypeEnum textTypeEnum = null;
	private String string = null;

	private EText(String string, TextTypeEnum textTypeEnum) {

		this.string = string;
		this.textTypeEnum = textTypeEnum;

	}

	public void show() {
		Text.INSTANCE.showText(this, getString());
	}

	public void showAdditionally(String string) {
		Text.INSTANCE.showText(this, getString() + string);
	}

	public void showAdditionally(int integer) {
		showAdditionally(Integer.toString(integer));
	}

	public void showInstead(String string) {
		Text.INSTANCE.showText(this, string);
	}

	public String getString() {
		return this.string;
	}

	public TextTypeEnum getTextTypeEnum() {
		return this.textTypeEnum;
	}

}
