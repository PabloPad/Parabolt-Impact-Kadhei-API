package com.parabolt.kadhei.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parabolt.kadhei.entity.Activity;
import com.parabolt.kadhei.repository.ActivityRepository;


@Service
public class ActivityService {
	
	@Autowired
	ActivityRepository activityRepository;
	
	public Activity createActivity(Activity activity) {
				
		return activityRepository.save(activity);
	}
	
	public Optional<Activity> getActivityById(int id) {
		
		return activityRepository.findById(id);
	}
	
	
	public List<Activity> getAllActivities(){
		
		return activityRepository.findAll();
	}
	
	//provisional implementation for fetching all the activities of a subprocess - consider alternatives
		public List<Activity> getProcessActivities(int processId){
			
			List<Activity> allActs = activityRepository.findAll();
			
			List<Activity> subActs = new ArrayList<Activity>();
			
			for(Activity sub:allActs) {
				
				if(sub.getSubprocess_id() == processId) {
					subActs.add(sub);
				}
				
			}
			return subActs;
			
		}
		
		public Optional<Activity> deleteActivity(int id) {
			
			Optional<Activity> act = activityRepository.findById(id);
			if(act.isPresent()) {
				activityRepository.deleteById(id);
			}
			return act;
		}
	
		public Optional<Activity> updateActivity(int id, Activity newAct) {
			
			Optional<Activity> foundAct = activityRepository.findById(id);
			if(foundAct.isPresent()) {
				Activity act = foundAct.get();
				act.setName(newAct.getName());
				act.setDescription(newAct.getDescription());
				activityRepository.save(act);
				foundAct = Optional.of(act);
			}
			return foundAct;
		}
}



