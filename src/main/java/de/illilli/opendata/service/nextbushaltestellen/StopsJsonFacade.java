package de.illilli.opendata.service.nextbushaltestellen;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.Facade;

public class StopsJsonFacade implements Facade {

	private List<Stop> stops;

	public StopsJsonFacade() throws JsonProcessingException, IOException {
		AskFor<List<Stop>> askFor = new AskForStops();
		stops = askFor.getData();
	}

	@Override
	public String getJson() throws JsonProcessingException {
		Gson gson = new Gson();
		return gson.toJson(stops);
	}

}
