package de.illilli.opendata.service.publicTransportStation.jdbc;

/**
 * <pre>
 * CREATE TABLE agency (
	id integer NOT NULL,
	name character varying(256),
	url character varying(128),
	timezone character varying(40),
	lang character varying(2),
	phone character varying(40),
	fare_url character varying(128)
);
 * </pre>
 */
public class AgencyDTO {
	private int id;
	private String name;
	private String url;
	private String timezone;
	private String lang;
	private String phone;
	private String fareUrl;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFareUrl() {
		return fareUrl;
	}

	public void setFareUrl(String fareUrl) {
		this.fareUrl = fareUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fareUrl == null) ? 0 : fareUrl.hashCode());
		result = prime * result + id;
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((timezone == null) ? 0 : timezone.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		AgencyDTO other = (AgencyDTO) obj;
		if (fareUrl == null) {
			if (other.fareUrl != null)
				return false;
		} else if (!fareUrl.equals(other.fareUrl))
			return false;
		if (id != other.id)
			return false;
		if (lang == null) {
			if (other.lang != null)
				return false;
		} else if (!lang.equals(other.lang))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
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
		return true;
	}

	@Override
	public String toString() {
		return "AgencyDTO [id=" + id + ", name=" + name + ", url=" + url + ", timezone=" + timezone + ", lang=" + lang
				+ ", phone=" + phone + ", fareUrl=" + fareUrl + "]";
	}

}
