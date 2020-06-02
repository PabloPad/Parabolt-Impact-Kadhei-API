package com.parabolt.kadhei.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parabolt.kadhei.entity.Subprocess;
import com.parabolt.kadhei.repository.SubprocessRepository;

@Service
public class SubprocessService {
	
	@Autowired
	SubprocessRepository subprocessRepository;
	
	public Subprocess createSubprocess(Subprocess subp) {
				
		return subprocessRepository.save(subp);
	}
	
	public Optional<Subprocess> getSubprocessById(int id) {
		
		return subprocessRepository.findById(id);
	}
	
	
	public List<Subprocess> getAllSubprocesses(){
		
		return subprocessRepository.findAll();
	}
	
	//provisional implementation for fetching all the subprocesses of a macroprocess - consider alternatives
	public List<Subprocess> getMacroProcesses(int macroId){
		
		List<Subprocess> allSubs = subprocessRepository.findAll();
		
		List<Subprocess> macroSubs = new ArrayList<Subprocess>();
		
		for(Subprocess sub:allSubs) {
			
			if(sub.getMacro_id()==macroId) {
				macroSubs.add(sub);
			}
			
		}
		return macroSubs;
		
	}
	
	public Optional<Subprocess> deleteSubprocess(int id) {
		
		Optional<Subprocess> sub = subprocessRepository.findById(id);
		if(sub.isPresent()) {
			subprocessRepository.deleteById(id);
		}
		return sub;
	}
	
	public Optional<Subprocess> updateSubprocess(int id, Subprocess newSub) {
		
		Optional<Subprocess> optSub = subprocessRepository.findById(id);
		if(optSub.isPresent()) {
			Subprocess sub = optSub.get();
			sub.setName(newSub.getName());
			sub.setDescription(newSub.getDescription());
			subprocessRepository.save(sub);
			optSub = Optional.of(sub);
		}
		return optSub;
	}
	
}
