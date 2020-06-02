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

import com.parabolt.kadhei.entity.Subtask;
import com.parabolt.kadhei.service.SubtaskService;

@Controller
@CrossOrigin
@RequestMapping("/home")
public class SubtaskController {

	@Autowired
	SubtaskService subtaskService;
	
	@PostMapping("/subtasks")
	public ResponseEntity<?> createSubtask(@RequestBody Subtask task) {
		
		Subtask newTask = subtaskService.createSubtask(task);
		return new ResponseEntity<Subtask>(newTask,HttpStatus.OK);
	}
	
	@GetMapping("/subtasks/{id}")
	public ResponseEntity<?> getSubtaskById(@PathVariable int id) {
		
		Optional<Subtask> foundSubtask = subtaskService.getSubtaskById(id);
		if(foundSubtask.isPresent()) {
			return new ResponseEntity<Subtask>(foundSubtask.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/subtasks")
	public ResponseEntity<?> getAllSubtasks() {
		
		List<Subtask> subTasks = subtaskService.getAllSubtasks();
		return new ResponseEntity<List<Subtask>>(subTasks,HttpStatus.OK);
		
	}
	
	@GetMapping("/{activityId}/subtasks")
	public ResponseEntity<?> getActivitySubtasks(@PathVariable int activityId) {
		
		List<Subtask> activitySubtasks = subtaskService.getActivitySubtasks(activityId);
		return new ResponseEntity<List<Subtask>>(activitySubtasks,HttpStatus.OK);
		
	}
	
	
	
}
