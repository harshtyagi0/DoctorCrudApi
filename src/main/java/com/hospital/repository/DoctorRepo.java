package com.hospital.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hospital.entity.Doctor;

@Repository
public interface DoctorRepo extends MongoRepository<Doctor, String> {

	Doctor findByEmail(String email);
	
	Doctor findByNumber(String number);

}
