package model;

import utils.ArrayList;

public enum AddInfluenceCubesManager {

	INSTANCE;

	private ArrayList<AddInfluenceCubes> list = new ArrayList<>();
	private AddInfluenceCubes addInfluenceCubesResolving = null;

	private AddInfluenceCubesManager() {

	}

	public void addInfluenceCubeAction(AddInfluenceCubes addInfluenceCubes) {
		this.list.addLast(addInfluenceCubes);
	}

	public void setAddInfluenceCubesResolving() {
		this.addInfluenceCubesResolving = this.list.removeFirst();
	}

	public AddInfluenceCubes getAddInfluenceCubesResolving() {
		return this.addInfluenceCubesResolving;
	}

}
