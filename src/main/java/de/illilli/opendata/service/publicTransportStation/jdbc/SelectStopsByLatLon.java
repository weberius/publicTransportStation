package de.illilli.opendata.service.publicTransportStation.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.Select;

public class SelectStopsByLatLon extends SelectStops implements Select<StopDTO> {

	static final String sqlFileName = "/sql/selectStopsByLatLon.sql";

	private double lat;
	private double lng;
	private int limit;

	public SelectStopsByLatLon(double lat, double lng, int limit) {
		this.lat = lat;
		this.lng = lng;
		this.limit = limit;
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = SelectStops.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { this.lng, this.lat, this.limit };
	}

}
