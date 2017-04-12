package de.illilli.opendata.service.publicTransportStation;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.opendata.service.Facade;

public class StopsFacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws IOException, SQLException, NamingException {
		ConnectionEnvironment.setUpConnectionForJndi();
		Facade facade = new StopsFacade();
		System.out.println(facade.getJson());
	}

}
