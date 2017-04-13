package de.illilli.opendata.service.publicTransportStation;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.opendata.service.Facade;

public class StopsByBoundigBoxFacadeTest {

	private static final Logger logger = Logger.getLogger(StopsByBoundigBoxFacadeTest.class);

	@Before
	public void setUp() throws Exception {
		ConnectionEnvironment.setUpConnectionForJndi();
	}

	@Test
	public void test() throws SQLException, IOException, NamingException {
		String bbox = "50.940692,6.951216,50.931568,6.977266";
		Facade facade = new StopsByBoundigBoxFacade(bbox);
		String expected = IOUtils.toString(this.getClass().getResourceAsStream("/stopsByBoundingBoxFacadeTest.json"));
		String actual = facade.getJson();
		logger.info(actual);
		Assert.assertEquals(expected, actual);
	}

}
