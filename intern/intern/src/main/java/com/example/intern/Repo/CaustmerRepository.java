package com.example.intern.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.intern.Registration.Caustmer;

@Repository
public interface CaustmerRepository extends MongoRepository<Caustmer, String>{
	Caustmer findByPhone(long phone);
	Caustmer findByEmail(String Email);

}
