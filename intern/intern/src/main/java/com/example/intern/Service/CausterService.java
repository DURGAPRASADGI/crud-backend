package com.example.intern.Service;

import java.util.List;

import com.example.intern.Expectionhandling.AlreadyExistCaustemer;
import com.example.intern.Registration.Caustmer;

import jakarta.validation.Valid;

public interface CausterService {

	Caustmer saveCauster(@Valid Caustmer caustmer) throws AlreadyExistCaustemer ;

	List<Caustmer> getAllCaustmer();

	String deleteCaustmer(String id);

	Caustmer getCaustmer(String id);

}
