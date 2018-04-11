package io.syndesis.example.extension.tp3;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "inventoryReceived")
public class InventoryList {

	@JacksonXmlElementWrapper(localName = "items", useWrapping = false)
	@JacksonXmlProperty(localName = "item")
	private List<Item> items;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		InventoryList that = (InventoryList) o;

		return items != null ? items.equals(that.items) : that.items == null;
	}

	@Override
	public int hashCode() {
		return items != null ? items.hashCode() : 0;
	}
}


