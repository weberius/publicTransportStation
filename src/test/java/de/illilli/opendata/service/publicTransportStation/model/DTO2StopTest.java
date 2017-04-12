package de.illilli.opendata.service.publicTransportStation.model;

import org.junit.Assert;
import org.junit.Test;

import de.illilli.opendata.service.publicTransportStation.jdbc.StopDTO;

public class DTO2StopTest {

	@Test
	public void testDTO2StopWithKommaOrt() {
		StopDTO dto = new StopDTO();
		dto.setName("Köln, Dom / Hbf");
		Stop stop = new DTO2Stop(dto);
		String expected = "Köln";
		String actual = stop.getOrt();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDTO2StopWithKommaName() {
		StopDTO dto = new StopDTO();
		dto.setName("Köln, Dom / Hbf");
		Stop stop = new DTO2Stop(dto);
		String expected = "Dom / Hbf";
		String actual = stop.getName();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDTO2StopWithoutKommaOrt() {
		StopDTO dto = new StopDTO();
		dto.setName("Lohmar Stadthaus");
		Stop stop = new DTO2Stop(dto);
		String expected = null;
		String actual = stop.getOrt();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDTO2StopWithoutKommaName() {
		StopDTO dto = new StopDTO();
		dto.setName("Lohmar Stadthaus");
		Stop stop = new DTO2Stop(dto);
		String expected = "Lohmar Stadthaus";
		String actual = stop.getName();
		Assert.assertEquals(expected, actual);
	}

}
