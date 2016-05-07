package de.illilli.opendata.service.nextbushaltestellen;

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
	 * <li><a href=
	 * "http://localhost:8080/nextbushaltestellen/service/haltestellen">
	 * /nextbushaltestellen/service/haltestellen</a></li>
	 * <li><a href=
	 * "http://localhost:8080/nextbushaltestellen/service/haltestellen?geojson">
	 * /nextbushaltestellen/service/haltestellen?geojson</a></li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/haltestellen")
	public String getHaltestellen() throws MalformedURLException, IOException {

		request.setCharacterEncoding(ENCODING);
		response.setCharacterEncoding(ENCODING);

		boolean geojson = request.getParameter("geojson") != null;

		Facade facade;
		if (geojson) {
			logger.info("call /haltestellen geojson");
			facade = new HaltestellenGeojsonFacade();
		} else {
			logger.info("call /haltestellen");
			facade = new HaltestellenJsonFacade();
		}

		return facade.getJson();
	}

}
