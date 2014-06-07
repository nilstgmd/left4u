package de.hackathon.left4u.requests;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hackathon.left4u.model.StuffItem;

/**
 * @author <a href="mailto:meder@adobe.com">Nils Meder</a>
 */
public class BrowseStuffRequest implements IRequest<List<StuffItem>> {

	private static final String UTF_8 = "UTF-8";
	private static final String ENDPOINT = RequestConstants.BASE_URL + "/"
			+ RequestConstants.ENDPOINT_STUFF;

	private final String sort;
	private final String filter;
	private final String lat;
	private final String lon;
	
	/**
	 * Constructs a new {@link BrowseStuffRequest}.
	 */
	public BrowseStuffRequest(String sort, String filter, String lat, String lon)
	{
		this.sort = sort;
		this.filter = filter;
		this.lat = lat;
		this.lon = lon;
	}
	
	@Override
	public List<StuffItem> execute() {
		List<StuffItem> stuffs = Arrays.<StuffItem> asList();
		try {
			final HttpResponse response = executeGet();
			final StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				final JSONObject payload = getResult(response);
				JSONArray results = (JSONArray) payload.get("results");
				for (int i = 0; i < results.length(); i++) {
					final JSONObject item = (JSONObject) results.get(i);
					final StuffItem stuff = createdStuffItem(item);
					stuffs.add(stuff);
				}
			} else {
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return stuffs;
		}
	}

	@SuppressWarnings("unchecked")
	private StuffItem createdStuffItem(JSONObject item) throws JSONException {
		final String id = (String) item.get("_id");
		final String name = (String) item.get("name");
		final long created = (Long) item.get("created");
		final long updated = (Long) item.get("updated");
		final String content = (String) item.get("content");
		final String location = (String) item.get("location");
		final List<String> tags = (List<String>) item.get("tags");
		return new StuffItem(id, name, created, updated, content, location,
				tags);
	}

	private JSONObject getResult(final HttpResponse response)
			throws IOException, JSONException {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		response.getEntity().writeTo(out);
		out.close();
		return new JSONObject(out.toString());
	}

	private HttpResponse executeGet() throws IOException,
			ClientProtocolException {
		final HttpClient httpclient = new DefaultHttpClient();
		
		String requestUri = ENDPOINT + "?";
		
		if (sort != null)
		{
			requestUri = requestUri + "sort=" + sort + "&";
		}
		if (filter != null)
		{
			requestUri = requestUri + "filter=" + filter + "&";
		}
		if (lat != null)
		{
			requestUri = requestUri + "lat=" + lat + "&";
		}
		if (lon != null)
		{
			requestUri = requestUri + "long=" + lon + "&";
		}
		
		final HttpGet get = new HttpGet(URLEncoder.encode(requestUri, UTF_8));
		final HttpResponse response = httpclient.execute(get);
		return response;
	}
}
