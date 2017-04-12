package de.illilli.opendata.service.publicTransportStation.model;

import java.util.ArrayList;
import java.util.List;

import de.illilli.opendata.service.publicTransportStation.jdbc.StopDTO;

public class DTOList2Stops {

	private List<Stop> stopList = new ArrayList<Stop>();

	public DTOList2Stops(List<StopDTO> dtoList) {
		for (StopDTO dto : dtoList) {
			stopList.add(new DTO2Stop(dto));
		}
	}

	public List<Stop> getData() {
		return stopList;
	}

}
