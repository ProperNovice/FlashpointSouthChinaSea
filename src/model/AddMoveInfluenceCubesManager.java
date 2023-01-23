package model;

import utils.ArrayList;

public enum AddMoveInfluenceCubesManager {

	INSTANCE;

	private ArrayList<AddMoveInfluenceCubes> list = new ArrayList<>();

	private AddMoveInfluenceCubesManager() {

	}

	public void addInfluenceCubeAction(AddMoveInfluenceCubes addInfluenceCubes) {
		this.list.addLast(addInfluenceCubes);
	}

	public ArrayList<AddMoveInfluenceCubes> getList() {
		return this.list;
	}

}
