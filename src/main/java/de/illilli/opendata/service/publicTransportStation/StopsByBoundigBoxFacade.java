package de.illilli.opendata.service.publicTransportStation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.TopLeftBottomRightFromString;
import de.illilli.opendata.service.publicTransportStation.jdbc.SelectStopsByBoundingBox;
import de.illilli.opendata.service.publicTransportStation.jdbc.StopDTO;
import de.illilli.opendata.service.publicTransportStation.model.DTOList2Stops;
import de.illilli.opendata.service.publicTransportStation.model.Stop;

public class StopsByBoundigBoxFacade implements Facade {

	List<StopDTO> dtoList;

	public StopsByBoundigBoxFacade(String bbox) throws SQLException, IOException, NamingException {

		double topx = new TopLeftBottomRightFromString(bbox).getTopx();
		double topy = new TopLeftBottomRightFromString(bbox).getTopy();
		double bottomx = new TopLeftBottomRightFromString(bbox).getBottomx();
		double bottomy = new TopLeftBottomRightFromString(bbox).getBottomy();

		Connection conn = ConnectionFactory.getConnection();
		dtoList = new SelectListDao<StopDTO>(new SelectStopsByBoundingBox(topx, topy, bottomx, bottomy), conn)
				.execute();
		conn.close();

	}

	@Override
	public String getJson() throws JsonProcessingException {
		List<Stop> data = new DTOList2Stops(this.dtoList).getData();
		return new Gson().toJson(data);
	}

}
