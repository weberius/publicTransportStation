package de.illilli.opendata.service.publicTransportStation.model;

import de.illilli.opendata.service.publicTransportStation.jdbc.StopDTO;

public class DTO2Stop extends Stop {

	public DTO2Stop(StopDTO dto) {

		super.setId(dto.getId());
		super.setLat(dto.getLat());
		super.setLon(dto.getLon());

		String name = dto.getName();
		if (name.contains(",")) {
			super.setOrt(name.substring(0, name.indexOf(',')).trim());
			super.setName(name.substring(name.indexOf(',') + 1, name.length()).trim());
		} else {
			super.setName(name);
		}
	}

}
