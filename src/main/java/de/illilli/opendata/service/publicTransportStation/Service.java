package de.illilli.opendata.service.publicTransportStation;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.Facade;

@Path("/")
public class Service {

	private final static Logger logger = Logger.getLogger(Service.class);
	public static final String ENCODING = Config.getProperty("encoding");

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	/**
	 * <p>
	 * This service delivers all 'haltestellen' as json. It can deliver the
	 * information als geoJson for location on a map.
	 * </p>
	 * <p>
	 * Exammples:
	 * <ul>
	 * <li><a href= "http://localhost:8080/nextbushaltestellen/service/stops">
	 * /nextbushaltestellen/service/stops</a></li>
	 * <li><a href=
	 * "http://localhost:8080/nextbushaltestellen/service/stops?geojson">
	 * /nextbushaltestellen/service/stops?geojson</a></li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/stops")
	public String getStops() throws MalformedURLException, IOException {

		request.setCharacterEncoding(ENCODING);
		response.setCharacterEncoding(ENCODING);

		boolean geojson = request.getParameter("geojson") != null;

		Facade facade;
		if (geojson) {
			logger.info("call /stops geojson");
			facade = new StopsGeojsonFacade();
		} else {
			logger.info("call /stops");
			facade = new StopsJsonFacade();
		}

		return facade.getJson();
	}

}
