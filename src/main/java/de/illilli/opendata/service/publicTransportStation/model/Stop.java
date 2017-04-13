package de.illilli.opendata.service.publicTransportStation.model;

/**
 * <p>
 * Dies ist die Model Klasse für eine Haltestelle. Die Felder id, lat und lon
 * werden aus der Datenbank gelesen. Name und ort werden aus dem Datenbank-Feld
 * name interpretiert. Dabei gilt, dass wenn ein Komma vorhanden ist, der
 * vordere Teil als Ort interpretiert wird und der hintere Teil als Name. Ist
 * das Komma nicht vorhanden, wird kein Ort gesetzt.
 * </p>
 * <p>
 * Bsp. mit Komma: "Köln, Dom / Hbf".
 * <ul>
 * <li>ort: "Köln"</li>
 * <li>name: "Dom/ Hbf"</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Bsp. ohne Komma: "Lohmar Stadthaus".
 * <ul>
 * <li>ort: null</li>
 * <li>name: "Lohmar Stadthaus"</li>
 * </ul>
 * </p>
 */
public class Stop {

	private int id;
	private String name;
	private String ort;
	private double lat;
	private double lon;
	private double distance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
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

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Stop [id=" + id + ", name=" + name + ", ort=" + ort + ", lat=" + lat + ", lon=" + lon + ", distance="
				+ distance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		temp = Double.doubleToLongBits(lat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lon);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((ort == null) ? 0 : ort.hashCode());
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
		Stop other = (Stop) obj;
		if (Double.doubleToLongBits(distance) != Double.doubleToLongBits(other.distance))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
			return false;
		if (Double.doubleToLongBits(lon) != Double.doubleToLongBits(other.lon))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ort == null) {
			if (other.ort != null)
				return false;
		} else if (!ort.equals(other.ort))
			return false;
		return true;
	}

}
