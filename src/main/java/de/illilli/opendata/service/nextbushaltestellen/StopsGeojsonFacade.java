package de.illilli.opendata.service.nextbushaltestellen;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.illilli.opendata.service.Facade;

public class StopsGeojsonFacade implements Facade {

	String json = "";

	public String getJson() throws JsonProcessingException {
		return json;
	}

}
