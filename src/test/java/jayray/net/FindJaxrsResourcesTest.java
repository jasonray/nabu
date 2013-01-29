package jayray.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.junit.BeforeClass;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

public class FindJaxrsResourcesTest {
	private static Reflections reflections;

	// private Reflections reflections = new Reflections(
	// new ConfigurationBuilder()
	// .setUrls(ClasspathHelper.forPackage("jayray.net"))
	// .setScanners(new SubTypesScanner(),
	// new TypeAnnotationsScanner(),
	// new ResourcesScanner()
	// )
	// );
	// private Reflections reflections = new Reflections("jayray.net", new
	// TypeAnnotationsScanner(), new MethodAnnotationsScanner());

	@BeforeClass
	public static void setup() {
		ConfigurationBuilder configBuilder;
		configBuilder = new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("jayray.net")).setScanners(new TypeAnnotationsScanner(), new MethodAnnotationsScanner());
		reflections = new Reflections(configBuilder);
	}

	@Test
	public void findResources() {
		System.out.println("looking for classes with Path annotation");
		Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(Path.class);
		for (Class<?> annotatedClass : annotatedClasses) {
			System.out.println(annotatedClass.getCanonicalName());
		}
		assertTrue(annotatedClasses.size() > 0);
	}

	@Test
	public void findResourceMethods() {
		System.out.println("looking for methods with GET annotation");
		Set<Method> resourceMethods = reflections.getMethodsAnnotatedWith(GET.class);
		for (Method resourceMethod : resourceMethods) {
			System.out.println(resourceMethod.getClass().getCanonicalName() + "." + resourceMethod.getName());
		}
		assertTrue(resourceMethods.size() > 0);
	}

	@Test
	public void forResourceMethodAndPath() {
		Method resourceMethod = findResourceMethod("jayray.net.hello.HelloWorldResource.sayhello");
		assertEquals("hello", resourceMethod.getDeclaringClass().getAnnotation(Path.class).value());
		assertEquals("hello", resourceMethod.getAnnotation(Path.class).value());
	}

	private Method findResourceMethod(String qualifiedName) {
		Set<Method> resourceMethods = reflections.getMethodsAnnotatedWith(GET.class);
		for (Method resourceMethod : resourceMethods) {
			String thisMethodsQualifiedName = resourceMethod.getDeclaringClass().getName() + "." + resourceMethod.getName();
			if (thisMethodsQualifiedName.contentEquals(qualifiedName))
				return resourceMethod;
		}
		throw new RuntimeException("Did not find method " + qualifiedName);
	}

}
