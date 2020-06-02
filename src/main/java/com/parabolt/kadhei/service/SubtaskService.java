package com.parabolt.kadhei.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parabolt.kadhei.entity.Subtask;
import com.parabolt.kadhei.repository.SubtaskRepository;


@Service
public class SubtaskService {
	
	@Autowired
	SubtaskRepository subtaskRepository;
	
	public Subtask createSubtask(Subtask subtask) {
				
		return subtaskRepository.save(subtask);
	}
	
	public Optional<Subtask> getSubtaskById(int id) {
		
		return subtaskRepository.findById(id);
	}
	
	
	public List<Subtask> getAllSubtasks(){
		
		return subtaskRepository.findAll();
	}
	
	//provisional implementation for fetching all the activities of a subprocess - consider alternatives
		public List<Subtask> getActivitySubtasks(int activityId){
			
			List<Subtask> allTasks = subtaskRepository.findAll();
			
			List<Subtask> subTasks = new ArrayList<Subtask>();
			
			for(Subtask sub:allTasks) {
				
				if(sub.getActivity_id() == activityId) {
					subTasks.add(sub);
				}
				
			}
			return subTasks;
			
		}
		
		public Optional<Subtask> deleteSubtask(int id) {
			
			Optional<Subtask> subtask = subtaskRepository.findById(id);
			if(subtask.isPresent()) {
				subtaskRepository.deleteById(id);
			}
			return subtask;
		}
		
		public Optional<Subtask> updateSubtask(int id, Subtask newTask) {
			
			Optional<Subtask> foundSubtask = subtaskRepository.findById(id);
			if(foundSubtask.isPresent()) {
				Subtask task = foundSubtask.get();
				task.setName(newTask.getName());
				task.setDescription(newTask.getDescription());
				subtaskRepository.save(task);
				foundSubtask = Optional.of(task);
			}
			return foundSubtask;
		}
	
	
}



