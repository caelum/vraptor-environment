package br.com.caelum.vraptor.environment;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A default environment based on a string.
 * 
 * @author Alexandre Atoji
 * @author Andrew Kurauchi
 * @author Guilherme Silveira
 */
public class DefaultEnvironment implements Environment {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(DefaultEnvironment.class);
	private final Properties properties;
	private final String environment;
	
	public DefaultEnvironment(String environment) throws IOException {
		if (environment == null || environment.equals("")) {
			environment = "development";
		}
		this.environment = environment;
		LOG.info("Using vraptor environment " + environment);
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

	@Override
	public boolean supports(String feature) {
		return Boolean.parseBoolean(get(feature));
	}

	@Override
	public boolean has(String key) {
		return properties.containsKey(key);
	}

	@Override
	public String get(String key) {
		if(!has(key)) {
			throw new NoSuchElementException("Key " + key + " not found in environment " + environment);
		}
		return properties.getProperty(key);
	}
	
	@Override
	public String get(String key, String defaultValue) {
	    if (has(key)) {
	        return get(key);
	    }
	    return defaultValue;
	}

	@Override
	public void set(String key, String value) {
		properties.setProperty(key, value);
	}

	@Override
	public Iterable<String> getKeys() {
		return properties.stringPropertyNames();
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

	@Override
	public String getName() {
		return environment;
	}


}
