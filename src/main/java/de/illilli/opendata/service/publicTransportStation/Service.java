package de.illilli.opendata.service.publicTransportStation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

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

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/ping")
	public String getPing() throws MalformedURLException, IOException {

		request.setCharacterEncoding(ENCODING);
		response.setCharacterEncoding(ENCODING);

		return "{alive}";
	}

	/**
	 * <p>
	 * Beispiele:
	 * <ul>
	 * <li><a href="http://localhost:8080/publicTransportStation/service/stops">
	 * /publicTransportStation/service/stops</a></li>
	 * <li><a href=
	 * "http://localhost:8080/publicTransportStation/service/stops?geojson">
	 * /publicTransportStation/service/stops?geojson</a></li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/stops")
	public String getStops() throws SQLException, NamingException, IOException {

		request.setCharacterEncoding(ENCODING);
		response.setCharacterEncoding(ENCODING);

		boolean geojson = request.getParameter("geojson") != null;

		Facade facade = new ErrorFacade("error.service");
		if (geojson) {
			facade = new StopsGeojsonFacade();
		} else {
			facade = new StopsFacade();
		}

		return facade.getJson();
	}

	/**
	 * <p>
	 * Mit Hilfe dieser Schnittstelle werden die Daten ausgelesen, konvertiert
	 * und in der Datenbank persistiert. Zunächst werden bestehende
	 * Datenbestände gelöscht. Dann werden die aktuellen Daten einfügt. Die
	 * Schnitt kann beliebig häufig aufgerufen werden.
	 * </p>
	 * <p>
	 * Von der Kommandozeile kann die Schnittstelle mit folgendem Kommando
	 * aufgerufen werden:
	 * </p>
	 * <code>curl -X PUT http://localhost:8080/publicTransportStation/service/put/vrs</code>
	 * 
	 * @param verbund
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @throws URISyntaxException
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/put/{verbund}")
	public String put(@PathParam("verbund") String verbund) throws JsonParseException, JsonMappingException,
			IOException, SQLException, NamingException, URISyntaxException {

		logger.info("/put called");
		Facade facade = new PutGtfsDataFacade();
		if (verbund != null && "vrs".equals(verbund.toLowerCase())) {
			facade = new PutGtfsDataFacade();
		} else {
			facade = new ErrorFacade("error.service.verbundnotfound");
		}
		return facade.getJson();
	}

}
