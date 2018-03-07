package io.syndesis.example.extension.tp3;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryListTest {

	private String xmlData = "<inventoryReceived>\n" +
			                 "  <item id=\"XYZ123\" damaged=\"false\" vendor=\"Good Inc\" />\n" +
							 "  <item id=\"ABC789\" damaged=\"true\" vendor=\"Bad Inc.\" />\n" +
							 "</inventoryReceived>";
	@Test
	public void listSizeTest() throws IOException {
		ObjectMapper mapper = new XmlMapper();

		InventoryList inventoryList = mapper.readValue(xmlData, InventoryList.class);

		Assert.assertNotNull(inventoryList);
		Assert.assertEquals(2, inventoryList.getItems().size());
	}

	@Test
	public void notDamagedItemTest() throws IOException {
		ObjectMapper mapper = new XmlMapper();

		InventoryList inventoryList = mapper.readValue(xmlData, InventoryList.class);

		Assert.assertNotNull(inventoryList);
		List<Item> notDamagedList = inventoryList.getItems().stream().filter(i -> !i.isDamaged()).collect(Collectors.toList());
		Assert.assertEquals(1, notDamagedList.size());
	}

	@Test
	public void damagedItemTest() throws IOException {
		ObjectMapper mapper = new XmlMapper();

		InventoryList inventoryList = mapper.readValue(xmlData, InventoryList.class);

		Assert.assertNotNull(inventoryList);
		List<Item> damagedList = inventoryList.getItems().stream().filter(Item::isDamaged).collect(Collectors.toList());
		Assert.assertEquals(1, damagedList.size());
	}
}
