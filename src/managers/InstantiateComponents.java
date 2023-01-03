package managers;

import model.Map;

public enum InstantiateComponents {

	INSTANCE;

	private InstantiateComponents() {

		Map.values();

	}

}
