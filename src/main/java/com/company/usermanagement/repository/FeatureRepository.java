package com.company.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.usermanagement.entity.Feature;

@Repository
public interface FeatureRepository extends CrudRepository<Feature, Long> {
	public Optional<Feature> findFeatureByEmailAndFeatureName(String email,@Param("email") String featureName);
	
}
