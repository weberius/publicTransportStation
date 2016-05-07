package de.illilli.opendata.service.nextbushaltestellen;

import java.util.Hashtable;
import java.util.Map;

import org.geojson.GeoJsonObject;
import org.geojson.LngLatAlt;
import org.geojson.Point;

import de.illilli.opendata.service.GeoJson;

public class Stop2GeoJson {

	private GeoJson geoJson;

	public Stop2GeoJson(Stop stop) {

		String id = Integer.toString(stop.getId());

		LngLatAlt coordinate = new LngLatAlt();
		coordinate.setLatitude(stop.getLat());
		coordinate.setLongitude(stop.getLng());
		GeoJsonObject geometry = new Point(coordinate);

		Map<String, Object> properties = new Hashtable<String, Object>();
		properties.put("name", stop.getName());

		geoJson = new GeoJson(id, properties, geometry);

	}

	public GeoJson getGeoJson() {
		return geoJson;
	}
}
