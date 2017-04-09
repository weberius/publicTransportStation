package de.illilli.opendata.service.publicTransportStation.jdbc;

import org.postgis.PGgeometry;

/**
 * <pre>
	CREATE TABLE stop (
	    id integer NOT NULL,
	    code character varying(40),
	    name character varying(40),
	    descrition character varying(256),
	    lat double precision,
	    lon double precision,
	    zoneid character varying(40),
	    url character varying(128),
	    locationtype integer,
	    parentstation character varying(40),
	    timezone character varying(40)
	);	
	SELECT AddGeometryColumn ('public','stops','geom',4326,'POINT',2);
 * </pre>
 */
public class StopDTO {

	private int id;
	private String code;
	private String name;
	private String descrition;
	private double lat;
	private double lon;
	private String zoneId;
	private String url;
	private int locationtype;
	private String parentStation;
	private String timezone;
	private PGgeometry geom;
	private String geojson;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getLocationtype() {
		return locationtype;
	}

	public void setLocationtype(int locationtype) {
		this.locationtype = locationtype;
	}

	public String getParentStation() {
		return parentStation;
	}

	public void setParentStation(String parentStation) {
		this.parentStation = parentStation;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public PGgeometry getGeom() {
		return geom;
	}

	public void setGeom(PGgeometry geom) {
		this.geom = geom;
	}

	public String getGeojson() {
		return geojson;
	}

	public void setGeojson(String geojson) {
		this.geojson = geojson;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((descrition == null) ? 0 : descrition.hashCode());
		result = prime * result + ((geojson == null) ? 0 : geojson.hashCode());
		result = prime * result + ((geom == null) ? 0 : geom.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(lat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + locationtype;
		temp = Double.doubleToLongBits(lon);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentStation == null) ? 0 : parentStation.hashCode());
		result = prime * result + ((timezone == null) ? 0 : timezone.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((zoneId == null) ? 0 : zoneId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StopDTO other = (StopDTO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (descrition == null) {
			if (other.descrition != null)
				return false;
		} else if (!descrition.equals(other.descrition))
			return false;
		if (geojson == null) {
			if (other.geojson != null)
				return false;
		} else if (!geojson.equals(other.geojson))
			return false;
		if (geom == null) {
			if (other.geom != null)
				return false;
		} else if (!geom.equals(other.geom))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
			return false;
		if (locationtype != other.locationtype)
			return false;
		if (Double.doubleToLongBits(lon) != Double.doubleToLongBits(other.lon))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentStation == null) {
			if (other.parentStation != null)
				return false;
		} else if (!parentStation.equals(other.parentStation))
			return false;
		if (timezone == null) {
			if (other.timezone != null)
				return false;
		} else if (!timezone.equals(other.timezone))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (zoneId == null) {
			if (other.zoneId != null)
				return false;
		} else if (!zoneId.equals(other.zoneId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StopDTO [id=" + id + ", code=" + code + ", name=" + name + ", descrition=" + descrition + ", lat=" + lat
				+ ", lon=" + lon + ", zoneId=" + zoneId + ", url=" + url + ", locationtype=" + locationtype
				+ ", parentStation=" + parentStation + ", timezone=" + timezone + ", geom=" + geom + ", geojson="
				+ geojson + "]";
	}

}
