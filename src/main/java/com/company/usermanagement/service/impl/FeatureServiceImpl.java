package com.company.usermanagement.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.usermanagement.entity.Feature;
import com.company.usermanagement.repository.FeatureRepository;
import com.company.usermanagement.service.FeatureService;

@Service
public class FeatureServiceImpl implements FeatureService {

	@Autowired
	private FeatureRepository featureRepository;

	public boolean getFeatureByEmail(String featureName, String email) {
		Optional<Feature> feature = featureRepository.findFeatureByEmailAndFeatureName(email, featureName);
		return feature.isPresent() ? feature.get().isEnable() : false;
	}

	public boolean createFeature(String featureName, String email, boolean enable) {

		try {
			Feature feature = new Feature();
			feature.setFeatureName(featureName);
			feature.setEmail(email);
			feature.setEnable(enable);
			Feature saved = featureRepository.save(feature);

			if (saved != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateFeature(String featureName, String email, boolean enable) {
		return featureRepository.findFeatureByEmailAndFeatureName(email, featureName).map(feature -> {

			try {
				feature.setEnable(enable);
				featureRepository.save(feature);
			} catch (Exception e) {
				return false;
			}
			return true;
		}).orElse(false);
	}
}
