package de.illilli.opendata.service.publicTransportStation.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.InsertOrUpdate;

public class DeleteAgencys implements InsertOrUpdate {

	static final String sqlFileName = "/sql/deleteAgencys.sql";

	@Override
	public Object[] getParameter() {
		return new Object[0];
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = DeleteStops.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

}
