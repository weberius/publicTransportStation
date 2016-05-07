package de.illilli.opendata.service.nextbushaltestellen;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.illilli.opendata.service.Facade;

public class StopsGeojsonFacadeTest {

	String json;

	@Before
	public void setUp() throws Exception {
		InputStream inputStream = this.getClass().getResourceAsStream("/stops.geojson");
		json = IOUtils.toString(inputStream);
	}

	@Test
	public void testGetJson() throws IOException {
		Facade facade = new StopsGeojsonFacade();
		String expected = json;
		String actual = facade.getJson();
		Assert.assertEquals(expected, actual);
	}

}
