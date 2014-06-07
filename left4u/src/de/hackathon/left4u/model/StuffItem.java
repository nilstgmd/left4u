package de.hackathon.left4u.model;

import java.util.List;

/**
 * @author <a href="mailto:meder@adobe.com">Nils Meder</a>
 */
public class StuffItem {

	private final String id;
	private final String name;
	private final long created;
	private final long updated;
	private final String content;
	private final String location;
	private final List<String> tags;
	
	public StuffItem(String id, String name, long created, long updated, String content, String location, List<String> tags)
	{
		this.id = id;
		this.name = name;
		this.created = created;
		this.updated = updated;
		this.content = content;
		this.location = location;
		this.tags = tags;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public long getCreated() {
		return created;
	}
	
	public long getUpdated() {
		return updated;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getLocation() {
		return location;
	}
	
	public List<String> getTags() {
		return tags;
	}
}
