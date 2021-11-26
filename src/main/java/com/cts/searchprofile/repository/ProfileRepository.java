package com.cts.searchprofile.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;

import com.cts.searchprofile.data.Profile;
import com.cts.searchprofile.data.Skill;

@Repository
public class ProfileRepository {

	private static final String CACHE_KEY_SKILLS = "SKILLS";
	private static final String CACHE_KEY_NAME = "NAME";
	private static final String CACHE_KEY_ASSOCIATEID = "ASSOCIATEID";

	@Autowired
	RedisTemplate<String, Profile> redisTemplate;

	public Map<Integer, List<Object>> searchProfileByName(String criteria, String criteriaValue, int pageNo,
			int pageSize) {
		Map<Integer, List<Object>> paginatedMap = new HashMap<>();
		String patternString = "*:" + criteriaValue + "*";
		ScanOptions scanOptions = ScanOptions.scanOptions().match(patternString).count(100).build();
		try (Cursor<Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(CACHE_KEY_NAME, scanOptions);) {
			getPageData(cursor, paginatedMap, pageNo, pageSize);
		} catch (Exception ex) {

		}
		return paginatedMap;
	}

	public Map<Integer, List<Object>> searchProfileByAssociateId(String criteria, String criteriaValue, int pageNo,
			int pageSize) {
		Map<Integer, List<Object>> paginatedMap = new HashMap<>();
		String patternString = "*:" + criteriaValue;
		ScanOptions scanOptions = ScanOptions.scanOptions().match(patternString).count(100).build();
		try (Cursor<Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(CACHE_KEY_ASSOCIATEID,
				scanOptions);) {
			getPageData(cursor, paginatedMap, pageNo, pageSize);
		} catch (Exception ex) {

		}
		return paginatedMap;
	}

	public Map<Integer, List<Object>> searchProfileBySkills(String criteria, String criteriaValue, int pageNo,
			int pageSize) {
		Map<Integer, List<Object>> paginatedMap = new HashMap<>();
		String patternString = "*:" + criteriaValue + ":*";
		ScanOptions scanOptions = ScanOptions.scanOptions().match(patternString).count(100).build();
		try (Cursor<Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(CACHE_KEY_SKILLS, scanOptions);) {
			getPageData(cursor, paginatedMap, pageNo, pageSize);
		} catch (Exception ex) {

		}
		return paginatedMap;
	}

	public void addProfile(Profile profile) {

		redisTemplate.opsForHash().put(CACHE_KEY_NAME, profile.getUserId() + ":" + profile.getName(), profile);
		redisTemplate.opsForHash().put(CACHE_KEY_ASSOCIATEID, profile.getUserId() + ":" + profile.getAssociateId(),
				profile);
		String skills = null;
		for (Skill s : profile.getSkills()) {
			skills = (skills == null ? "" : skills + ":") + s.getName();
		}
		redisTemplate.opsForHash().put(CACHE_KEY_SKILLS, profile.getUserId() + ":" + skills, profile);
	}

	public void updateProfile(Profile profile) {
		List<Object> hashKeyList = new ArrayList<>();
		ScanOptions scanOptions = ScanOptions.scanOptions().match(profile.getUserId() + ":*").count(1000).build();
		try (Cursor<Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(CACHE_KEY_SKILLS, scanOptions);) {
			while (cursor.hasNext())
				hashKeyList.add(cursor.next().getKey());

		} catch (Exception ex) {
			return;
		}
		for (Object o : hashKeyList) {
			redisTemplate.opsForHash().delete(CACHE_KEY_SKILLS, o);
			String skills = null;
			for (Skill s : profile.getSkills()) {
				skills = (skills == null ? "" : skills + ":") + s.getName();
			}
			redisTemplate.opsForHash().put(CACHE_KEY_SKILLS, profile.getUserId() + ":" + skills, profile);
		}
		redisTemplate.opsForHash().put(CACHE_KEY_NAME, profile.getUserId() + ":" + profile.getName(), profile);
		redisTemplate.opsForHash().put(CACHE_KEY_ASSOCIATEID, profile.getUserId() + ":" + profile.getAssociateId(),
				profile);
	}

	private void getPageData(Cursor<Entry<Object, Object>> cursor, Map<Integer, List<Object>> paginatedMap, int pageNo,
			int pageSize) {

		int tmpIndex = 0;
		int tmpEndIndex = 0;
		while (cursor.hasNext()) {
			if (tmpIndex < pageNo && tmpEndIndex < pageSize) {
				if (tmpEndIndex == 0)
					paginatedMap.put(tmpIndex, new ArrayList<>());

				paginatedMap.get(tmpIndex).add(cursor.next().getValue());
				tmpEndIndex++;
				continue;
			}

			if (tmpIndex >= pageNo)
				break;
			if (tmpEndIndex >= pageSize) {
				tmpEndIndex = 0;
			}

			tmpIndex++;
		}
	}
}
