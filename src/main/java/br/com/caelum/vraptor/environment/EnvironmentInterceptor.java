package br.com.caelum.vraptor.environment;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor4.BeforeCall;
import br.com.caelum.vraptor4.Intercepts;

/**
 * This interceptor inserts a environment variable in all requests
 *
 * @author Alexandre Atoji
 *
 */

@Intercepts
public class EnvironmentInterceptor {

	private Environment environment;
	private HttpServletRequest request;

	@Deprecated // CDI eyes only
	public EnvironmentInterceptor() {}

	@Inject
	public EnvironmentInterceptor(HttpServletRequest request, Environment environment) {
		this.request = request;
		this.environment = environment;
	}

	@BeforeCall
	public void intercept() {
		request.setAttribute("environment", environment);
	}

}
