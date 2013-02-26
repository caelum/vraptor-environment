package br.com.caelum.vraptor.environment;

import java.net.URL;

/**
 * An environment has a set of key/value properties to be
 * used within your application
 * 
 * @author Alexandre Atoji
 * @author Guilherme Silveira
 */
public interface Environment {

	/**
	 * Returns the environment name
	 * 
	 */
	String getName();
	
	/** 
	 * Checks if a key is present 
	 * 
	 */
	boolean has(String key);
	
	/**
	 * Checks if a key is equals to true
	 * if it's not present will return false
	 */
	boolean supports(String feature);
	
	/**
	 * Returns a key
	 */
	String get(String string);
	
	/**
	 * Returns a key or a default value if the value is'nt set
	 */
	String get(String string, String defaultValue);
	
	/**
	 * Sets a key in memory. This will *not* affect any configuration file.
	 * @param key
	 * @param value
	 */
	void set(String key, String value);

	Iterable<String> getKeys();
	
	/**
	 * Locates a resource according to your current environment.
	 */
	URL getResource(String name);
}
