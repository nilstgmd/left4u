package de.hackathon.left4u.requests;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author <a href="mailto:meder@adobe.com">Nils Meder</a>
 */
public class DeleteStuffRequest implements IRequest<Void>{

	private static final String ENDPOINT = RequestConstants.BASE_URL + "/"
			+ RequestConstants.ENDPOINT_STUFF + "/";
	
	private final String stuffId;
	
	/**
	 * Constructs a new {@link DeleteStuffRequest}.
	 * @param stuffId The id to delete.
	 */
	public DeleteStuffRequest(String stuffId)
	{
		this.stuffId = stuffId;
	}
	
	@Override
	public Void execute() {
		try {
			executeDelete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private HttpResponse executeDelete() throws IOException,
	ClientProtocolException {
		final HttpClient httpclient = new DefaultHttpClient();
		final HttpDelete delete = new HttpDelete(ENDPOINT + stuffId);
		final HttpResponse response = httpclient.execute(delete);
		return response;
	}
}
