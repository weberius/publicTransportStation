package de.illilli.opendata.service.publicTransportStation.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.InsertOrUpdate;

/**
 * <pre>
 * INSERT INTO 
  stop(
    id,
    code,
    name,
    descrition,
    lat,
    lon,
    zoneid,
    url,
    locationtype,
    parentstation,
    timezone,
    geom
  )
VALUES
  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
 * </pre>
 *
 */
public class InsertStop implements InsertOrUpdate {

	static final String sqlFileName = "/sql/insertStop.sql";
	private StopDTO dto;

	public InsertStop(StopDTO dto) {
		this.dto = dto;
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { //
				this.dto.getId(), //
				dto.getCode(), //
				dto.getName(), //
				dto.getDescrition(), //
				dto.getLat(), //
				dto.getLon(), //
				dto.getZoneId(), //
				dto.getUrl(), //
				dto.getLocationtype(), //
				dto.getParentStation(), //
				dto.getTimezone(), //
				dto.getGeom() //
		};
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = InsertStop.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

}
