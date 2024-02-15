package com.example.intern.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.intern.Expectionhandling.AlreadyExistCaustemer;
import com.example.intern.Registration.Caustmer;
import com.example.intern.Repo.CaustmerRepository;

import jakarta.validation.Valid;

@Service
public class CaustmerServiceimpl implements CausterService {
	@Autowired
	private CaustmerRepository caustmerRepository;

	@Override
	public Caustmer saveCauster(@Valid Caustmer caustmer) throws AlreadyExistCaustemer{
		Caustmer existphone=caustmerRepository.findByPhone(caustmer.getPhone());
		Caustmer existEmail=caustmerRepository.findByEmail(caustmer.getEmail());
		if(existphone!=null) {
			throw new AlreadyExistCaustemer("the phone already exist"+caustmer.getPhone());
			
		}
		if(existEmail!=null) {
			throw new AlreadyExistCaustemer("the Email already exist"+caustmer.getEmail());
		}
		return caustmerRepository.save(caustmer);
	}

	@Override
	public List<Caustmer> getAllCaustmer() {
		
		return caustmerRepository.findAll();
	}

	@Override
	public String deleteCaustmer(String id) {
		caustmerRepository.deleteById(id);
		return "deleted";
		
		
		
	}

	@Override
	public Caustmer getCaustmer(String id) {
		  Optional<Caustmer> optionalCaustmer = caustmerRepository.findById(id);
		    if (optionalCaustmer.isPresent()) {
		        return optionalCaustmer.get();
		    } else {
		        throw new IllegalArgumentException("Caustmer not found");
		    }
	}

}
