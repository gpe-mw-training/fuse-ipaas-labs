package io.syndesis.example.extension.tp3;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TodoReport {

	private String task;

	public TodoReport(List<Item> damagedItems) {
		this.task = generateForTodo(damagedItems);
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public static String generateForTodo(List<Item> damagedItems){
		List<String> added = new LinkedList<>();

		Map<String, List<Item>> itemsPerVendorMap = damagedItems.stream().collect(Collectors.groupingBy(Item::getVendor));

		StringBuilder report = new StringBuilder();
		report.append("Task: ");

		itemsPerVendorMap.forEach((k,v) -> {
			Optional<Contact> contactForCompany = Contacts.findByCompany(k);
			if (contactForCompany.isPresent()) {
				report.append("Contact ").append(contactForCompany.get().getName()).append(", ")
						.append(contactForCompany.get().getPhoneNumber()).append(". ");
			} else {
				report.append("No contact found. ");
			}
			String listAsString = v.stream()
					.map(Item::getId).collect(Collectors.joining("|"));
			report.append("Damaged items: ").append(listAsString).append(". ");

		});
		return  report.toString();
	}
}
