package de.illilli.opendata.service.publicTransportStation.jdbc;

import java.io.IOException;

import de.illilli.jdbc.Select;

public class SelectStopsByBoundingBox extends SelectStops implements Select<StopDTO> {

	private double topx;
	private double topy;
	private double bottomx;
	private double bottomy;
	private int srid = 4326;

	public SelectStopsByBoundingBox(double topx, double topy, double bottomx, double bottomy) {
		this.topx = topx;
		this.topy = topy;
		this.bottomx = bottomx;
		this.bottomy = bottomy;
	}

	@Override
	public String getSql() throws IOException {
		StringBuffer sql = new StringBuffer(super.getSql());
		sql.append("\n");
		sql.append("WHERE\n  geom && ST_MakeEnvelope(?, ?, ?, ?, ?) ");
		return sql.toString();
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { this.topy, this.topx, this.bottomy, this.bottomx, this.srid };

	}
}
