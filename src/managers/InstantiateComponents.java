package managers;

import model.Map;
import model.TensionManager;

public enum InstantiateComponents {

	INSTANCE;

	private InstantiateComponents() {

		Map.values();
		TensionManager.values();

	}

}
