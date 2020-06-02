package com.parabolt.kadhei.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parabolt.kadhei.entity.Macro;
import com.parabolt.kadhei.repository.MacroRepository;

@Service
public class MacroService {
	
	@Autowired
	MacroRepository macroRepository;
	
	public Macro createMacro(Macro macro) {
				
		return macroRepository.save(macro);
	}
	
	public Optional<Macro> getMacroById(int id) {
		
		return macroRepository.findById(id);
	}
	
	
	public List<Macro> getAllMacros(){
		
		return macroRepository.findAll();
	}
	
	public Optional<Macro> deleteMacro(int id) {
		
		Optional<Macro> macro = getMacroById(id);
		if(macro.isPresent()) {
			macroRepository.deleteById(id);
		}

		return macro;
	}
	
	public Optional<Macro> updateMacro(int id, Macro newMacro) {
		
		Optional<Macro> foundMacro = macroRepository.findById(id);
		if(foundMacro.isPresent()) {
			Macro macro = foundMacro.get();
			macro.setName(newMacro.getName());
			macro.setDescription(newMacro.getDescription());
			macroRepository.save(macro);
			foundMacro = Optional.of(macro);
		}
		
		return foundMacro;
	}
}
