package de.illilli.opendata.service.nextbushaltestellen;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import de.illilli.opendata.service.AskFor;

public class AskForStops implements AskFor<List<Stop>> {

	private List<Stop> stops = new ArrayList<Stop>();

	public AskForStops() throws JsonProcessingException, IOException {
		this(AskForStops.class.getResourceAsStream("/stops.txt"));
	}

	public AskForStops(InputStream inputStream) throws JsonProcessingException, IOException {
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(Stop.class).withColumnSeparator(',').withoutHeader();
		MappingIterator<Stop> it = mapper.reader(Stop.class).with(schema).readValues(inputStream);

		while (it.hasNextValue()) {
			try {
				Stop stop = it.nextValue();
				stops.add(stop);
			} catch (JsonParseException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<Stop> getData() {
		return stops;
	}

}
