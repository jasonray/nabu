package nabu.model;

import java.net.URI;

public class ResourceMethod {
	private String ResourceName;
	private URI mountPoint;
	private String httpVerb;
	//private Parameters parameters; (query + path)
	//private MediaType produces
	//private MediaType consumes;
	//private String description;

	public String getResourceName() {
		return ResourceName;
	}
	public void setResourceName(String resourceName) {
		ResourceName = resourceName;
	}
	public URI getMountPoint() {
		return mountPoint;
	}
	public void setMountPoint(URI mountPoint) {
		this.mountPoint = mountPoint;
	}
	public String getHttpVerb() {
		return httpVerb;
	}
	public void setHttpVerb(String httpVerb) {
		this.httpVerb = httpVerb;
	}
	
}
