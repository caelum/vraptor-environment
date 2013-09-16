package br.com.caelum.vraptor.environment;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;

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
public class ServletBasedEnvironment extends DefaultEnvironment {

	@Inject
	public ServletBasedEnvironment(ServletContext context) throws IOException {
		super(env(context));
	}

	@Deprecated// CDI eyes only
	public ServletBasedEnvironment() {}

	private static String env(ServletContext context) {
		String contextEnv = context.getInitParameter("br.com.caelum.vraptor.environment");
		if (contextEnv != null) {
			return contextEnv;
		}
		return System.getenv("VRAPTOR_ENVIRONMENT");
	}

}
