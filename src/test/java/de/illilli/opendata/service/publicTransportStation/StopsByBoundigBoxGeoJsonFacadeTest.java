package de.illilli.opendata.service.publicTransportStation;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.test.annotation.type.IntegrationTest;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.opendata.service.Facade;

public class StopsByBoundigBoxGeoJsonFacadeTest {

	private static final Logger logger = Logger.getLogger(StopsByBoundigBoxGeoJsonFacadeTest.class);

	@Before
	public void setUp() throws Exception {
		ConnectionEnvironment.setUpConnectionForJndi();
	}

	@Test
	@Category(IntegrationTest.class)
	@Ignore
	public void test() throws SQLException, IOException, NamingException {
		String bbox = "50.940692,6.951216,50.931568,6.977266";
		Facade facade = new StopsByBoundigBoxGeoJsonFacade(bbox);
		String expected = IOUtils
				.toString(this.getClass().getResourceAsStream("/stopsByBoundBoxGeoJsonFacadeTest.geojson"));
		String actual = facade.getJson();
		logger.info(actual);
		Assert.assertEquals(expected, actual);
	}

}
