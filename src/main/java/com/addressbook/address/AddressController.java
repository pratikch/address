package com.addressbook.address;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AddressController {

	@Autowired
	AddressRepository repository;

	@Autowired
	AddressTemplateRepository template;

	@GetMapping("/addresses")
	public ResponseEntity<List<Address>> getAllAddresses(@RequestParam(required = false) String prefix) {

		try {
			List<Address> addresses = new ArrayList<Address>();

			if (prefix != null && prefix.length() >=1) {
				template.getAddressesByPrefix(prefix).forEach(addresses::add);

			} else {
				repository.findAll().forEach(addresses::add);
			}

			if (addresses.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(addresses, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/addresses/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable("id") String id) {
		Optional<Address> add = repository.findById(id);

		if (add.isPresent()) {
			return new ResponseEntity<>(add.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addresses")
	public ResponseEntity<Address> createAddress(@RequestBody Address address) {
		try {
			Address _address = repository.save(address);
			return new ResponseEntity<>(_address, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/addresses/{id}")
	public ResponseEntity<Address> updateAddress(@PathVariable("id") String id, @RequestBody Address add) {
		Optional<Address> old = repository.findById(id);

		if (old.isPresent()) {
			Address _add = old.get();
			_add.setLine1(add.getLine1());
			_add.setLine2(add.getLine2());
			_add.setCity(add.getCity());
			_add.setState(add.getState());
			_add.setZip(add.getZip());
			return new ResponseEntity<>(repository.save(_add), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/addresses/{id}")
	public ResponseEntity<HttpStatus> deleteAddress(@PathVariable("id") String id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/addresses")
	public ResponseEntity<HttpStatus> deleteAllAddresses() {
		try {
			repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
