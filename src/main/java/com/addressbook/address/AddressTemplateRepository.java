package com.addressbook.address;

import java.util.List;

public interface AddressTemplateRepository {
	public List<Address> getAddressesByPrefix(String prefix);
}
