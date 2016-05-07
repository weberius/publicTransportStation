package de.illilli.opendata.service.nextbushaltestellen;

import java.io.IOException;
import java.util.List;

import org.geojson.Feature;
import org.geojson.FeatureCollection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.GeoJson;

public class StopsGeojsonFacade implements Facade {

	private FeatureCollection featureCollection = new FeatureCollection();

	public StopsGeojsonFacade() throws JsonProcessingException, IOException {
		AskFor<List<Stop>> askFor = new AskForStops();
		List<Stop> stops = askFor.getData();

		for (Stop stop : stops) {
			GeoJson gjo = new Stop2GeoJson(stop).getGeoJson();
			Feature feature = new Feature();
			feature.setGeometry(gjo.getGeometry());
			feature.setId(gjo.getId());
			feature.setProperties(gjo.getProperties());
			featureCollection.add(feature);
		}
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
