package de.illilli.opendata.service.publicTransportStation.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.InsertOrUpdate;

/**
 * <pre>
 * 
  INSERT INTO 
    agency(
      id,
      name,
      url,
      timezone,
      lang,
      phone,
      fare_url
    )
  VALUES
    (?, ?, ?, ?, ?, ?, ?)
 * 
 * </pre>
 */
public class InsertAgency implements InsertOrUpdate {

	static final String sqlFileName = "/sql/insertAgency.sql";
	private AgencyDTO dto;

	public InsertAgency(AgencyDTO dto) {
		this.dto = dto;

	}

	@Override
	public Object[] getParameter() {
		return new Object[] { //
				this.dto.getId(), //
				dto.getName(), //
				dto.getUrl(), //
				dto.getTimezone(), //
				dto.getLang(), //
				dto.getPhone(), //
				dto.getFareUrl() //
		};
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = InsertStop.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}
}
