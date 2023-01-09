package managers;

import model.CampaignTrackManager;
import model.Map;
import model.TensionManager;
import model.VictoryPointTracker;

public enum InstantiateComponents {

	INSTANCE;

	private InstantiateComponents() {

		Map.values();
		TensionManager.values();
		CampaignTrackManager.values();
		VictoryPointTracker.values();

	}

}
