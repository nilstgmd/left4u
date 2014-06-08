package de.hackthon.left4u;

import java.util.List;

import de.hackathon.left4u.model.StuffItem;
import de.hackathon.left4u.requests.BrowseStuffRequest;
import android.app.Activity;
import android.os.Bundle;

public class BrowseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BrowseStuffRequest createdRequest = new BrowseStuffRequest("created", null, null, null);
    	List<StuffItem> createdResult = createdRequest.execute();

    	BrowseStuffRequest nearestRequest = new BrowseStuffRequest("created", null, null, null);
    	List<StuffItem> nearestResult = nearestRequest.execute();
    	
		super.onCreate(savedInstanceState);
	} 
}
