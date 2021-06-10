package com.addressbook.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

@Repository
public class AddressTemplateRepositoryImpl implements AddressTemplateRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<Address> getAddressesByPrefix(String prefix) {
		TextQuery textQuery = TextQuery.queryText(new TextCriteria().matching(prefix)).sortByScore();
		List<Address> result = mongoTemplate.find(textQuery, Address.class, "addresses");
		return result;
	}

}
