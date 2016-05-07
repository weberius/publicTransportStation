package de.illilli.opendata.service.nextbushaltestellen;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "stop_id", "stop_code", "stop_name", "stop_desc", "stop_lat", "stop_lon", "zone_id", "stop_url",
		"location_type", "parent_station", "stop_timezone" })
public class Stop {

	@JsonProperty("stop_id")
	private String id;
	@JsonProperty("stop_code")
	private String code;
	@JsonProperty("stop_name")
	private String name;
	@JsonProperty("stop_desc")
	private String desc;
	@JsonProperty("stop_lat")
	private String lat;
	@JsonProperty("stop_lon")
	private String lon;
	@JsonProperty("zone_id")
	private String zoneId;
	@JsonProperty("stop_url")
	private String url;
	@JsonProperty("location_type")
	private String locationType;
	@JsonProperty("parentStation")
	private String parent_station;
	@JsonProperty("stop_timezone")
	private String stopTimezone;

	public int getId() {
		return Integer.parseInt(id);
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public double getLat() {
		return Double.parseDouble(lat);
	}

	public double getLon() {
		return Double.parseDouble(lon);
	}

	public String getZoneId() {
		return zoneId;
	}

	public String getUrl() {
		return url;
	}

	public String getLocationType() {
		return locationType;
	}

	public String getParent_station() {
		return parent_station;
	}

	public String getStopTimezone() {
		return stopTimezone;
	}

}
