package io.syndesis.example.extension.tp3;

import static org.junit.Assert.assertNotNull;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelAutoConfiguration;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = {
				CamelAutoConfiguration.class,
				ExtensionTest.TestConfiguration.class
		},
		properties = {
				"spring.main.banner-mode = off",
				"camel.springboot.auto-startup=false"
		}
)
public class ExtensionTest {

	@Autowired
	private CamelContext camelContext;

	@Test
	public void test() {
		assertNotNull(camelContext);
	}

	@Configuration
	public static class TestConfiguration {
	}
}
