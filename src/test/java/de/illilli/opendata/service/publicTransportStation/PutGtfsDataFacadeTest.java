package de.illilli.opendata.service.publicTransportStation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.opendata.service.Facade;

public class PutGtfsDataFacadeTest {

	private static final Logger logger = Logger.getLogger(PutGtfsDataFacadeTest.class);
	private URL url;

	public static void main(String[] args) throws IOException, URISyntaxException, SQLException, NamingException {
		ConnectionEnvironment.setUpConnectionForJndi();
		URL url = PutGtfsDataFacadeTest.class.getClassLoader().getResource("./google_transit.zip");
		Facade facade = new PutGtfsDataFacade(url);
		logger.info("facade = " + facade.getJson());

	}

	@Before
	public void setUp() throws Exception {
		this.url = this.getClass().getClassLoader().getResource("./google_transit.zip");
	}

	@Test
	@Ignore
	public void test() throws IOException, URISyntaxException, SQLException, NamingException {
		ConnectionEnvironment.setUpConnectionForJndi();

		Facade facade = new PutGtfsDataFacade(this.url);

		System.out.println(facade.getJson());
	}

}
