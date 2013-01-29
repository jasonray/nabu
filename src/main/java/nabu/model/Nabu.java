package nabu.model;

import java.lang.reflect.Method;
import java.util.Set;

import javax.ws.rs.GET;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

public class Nabu {
	private Reflections reflections;

	public Nabu(String packageName) {
		reflections = intializeReflections(packageName);
	}

	public ResourceMethods extractApiDefinition() {
		Set<Method> methods = reflections.getMethodsAnnotatedWith(GET.class);

		ResourceMethods resourceMethods = new ResourceMethods();
		for (Method method : methods) {
			ResourceMethod resourceMethod = new ResourceMethod();
			resourceMethod.setResourceName(extractResourceName(method));
			// resourceMethod.setMountPoint(mountPoint);
			resourceMethod.setHttpVerb("GET");
			resourceMethods.add(resourceMethod);
		}

		return resourceMethods;
	}

	private String extractResourceName(Method resourceMethod) {
		return resourceMethod.getDeclaringClass().getName() + "." + resourceMethod.getName();
	}

	private Reflections intializeReflections(String packageName) {
		ConfigurationBuilder configBuilder;
		configBuilder = new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(packageName)).setScanners(new TypeAnnotationsScanner(), new MethodAnnotationsScanner());
		Reflections reflections = new Reflections(configBuilder);
		return reflections;
	}
}
