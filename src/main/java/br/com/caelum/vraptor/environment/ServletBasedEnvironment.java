package br.com.caelum.vraptor.environment;

import java.io.IOException;

import javax.servlet.ServletContext;

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
		super(env(context));
	}

	private static String env(ServletContext context) {
		String contextEnv = context.getInitParameter("br.com.caelum.vraptor.environment");
		if (contextEnv != null) {
			return contextEnv;
		}
		return System.getenv("VRAPTOR_ENVIRONMENT");
	}

}
