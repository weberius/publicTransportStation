package de.illilli.opendata.service.publicTransportStation;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.onebusaway.gtfs.impl.GtfsDaoImpl;
import org.onebusaway.gtfs.model.Agency;
import org.onebusaway.gtfs.model.Stop;
import org.onebusaway.gtfs.serialization.GtfsReader;
import org.onebusaway.gtfs.services.GtfsMutableDao;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.InsertDao;
import de.illilli.jdbc.UpdateDao;
import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.publicTransportStation.jdbc.Agency2DTO;
import de.illilli.opendata.service.publicTransportStation.jdbc.DeleteAgencys;
import de.illilli.opendata.service.publicTransportStation.jdbc.DeleteStops;
import de.illilli.opendata.service.publicTransportStation.jdbc.InsertAgency;
import de.illilli.opendata.service.publicTransportStation.jdbc.InsertStop;
import de.illilli.opendata.service.publicTransportStation.jdbc.Stop2DTO;

public class PutGtfsDataFacade implements Facade {

	private static final Logger logger = Logger.getLogger(PutGtfsDataFacade.class);

	/**
	 * <p>
	 * Read GTFS Data from remote.
	 * </p>
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws NamingException
	 * @throws SQLException
	 */
	public PutGtfsDataFacade() throws IOException, URISyntaxException, SQLException, NamingException {
		this(new URL(Config.getProperty("vrs.verkehrsdaten.gtfsdb.url")));
	}

	/**
	 * <p>
	 * Read GTFS Data from given URL.
	 * </p>
	 * 
	 * @param url
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws NamingException
	 * @throws SQLException
	 */
	public PutGtfsDataFacade(URL url) throws IOException, URISyntaxException, SQLException, NamingException {

		Connection conn = ConnectionFactory.getConnection();
		GtfsReader reader = new GtfsReader();
		reader.setInputLocation(new File(url.toURI()));

		GtfsMutableDao store = new GtfsDaoImpl();
		reader.setEntityStore(store);

		reader.run();

		// 1. bisherige Daten löschen
		logger.info("delete agencys");
		new UpdateDao(new DeleteAgencys(), conn).execute();

		List<Agency> agencyList = reader.getAgencies();
		for (Agency agency : agencyList) {
			new InsertDao(new InsertAgency(new Agency2DTO(agency)), conn).execute();
		}
		logger.info("'" + agencyList.size() + "' agencys inserted");

		// 1. bisherige Daten löschen
		logger.info("delete stops");
		new UpdateDao(new DeleteStops(), conn).execute();

		// Access entities through the store
		for (Stop stop : store.getAllStops()) {
			new InsertDao(new InsertStop(new Stop2DTO(stop)), conn).execute();
		}

		logger.info("'" + store.getAllStops().size() + "' stops inserted");
		conn.close();

	}

	@Override
	public String getJson() throws JsonProcessingException {
		return "{nothing happen}";
	}

}
