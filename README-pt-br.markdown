## vraptor-environment

Um plugin de vraptor que adiciona arquivos de configuração dependentes do ambiente

# instalação

É possível fazer o download do Vraptor-environment.jar do repositório do Maven, ou configurado em qualquer ferramenta compatível:

		<dependency>
			<groupId>br.com.caelum.vraptor</groupId>
			<artifactId>vraptor-environment</artifactId>
			<version>1.0.0</version>
			<scope>compile</scope>
		</dependency>
		
# configuração

Em seu web.xml, adicione:

		<context-param>
			<param-name>br.com.caelum.vraptor.environment</param-name>
			<param-value>nome_do_ambiente</param-value>
		</context-param>
		
onde nome_do_ambiente é o ambiente atual ('producao' ou 'desenvolvimento', por exemplo).

# nome_do_ambiente.properties

Seu nome_do_ambiente.properties deve ser criado em src/main/resources. Exemplo (desenvolvimento.properties):

		ambiente_de_teste = true
		email_de_teste = test.mail@mail.com
		environment.controller = true

O 'environment.controller' é uma chave reservada. Caso esteja setado como 'true' você terá acesso à /admin/environment onde é possível visualizar e editar suas configurações.

# acessando propriedades de ambiente no código java
		
		import br.com.caelum.vraptor.environment.Environment;
		
		@Resource
		public class meuController {
			Environment ambiente;
			EnviadorDeEmail enviador;
		
			public meuController(Environment ambiente, EnviadorDeEmail enviador) {
				this.ambiente = ambientet;
				this.enviador = enviador;
			}
			
			public void enviaEmail(String email) {
				if(ambiente.supports("ambiente_de_teste")) {
					enviador.enviaEmailPara(ambiente.get("email_de_teste"));
					return;
				}
				enviador.enviaEmailPara(email);
			}
		}

# acessando propriedades de ambiente no jsp

		<env:supports key='ambiente_de_teste'>
			<p>
				Você está no ambiente de teste. Suas ações aqui não afetarão o sistema.
			</p>
		</env:supports>
		
		Enviando e-mail para: <env:get key='email_de_teste'/>
		
# Ajuda

Receba assistência dos desenvolvedores do vraptor e da comunidade na lista de emails do vraptor.
		