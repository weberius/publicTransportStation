package de.illilli.opendata.service.publicTransportStation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.lang3.math.NumberUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.LatLngFromString;
import de.illilli.opendata.service.publicTransportStation.jdbc.SelectStopsByLatLon;
import de.illilli.opendata.service.publicTransportStation.jdbc.StopDTO;
import de.illilli.opendata.service.publicTransportStation.model.DTOList2Stops;
import de.illilli.opendata.service.publicTransportStation.model.Stop;

public class StopsByLatLonFacade implements Facade {

	List<StopDTO> dtoList;
	private int limit = 5;

	public StopsByLatLonFacade(String latlng, String limitAsString) throws SQLException, NamingException, IOException {

		double lat = new LatLngFromString(latlng).getLat();
		double lng = new LatLngFromString(latlng).getLng();

		if (limitAsString != null && NumberUtils.isNumber(limitAsString)) {
			limit = NumberUtils.createInteger(limitAsString);
		}

		Connection conn = ConnectionFactory.getConnection();
		dtoList = new SelectListDao<StopDTO>(new SelectStopsByLatLon(lat, lng, limit), conn).execute();
		conn.close();

	}

	@Override
	public String getJson() throws JsonProcessingException {
		List<Stop> data = new DTOList2Stops(this.dtoList).getData();
		return new Gson().toJson(data);
	}

}
