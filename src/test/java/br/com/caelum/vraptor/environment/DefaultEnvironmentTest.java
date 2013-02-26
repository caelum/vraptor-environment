package br.com.caelum.vraptor.environment;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DefaultEnvironmentTest {

    @Test
    public void shouldGetDefaultValueIfTheValueIsntSet() throws Exception {
        DefaultEnvironment defaultEnvironment = new DefaultEnvironment("development");
        String value = defaultEnvironment.get("inexistent", "fallback");
        assertEquals("fallback", value);
    }
    
    @Test
    public void shouldGetValueIfIsSetInProperties() throws Exception {
        DefaultEnvironment defaultEnvironment = new DefaultEnvironment("development");
        String value = defaultEnvironment.get("env_name", "fallback");
        assertEquals("development", value);
    }

}
