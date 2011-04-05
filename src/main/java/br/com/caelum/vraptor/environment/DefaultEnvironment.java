package br.com.caelum.vraptor.environment;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

/**
 * A default environment implementation which loads the environment file based
 * on the br.com.caelum.vraptor.environment property in the context init
 * parameter.
 * 
 * @author Alexandre Atoji
 * @author Andrew Kurauchi
 * @author Guilherme Silveira
 */
@ApplicationScoped
@Component
public class DefaultEnvironment implements Environment {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(DefaultEnvironment.class);
	private final Properties properties;
	private String environment;

	public DefaultEnvironment(ServletContext context) throws IOException {
		this.environment = context
				.getInitParameter("br.com.caelum.vraptor.environment");
		if (environment == null || environment.equals("")) {
			environment = "development";
		}
		String name = "/" + environment + ".properties";
		InputStream stream = DefaultEnvironment.class.getResourceAsStream(name);
		this.properties = new Properties();
		if (stream != null) {
			this.properties.load(stream);
		} else {
			LOG.warn("Could not find the file " + name
					+ " to load. If you ask for any property, null will be returned");
		}
	}

	public boolean supports(String feature) {
		return Boolean.parseBoolean(properties.getProperty(feature, "false"));
	}

	public boolean has(String key) {
		return properties.containsKey(key);
	}

	public String get(String key) {
		return properties.getProperty(key);
	}

	@Override
	public void set(String key, String value) {
		properties.setProperty(key, value);
	}

	@Override
	public Iterable<String> getKeys() {
		return (Iterable<String>) properties.stringPropertyNames();
	}

	@Override
	public URL getResource(String name) {
		URL resource = DefaultEnvironment.class.getResource("/" + environment
				+ name);
		if (resource != null) {
			return resource;
		}
		return DefaultEnvironment.class.getResource(name);
	}

}
