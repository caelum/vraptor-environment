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

	public static final String ENVIRONMENT_PROPERTY = "br.com.caelum.vraptor.environment";

	@Inject
	public ServletBasedEnvironment(ServletContext context) throws IOException {
		super(env(context));
	}

	@Deprecated// CDI eyes only
	public ServletBasedEnvironment() {}

	private static String env(ServletContext context) {
		String contextEnv = context.getInitParameter(ENVIRONMENT_PROPERTY);
		if (contextEnv != null) {
			return contextEnv;
		}
		String systemEnv = System.getenv("VRAPTOR_ENVIRONMENT");
		if (systemEnv != null) {
			return systemEnv;
		}
		return System.getProperty(ENVIRONMENT_PROPERTY);
	}

}
