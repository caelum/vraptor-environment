package br.com.caelum.vraptor.environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

/**
 * A default environment implementation which loads the environment file based on the br.com.caelum.vraptor.environment
 * property in the context init parameter.
 * @author Alexandre Atoji
 * @author Andrew Kurauchi
 * @author Guilherme Silveira
 */
@ApplicationScoped
@Component
public class DefaultEnvironment implements Environment {

	private final Properties properties;

	public DefaultEnvironment(ServletContext context) throws IOException {
		String name = context.getInitParameter("br.com.caelum.vraptor.environment");
		if (name == null || name.equals("")) {
			name = "development";
		}
		InputStream stream = DefaultEnvironment.class.getResourceAsStream("/" + name+".properties");
		this.properties = new Properties();
		this.properties.load(stream);
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

}
