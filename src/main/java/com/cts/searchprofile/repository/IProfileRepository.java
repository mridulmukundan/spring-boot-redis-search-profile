package com.cts.searchprofile.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cts.searchprofile.data.Profile;

@Repository
public interface IProfileRepository extends PagingAndSortingRepository<Profile, String>{
	
	Page<Profile> findAll(Pageable pageable);
	//Page<Profile> findByNameContaining(Pageable pageable);
	//Page<Profile> findByAssociateIdContaining(Pageable pageable);
}
