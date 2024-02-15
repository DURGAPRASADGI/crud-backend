package com.example.intern.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.intern.Expectionhandling.AlreadyExistCaustemer;
import com.example.intern.Registration.Caustmer;
import com.example.intern.Repo.CaustmerRepository;
import com.example.intern.Service.CausterService;
import com.example.intern.Update.UpdateCaustmer;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class CaustmerController {
	
	@Autowired
	private CaustmerRepository caustmerRepository;
	
	@Autowired
	private CausterService causterService;
	
	@PostMapping("/registration")
	public ResponseEntity<Caustmer> saveCaustmer(@RequestBody @Valid Caustmer caustmer) throws AlreadyExistCaustemer{
		caustmer=causterService.saveCauster(caustmer);
		return ResponseEntity.status(HttpStatus.CREATED).body(caustmer);
		
	}
	
	@GetMapping("/getAllCaustmerList")
	public ResponseEntity<List<Caustmer>> getAllCaustmer(){
		List<Caustmer> caustmers=causterService.getAllCaustmer();
		if(caustmers.size()==0) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(caustmers);
		}
		return ResponseEntity.ok(caustmers);
		
	}
	
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteCaustmer(@PathVariable String id){
		String message=causterService.deleteCaustmer(id);
		if(message.equals("deleted")) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("deleted");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Caustmer> updateCaustmer(@RequestBody UpdateCaustmer updateCaustmer, @PathVariable String id){
		Caustmer caustmer=causterService.getCaustmer(id);
		caustmer.setFristName(updateCaustmer.getFristName());
		caustmer.setLastName(updateCaustmer.getLastName());
		caustmer.setStreet(updateCaustmer.getStreet());
		caustmer.setAddress(updateCaustmer.getAddress());
		caustmer.setCity(updateCaustmer.getCity());
		caustmer.setState(caustmer.getState());
		caustmer.setEmail(updateCaustmer.getEmail());
		caustmer.setPhone(updateCaustmer.getPhone());
		caustmerRepository.save(caustmer);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(caustmer);
		
	}
	

}
