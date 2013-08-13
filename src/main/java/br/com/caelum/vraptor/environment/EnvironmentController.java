package br.com.caelum.vraptor.environment;

import java.io.IOException;

import javax.inject.Inject;

import br.com.caelum.vraptor.freemarker.Freemarker;
import br.com.caelum.vraptor4.Controller;
import br.com.caelum.vraptor4.Get;
import br.com.caelum.vraptor4.Path;
import br.com.caelum.vraptor4.Put;
import br.com.caelum.vraptor4.Result;
import freemarker.template.TemplateException;

@Controller
public class EnvironmentController {

	private Result result;
	private Environment environment;
	private Freemarker freemarker;

	@Deprecated// CDI eyes only
	public EnvironmentController() {}

	@Inject
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
		return !environment.supports("environment.controller");
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
