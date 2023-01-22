package gameStates;

import enums.EText;
import tensions.Tension;
import tensions.TensionLow;

public class TensionToLow extends TensionToLevelSet {

	@Override
	protected EText getEText() {
		return EText.TENSION_TO_LOW;
	}

	@Override
	protected Class<? extends Tension> getTensionClass() {
		return TensionLow.class;
	}

}
