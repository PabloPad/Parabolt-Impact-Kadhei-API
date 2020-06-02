package com.parabolt.kadhei.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parabolt.kadhei.entity.Macro;
import com.parabolt.kadhei.service.MacroService;

@Controller
@CrossOrigin
@RequestMapping("/home")
public class MacroController {

	@Autowired
	MacroService macroService;
	
	@PostMapping("/macros")
	public ResponseEntity<?> createMacro(@RequestBody Macro macro) {
		
		Macro newMacro = macroService.createMacro(macro);
		return new ResponseEntity<Macro>(newMacro,HttpStatus.OK);
	}
	
	@GetMapping("/macros/{id}")
	public ResponseEntity<?> getMacroById(@PathVariable int id) {
		
		Optional<Macro> foundMacro = macroService.getMacroById(id);
		if(foundMacro.isPresent()) {
			return new ResponseEntity<Macro>(foundMacro.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/macros")
	public ResponseEntity<?> getAllMacros() {
		
		List<Macro> macros = macroService.getAllMacros();
		return new ResponseEntity<List<Macro>>(macros,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/macros/{id}")
	public ResponseEntity<?> deleteMacroById(@PathVariable int id) {
		
		Optional<Macro> foundMacro = macroService.deleteMacro(id);
		if(foundMacro.isPresent()) {
			return new ResponseEntity<Macro>(foundMacro.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/macros/{id}")
	public ResponseEntity<?> updateMacroById(@PathVariable int id, @RequestBody Macro newMacro) {
		
		Optional<Macro> foundMacro = macroService.updateMacro(id, newMacro);
		if(foundMacro.isPresent()) {
			return new ResponseEntity<Macro>(foundMacro.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	
	
	
}
