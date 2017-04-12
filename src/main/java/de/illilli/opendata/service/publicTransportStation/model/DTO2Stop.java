package de.illilli.opendata.service.publicTransportStation.model;

import de.illilli.opendata.service.publicTransportStation.jdbc.StopDTO;

public class DTO2Stop extends Stop {

	public DTO2Stop(StopDTO dto) {

		super.setId(dto.getId());
		super.setLat(dto.getLat());
		super.setLon(dto.getLon());

		OrtAndNameTokenizer oant = new OrtAndNameTokenizer(dto.getName());
		super.setOrt(oant.getOrt());
		super.setName(oant.getName());
	}

}
