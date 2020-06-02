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

import com.parabolt.kadhei.entity.Activity;
import com.parabolt.kadhei.service.ActivityService;

@Controller
@CrossOrigin
@RequestMapping("/home")
public class ActivityController {

	@Autowired
	ActivityService activityService;
	
	@PostMapping("/activities")
	public ResponseEntity<?> createActivity(@RequestBody Activity act) {
		
		Activity newAct = activityService.createActivity(act);
		return new ResponseEntity<Activity>(newAct,HttpStatus.OK);
	}
	
	@GetMapping("/activities/{id}")
	public ResponseEntity<?> getActivityById(@PathVariable int id) {
		
		Optional<Activity> foundActivity = activityService.getActivityById(id);
		if(foundActivity.isPresent()) {
			return new ResponseEntity<Activity>(foundActivity.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/activities")
	public ResponseEntity<?> getAllActivities() {
		
		List<Activity> subs = activityService.getAllActivities();
		return new ResponseEntity<List<Activity>>(subs,HttpStatus.OK);
		
	}
	
	@GetMapping("/{processId}/activities")
	public ResponseEntity<?> getActivities(@PathVariable int processId) {
		
		List<Activity> processActs = activityService.getProcessActivities(processId);
		return new ResponseEntity<List<Activity>>(processActs,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/activities/{id}")
	public ResponseEntity<?> deleteActivityById(@PathVariable int id) {
		
		Optional<Activity> foundActivity = activityService.deleteActivity(id);
		if(foundActivity.isPresent()) {
			return new ResponseEntity<Activity>(foundActivity.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/activities/{id}")
	public ResponseEntity<?> updateActivityById(@PathVariable int id, @RequestBody Activity newAct) {
		
		Optional<Activity> foundActivity = activityService.updateActivity(id, newAct);
		if(foundActivity.isPresent()) {
			return new ResponseEntity<Activity>(foundActivity.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
}
