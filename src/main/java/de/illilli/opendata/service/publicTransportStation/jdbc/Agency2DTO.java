package de.illilli.opendata.service.publicTransportStation.jdbc;

import org.onebusaway.gtfs.model.Agency;

/**
 * <pre>
 * CREATE TABLE agency (
	id integer NOT NULL,
	name character varying(256),
	url character varying(128),
	timezone character varying(40),
	lang character varying(2),
	phone character varying(40),
	fare_url character varying(128)
);
 * </pre>
 */
public class Agency2DTO extends AgencyDTO {

	public Agency2DTO(Agency agency) {
		super.setId(Integer.parseInt(agency.getId()));
		super.setName(agency.getName());
		super.setUrl(agency.getUrl());
		super.setTimezone(agency.getTimezone());
		super.setLang(agency.getLang());
		super.setPhone(agency.getPhone());
		super.setFareUrl(agency.getFareUrl());
	}
}
