## vraptor-environment

A simple vraptor plugin that adds an environment dependent configuration file.

# installing

Vraptor-environment.jar can be downloaded from maven's repository, or configured in any compatible tool:

		<dependency>
			<groupId>br.com.caelum.vraptor</groupId>
			<artifactId>vraptor-environment</artifactId>
			<version>1.0.0</version>
			<scope>compile</scope>
		</dependency>
		
# configuration

In your web.xml add:

		<context-param>
			<param-name>br.com.caelum.vraptor.environment</param-name>
			<param-value>environment_name</param-value>
		</context-param>
		
where environment_name is the current environment ('production' or 'development' for example).

# environment_name.properties

Your environment_name.properties should be located at src/main/resources. Example ('development.properties'):

		test_environment = true
		test_mail = test.mail@mail.com
		environment.controller = true

The 'environment.controller' key is reserved. If it's set to true you will have access to /admin/environment where you can view and edit your configurations.

# accessing environment properties on java code
		
		import br.com.caelum.vraptor.environment.Environment;
		
		@Resource
		public class myController {
			Environment environment;
			MailSender sender;
		
			public myController(Environment environment, MailSender sender) {
				this.environment = environment;
				this.sender = sender;
			}
			
			public void sendMail(String email) {
				if(environment.supports("test_environment")) {
					sender.sendMailTo(environment.get("test_mail"));
					return;
				}
				sender.sendMailTo(email);
			}
		}

# accessing environment properties on jsp

		<env:supports key='test_environment'>
			<p>
				You are in the test environment. Your actions here won't affect the real system.
			</p>
		</env:supports>
		
		Sending mail to: <env:get key='test_mail'/>
		
# acessing configuration files per environment

Suppose you need to access a hibernate.cfg.xml file according to your environment.
Put your hibernate.cfg.xml files into the folders *development* and *production*.
Now Environment.getResource will return the resource according to your current environment as in:

	cfg = new AnnotationConfiguration();
	cfg.configure(environment.getResource("/hibernate.cfg.xml"));
	
For backward compatibility, if the configuration file is not found in the environment configuration dir, it will be loaded
from the root directory.
		
# help

Get help from vraptor developers and the community at vraptor mailing list.
