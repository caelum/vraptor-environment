package br.com.caelum.vraptor.environment;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

/**
 * This interceptor inserts a environment variable in all requests
 * 
 * @author Alexandre Atoji
 *
 */

@Intercepts
public class EnvironmentInterceptor implements Interceptor {

	private final Environment environment;
	private final HttpServletRequest request;

	public EnvironmentInterceptor(HttpServletRequest request, Environment environment) {
		this.request = request;
		this.environment = environment;
	}
	
	@Override
	public boolean accepts(ResourceMethod arg0) {
		return true;
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) throws InterceptionException {
		request.setAttribute("environment", environment);
		stack.next(method, instance);
	}

}
