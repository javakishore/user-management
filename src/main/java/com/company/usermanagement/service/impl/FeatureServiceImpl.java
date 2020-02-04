package com.company.usermanagement.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.usermanagement.entity.Feature;
import com.company.usermanagement.repository.FeatureRepository;
import com.company.usermanagement.request.FeatureRequest;
import com.company.usermanagement.service.FeatureService;

@Service
public class FeatureServiceImpl implements FeatureService {

	@Autowired
	private FeatureRepository featureRepository;

	public boolean getFeatureByEmail(FeatureRequest req) {
		Optional<Feature> feature = featureRepository.findFeatureByEmailAndFeatureName(req.getEmail(), req.getFeatureName());
		return feature.isPresent() ? feature.get().isEnable() : false;
	}

	public boolean createFeature(FeatureRequest req) {

		try {
			Feature feature = new Feature();
			feature.setFeatureName(req.getFeatureName());
			feature.setEmail(req.getEmail());
			feature.setEnable(req.isEnable());
			Feature saved = featureRepository.save(feature);

			if (saved != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateFeature(FeatureRequest req) {
		return featureRepository.findFeatureByEmailAndFeatureName(req.getEmail(), req.getFeatureName()).map(feature -> {

			try {
				feature.setEnable(req.isEnable());
				featureRepository.save(feature);
			} catch (Exception e) {
				return false;
			}
			return true;
		}).orElse(false);
	}
}
