package de.hackathon.left4u.requests;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import de.hackathon.left4u.model.StuffItem;

/**
 * @author <a href="mailto:meder@adobe.com">Nils Meder</a>
 */
public class PutStuffRequest implements IRequest<Void>{

	private static final String ENDPOINT = RequestConstants.BASE_URL + "/"
			+ RequestConstants.ENDPOINT_STUFF + "/";
	
	private final StuffItem item;
	
	/**
	 * Constructs a new {@link PutStuffRequest}.
	 * @param item The update item.
	 */
	public PutStuffRequest(StuffItem item)
	{
		this.item = item;
	}
	
	@Override
	public Void execute() {
		try {
			final JSONObject object = convertToJson(item);
			executePut(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private JSONObject convertToJson(StuffItem item) {
		final JSONObject object = new JSONObject();

		try {
			object.put("name", item.getName());
			object.put("created", item.getName());
			object.put("updated", item.getName());
			object.put("content", item.getName());
			object.put("location", item.getName());
			object.put("tags", item.getName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return object;
	}

	private HttpResponse executePut(JSONObject object) throws IOException,
	ClientProtocolException {
		final HttpClient httpclient = new DefaultHttpClient();
		final HttpPut put = new HttpPut(ENDPOINT + item.getId());
		put.setEntity(new StringEntity(object.toString(), "UTF-8"));
		final HttpResponse response = httpclient.execute(put);
		return response;
	}
}
