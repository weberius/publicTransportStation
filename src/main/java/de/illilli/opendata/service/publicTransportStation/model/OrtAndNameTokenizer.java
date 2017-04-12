package de.illilli.opendata.service.publicTransportStation.model;

public class OrtAndNameTokenizer {

	private String ort;
	private String name;

	public OrtAndNameTokenizer(String name) {
		if (name.contains(",")) {
			this.ort = name.substring(0, name.indexOf(',')).trim();
			this.name = name.substring(name.indexOf(',') + 1, name.length()).trim();
		} else {
			this.name = name;
		}
	}

	public String getOrt() {
		return ort;
	}

	public String getName() {
		return name;
	}

}
