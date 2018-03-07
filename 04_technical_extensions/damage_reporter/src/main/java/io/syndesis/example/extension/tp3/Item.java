package io.syndesis.example.extension.tp3;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Item {

	@JacksonXmlProperty(localName = "id", isAttribute = true)
	private String id;

	@JacksonXmlProperty(localName = "vendor", isAttribute = true)
	private String vendor;

	@JacksonXmlProperty(localName = "damaged", isAttribute = true)
	private boolean isDamaged;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public boolean isDamaged() {
		return isDamaged;
	}

	public void setDamaged(boolean damaged) {
		isDamaged = damaged;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Item item = (Item) o;

		return id != null ? id.equals(item.id) : item.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
