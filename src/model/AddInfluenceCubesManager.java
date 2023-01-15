package model;

import utils.ArrayList;

public enum AddInfluenceCubesManager {

	INSTANCE;

	private ArrayList<AddInfluenceCubes> list = new ArrayList<>();

	private AddInfluenceCubesManager() {

	}

	public void addInfluenceCubeAction(AddInfluenceCubes addInfluenceCubes) {
		this.list.addLast(addInfluenceCubes);
	}

	public ArrayList<AddInfluenceCubes> getList() {
		return this.list;
	}

}
