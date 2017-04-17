package de.illilli.opendata.service.publicTransportStation.model;

import java.util.HashMap;
import java.util.Map;

import org.geojson.Feature;
import org.geojson.Point;

import de.illilli.opendata.service.publicTransportStation.jdbc.StopDTO;

public class Stop2GeoJson {

	private Feature feature = new Feature();

	public Stop2GeoJson(StopDTO dto) {

		feature.setId(dto.getId() + "");

		Map<String, Object> properties = new HashMap<>();
		OrtAndNameTokenizer oant = new OrtAndNameTokenizer(dto.getName());
		properties.put("ort", oant.getOrt());
		properties.put("name", oant.getName());
		properties.put("distance", dto.getDistance());
		properties.put("typ", "Stop");

		feature.setProperties(properties);

		Point point = new Point(dto.getLon(), dto.getLat());
		feature.setGeometry(point);

	}

	public Feature getFeature() {
		return feature;
	}

}
