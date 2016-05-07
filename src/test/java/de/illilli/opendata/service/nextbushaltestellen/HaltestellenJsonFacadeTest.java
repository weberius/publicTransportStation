package de.illilli.opendata.service.nextbushaltestellen;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.illilli.opendata.service.Facade;

public class HaltestellenJsonFacadeTest {

	String json;

	@Before
	public void setUp() throws Exception {
		InputStream inputStream = this.getClass().getResourceAsStream("/haltestellen.json");
		json = IOUtils.toString(inputStream);
	}

	@Test
	public void testGetJson() throws JsonProcessingException {
		Facade facade = new HaltestellenJsonFacade();
		String expected = json;
		String actual = facade.getJson();
		Assert.assertEquals(expected, actual);
	}

}
