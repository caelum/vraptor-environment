class VraptorEnvironment < VraptorScaffold::Base

  def initialize(opts)
    super(opts)
  end

  def self.banner
    "vraptor environment"
  end

  def build
    environment = "\n\t<context-param>\n\t\t<param-name>br.com.caelum.vraptor.environment</param-name>\n\t\t<param-value>development</param-value>\n\t</context-param>\n\n\t"
    inject_into_file("src/main/webapp/WEB-INF/web.xml", environment, :before=>"<filter>")
    create_file("src/main/resources/development.properties", "environment.controller=true\n")
    create_file("src/main/resources/test.properties", "environment.controller=true\n")
    create_file("src/main/resources/production.properties", "environment.controller=false\n")
  end
end
