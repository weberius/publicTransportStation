package de.illilli.opendata.service.publicTransportStation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.geojson.Feature;
import org.geojson.FeatureCollection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.publicTransportStation.jdbc.StopDTO;
import de.illilli.opendata.service.publicTransportStation.model.Stop2GeoJson;

public class StopsByBoundigBoxGeoJsonFacade extends StopsByBoundigBoxFacade implements Facade {

	private FeatureCollection featureCollection = new FeatureCollection();
	private List<Feature> featureList = new ArrayList<Feature>();

	public StopsByBoundigBoxGeoJsonFacade(String bbox) throws SQLException, IOException, NamingException {
		super(bbox);
		for (StopDTO dto : dtoList) {
			featureList.add(new Stop2GeoJson(dto).getFeature());
		}
		featureCollection.addAll(featureList);
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
