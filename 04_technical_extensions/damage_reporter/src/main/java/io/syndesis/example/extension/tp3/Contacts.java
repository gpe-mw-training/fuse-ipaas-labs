package io.syndesis.example.extension.tp3;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Contacts {

	private static final List<Contact> CONTACTS = Arrays.asList(
			new Contact("John Smith", "123 456 789", "Good Inc."),
			new Contact("Joe Doe", "987 654 321", "Bad Inc.")
	);


	public static Optional<Contact> findByCompany(String company) {
		return CONTACTS.stream().filter(c -> company.equals(c.getCompany())).findFirst();
	}


}
