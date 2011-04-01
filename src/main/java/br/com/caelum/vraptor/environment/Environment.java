package br.com.caelum.vraptor.environment;



/**
 * 
 * An environment has a set of key/value properties to be
 * used within your application
 * 
 * @author Alexandre Atoji
 *
 */

public interface Environment {

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
	 * Sets a key in memory. This will *not* affect any configuration file.
	 * @param key
	 * @param value
	 */
	void set(String key, String value);

	Iterable<String> getKeys();
}
