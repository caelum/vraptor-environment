package br.com.caelum.vraptor.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * A default environment based on a string.
 *
 * @author Alexandre Atoji
 * @author Andrew Kurauchi
 * @author Guilherme Silveira
 */
public class DefaultEnvironment implements Environment {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultEnvironment.class);
    private final Properties specificProperties;
    private final Properties generalProperties;
    private String environment;

    public DefaultEnvironment(String environment) throws IOException {
        if (environment == null || environment.equals("")) {
            environment = "development";
        }
        this.environment = environment;

        String name = "/" + environment + ".properties";
        InputStream specificStream = DefaultEnvironment.class.getResourceAsStream(name);
        InputStream generalStream = DefaultEnvironment.class.getResourceAsStream("/environment.properties");

        this.specificProperties = new Properties();
        this.generalProperties = new Properties();

        if (generalStream != null) {
            this.generalProperties.load(generalStream);

            if (specificStream != null) {
                this.specificProperties.load(specificStream);
                this.generalProperties.putAll(this.specificProperties);
            }

        } else {
            LOG.warn("Could not find the file 'enviroment' to load. If you ask for any property, null will be returned");
        }

    }

    public boolean supports(String feature) {
        return Boolean.parseBoolean(get(feature));
    }

    public boolean has(String key) {
        return generalProperties.containsKey(key);
    }

    public String get(String key) {
        if(!has(key)) {
            throw new NoSuchElementException("Key " + key + " not found in environment " + environment);
        }
        return generalProperties.getProperty(key);
    }

    @Override
    public void set(String key, String value) {
        specificProperties.setProperty(key, value);
    }

    @Override
    public Iterable<String> getKeys() {
        return (Iterable<String>) specificProperties.stringPropertyNames();
    }

    @Override
    public URL getResource(String name) {
        URL resource = DefaultEnvironment.class.getResource("/" + environment + name);
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
