package com.cts.searchprofile.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.searchprofile.data.Profile;
import com.cts.searchprofile.service.ProfileService;

@RestController
public class ProfileController {
	
	@Autowired
	ProfileService profileService;
	
	@PostMapping("/skill-tracker/api/v1/engineer/add-profile")
	public ResponseEntity<?> addProfile(@RequestBody Profile profile) {
		Profile profileNew  = profileService.addProfile(profile);
		return new ResponseEntity<Profile>(profileNew, HttpStatus.OK);
	}

	@PutMapping("/skill-tracker/api/v1/engineer/update-profile/{userId}")
	public ResponseEntity<?> updateProfile(@PathVariable("userId") String userId, 
			 @RequestBody Profile profile) throws Exception {
		Profile profileNew  = profileService.updateProfile(profile, userId);
		return new ResponseEntity<Profile>(profileNew, HttpStatus.OK);
	}

	@GetMapping("/skill-tracker/api/v1/admin/{criteria}/{criteriaValue}")
	public ResponseEntity<?> searchProfiles(@PathVariable("criteria") String criteria, 
			@PathVariable("criteriaValue") String criteriaValue, @RequestParam int pageNo, @RequestParam int pageSize) {
		
		Map<Integer, List<Object>> paginatedMap = profileService.searchProfile(criteria, criteriaValue, pageNo, pageSize);
		
		return new ResponseEntity<Map<Integer, List<Object>>>(paginatedMap, HttpStatus.OK);
	}

}
