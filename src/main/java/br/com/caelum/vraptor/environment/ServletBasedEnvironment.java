package br.com.caelum.vraptor.environment;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.NoSuchElementException;
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
public class ServletBasedEnvironment extends DefaultEnvironment {
	
	public ServletBasedEnvironment(ServletContext context) throws IOException {
		super(context
				.getInitParameter("br.com.caelum.vraptor.environment"));
	}

}
