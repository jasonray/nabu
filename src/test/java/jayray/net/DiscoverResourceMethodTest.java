package jayray.net;

import static org.junit.Assert.*;
import nabu.model.Nabu;
import nabu.model.ResourceMethod;
import nabu.model.ResourceMethods;

import org.junit.Test;

public class DiscoverResourceMethodTest {
	final private String packageName = "jayray.net";

	@Test
	public void outputResourceMethods() {
		Nabu nabu = new Nabu(packageName);
		ResourceMethods extractApiDefinition = nabu.extractApiDefinition();

		System.out.println(String.format("output extracted resource methods [%s]", extractApiDefinition.size()));
		for (ResourceMethod resourceMethod : extractApiDefinition) {
			System.out.println("resource method: " + resourceMethod.getResourceName());
		}
	}

	@Test
	public void discoverMethodWithGet() {
		final String expectedResourceName = "jayray.net.hello.HelloWorldResource.sayhello";
		Nabu nabu = new Nabu(packageName);
		ResourceMethods extractApiDefinition = nabu.extractApiDefinition();

		boolean found = false;
		for (ResourceMethod resourceMethod : extractApiDefinition) {
			if (resourceMethod.getResourceName().contentEquals(expectedResourceName)) {
				found = true;
			}
		}
		assertTrue(found);
	}
	
	@Test
	public void ensureThatNonResourceMethodDoesNotGetReturned() {
		final String expectedResourceName = "jayray.net.hello.HelloWorldResource.nonResourceMethod";
		Nabu nabu = new Nabu(packageName);
		ResourceMethods extractApiDefinition = nabu.extractApiDefinition();

		boolean found = false;
		for (ResourceMethod resourceMethod : extractApiDefinition) {
			if (resourceMethod.getResourceName().contentEquals(expectedResourceName)) {
				found = true;
			}
		}
		assertTrue(!found);
	}
}
