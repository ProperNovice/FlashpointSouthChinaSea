package model;

import components.Disk;
import managers.Credentials;
import utils.Animation;
import utils.Enums.AnimationSynchEnum;
import utils.HashMap;
import utils.Vector2;

public enum CampaignTrackManager {

	INSTANCE;

	private int currentCampaign = 1;
	private HashMap<Integer, Vector2> campaignCoordinates = new HashMap<>();
	private Disk disk = new Disk();

	private CampaignTrackManager() {
		createCampaignTrack();
	}

	private void createCampaignTrack() {

		Vector2 startingCoordinates = new Vector2(100.5, 357.5)
				.addVector2(Credentials.INSTANCE.cMap);

		Vector2 gap = new Vector2(0, 55);

		this.campaignCoordinates.put(1, startingCoordinates);
		this.campaignCoordinates.put(2, startingCoordinates.addVector2(gap));
		this.campaignCoordinates.put(3,
				startingCoordinates.addVector2(gap).addVector2(gap));

		this.disk.getImageView()
				.relocateCenter(this.campaignCoordinates.getValue(this.currentCampaign));

	}

	public int getCurrentCampaign() {
		return this.currentCampaign;
	}

	public void setCampaign(int campaign) {

		this.currentCampaign = campaign;
		Vector2 vector2 = this.campaignCoordinates.getValue(this.currentCampaign);
		Animation.INSTANCE.animateCenter(this.disk, vector2, AnimationSynchEnum.ASYNCHRONOUS);

	}

}
