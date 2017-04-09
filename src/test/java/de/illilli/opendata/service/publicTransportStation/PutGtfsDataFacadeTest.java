package de.illilli.opendata.service.publicTransportStation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.opendata.service.Facade;

public class PutGtfsDataFacadeTest {

	URL url;

	@Before
	public void setUp() throws Exception {
		this.url = this.getClass().getClassLoader().getResource("./google_transit.zip");
	}

	@Test
	public void test() throws IOException, URISyntaxException, SQLException, NamingException {
		ConnectionEnvironment.setUpConnectionForJndi();

		Facade facade = new PutGtfsDataFacade(this.url);

		System.out.println(facade.getJson());
	}

}
