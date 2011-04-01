package br.com.caelum.vraptor.environment;

import java.io.IOException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.freemarker.Freemarker;
import freemarker.template.TemplateException;

@Resource
public class EnvironmentController {

	private final Result result;
	private final Environment environment;
	private final Freemarker freemarker;

	public EnvironmentController(Environment environment, Result result, Freemarker freemarker) {
		this.environment = environment;
		this.result = result;
		this.freemarker = freemarker;
	}
	
	@Path("/admin/environment")
	@Get
	public void list() throws IOException, TemplateException {
		if(isDisabled()) {
			result.notFound();
			return;
		}
		freemarker.use("list").with("environment", environment).render();
	}
	
	private boolean isDisabled() {
		return environment.supports("environment.controller");
	}

	@Path("/admin/environment")
	@Put
	public void edit(String key, String value) throws IOException, TemplateException {
		if(isDisabled()) {
			result.notFound();
			return;
		}
		environment.set(key, value);
		result.redirectTo(this).list();
	}
}
