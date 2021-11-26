package com.cts.searchprofile.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.cts.searchprofile.data.Profile;

@Repository
public interface ProfileMatchingRepository extends PagingAndSortingRepository<Profile, String>,
QueryByExampleExecutor<Profile>{

}
