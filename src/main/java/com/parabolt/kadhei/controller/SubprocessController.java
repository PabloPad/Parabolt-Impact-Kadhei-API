package com.parabolt.kadhei.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.parabolt.kadhei.entity.Subprocess;
import com.parabolt.kadhei.service.SubprocessService;

@Controller
@CrossOrigin
@RequestMapping("/home")
public class SubprocessController {

	@Autowired
	SubprocessService subprocessService;
	
	@PostMapping("/processes")
	public ResponseEntity<?> createSubprocess(@RequestBody Subprocess subp) {
		
		Subprocess newSubp = subprocessService.createSubprocess(subp);
		return new ResponseEntity<Subprocess>(newSubp,HttpStatus.OK);
	}
	
	@GetMapping("/processes/{id}")
	public ResponseEntity<?> getSubprocessById(@PathVariable int id) {
		
		Optional<Subprocess> foundSubprocess = subprocessService.getSubprocessById(id);
		if(foundSubprocess.isPresent()) {
			return new ResponseEntity<Subprocess>(foundSubprocess.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/processes")
	public ResponseEntity<?> getAllSubprocesses() {
		
		List<Subprocess> subs = subprocessService.getAllSubprocesses();
		return new ResponseEntity<List<Subprocess>>(subs,HttpStatus.OK);
		
	}
	
	@GetMapping("/subprocesses")
	public ResponseEntity<?> getSubprocesses(@RequestParam int macroId) {
		
		List<Subprocess> macroSubs = subprocessService.getMacroProcesses(macroId);
		return new ResponseEntity<List<Subprocess>>(macroSubs,HttpStatus.OK);
		
	}
	
	
	
}
