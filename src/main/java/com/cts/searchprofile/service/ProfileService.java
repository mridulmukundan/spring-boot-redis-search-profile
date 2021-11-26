package com.cts.searchprofile.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.searchprofile.data.Profile;
import com.cts.searchprofile.repository.ProfileRepository;

@Service
public class ProfileService {

	@Autowired
	ProfileRepository profileRepo;

	public Profile addProfile(Profile profile) {
		profileRepo.addProfile(profile);
		return profile;
	}

	public Profile updateProfile(Profile profile, String userId) {
		profileRepo.updateProfile(profile);
		return profile;

	}

	public Map<Integer, List<Object>> searchProfile(String criteria, String criteriaValue, int pageNo, int pageSize) {

		switch (criteria) {
		case "NAME": {
			return profileRepo.searchProfileByName(criteria, criteriaValue, pageNo, pageSize);
		}
		case "ASSOCIATEID":
			return profileRepo.searchProfileByAssociateId(criteria, criteriaValue, pageNo, pageSize);
		case "SKILLS":
			return profileRepo.searchProfileBySkills(criteria, criteriaValue, pageNo, pageSize);
		default:
		}
		return null;
	}

}
