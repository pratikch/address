package com.addressbook.address;

import java.util.List;

import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {

	public Address findByZip(String firstName);

	public List<Address> findByCity(String lastName);

	public List<Address> findByState(String state);

	public List<Address> findAllBy(TextCriteria criteria);

}
