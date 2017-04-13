package de.illilli.opendata.service.publicTransportStation;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.test.annotation.type.IntegrationTest;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.opendata.service.Facade;

@Category(IntegrationTest.class)
public class StopsByLatLonFacadeTest {

	private static final Logger logger = Logger.getLogger(StopsByLatLonFacadeTest.class);

	@Before
	public void setUp() throws Exception {
		ConnectionEnvironment.setUpConnectionForJndi();
	}

	@Test
	public void test() throws IOException, SQLException, NamingException {
		String latlng = "50.96659064159747,6.9881160312996728";
		String limitAsString = "5";
		Facade facade = new StopsByLatLonFacade(latlng, limitAsString);
		String expected = IOUtils.toString(this.getClass().getResourceAsStream("/stopsByLatLonFacadeTest.json"));
		String actual = facade.getJson();
		logger.info(actual);
		Assert.assertEquals(expected, actual);
	}

}
