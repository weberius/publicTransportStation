package de.illilli.opendata.service.publicTransportStation.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.Select;

public class SelectStops implements Select<StopDTO> {

	static final String sqlFileName = "/sql/selectStops.sql";

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = SelectStops.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

	@Override
	public Object[] getParameter() {
		return new Object[0];
	}

	@Override
	public Class<StopDTO> getDtoClazz() {
		return StopDTO.class;
	}

}
