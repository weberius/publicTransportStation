package de.illilli.opendata.service.publicTransportStation.jdbc;

import org.onebusaway.gtfs.model.Stop;
import org.postgis.PGgeometry;

public class Stop2DTO extends StopDTO {

	public Stop2DTO(Stop stop) {

		super.setId(Integer.parseInt(stop.getId().getId()));
		super.setCode(stop.getCode());
		super.setName(stop.getName());
		super.setDescrition(stop.getDesc());
		super.setLat(stop.getLat());
		super.setLon(stop.getLon());
		super.setZoneId(stop.getZoneId());
		super.setUrl(stop.getUrl());
		super.setLocationtype(stop.getLocationType());
		super.setParentStation(stop.getParentStation());
		super.setTimezone(stop.getTimezone());

		org.postgis.Geometry pgPoint = new org.postgis.Point(stop.getLon(), stop.getLat());
		pgPoint.setSrid(4326);
		PGgeometry geom = new PGgeometry(pgPoint);
		super.setGeom(geom);
	}

}
