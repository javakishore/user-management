package com.company.usermanagement.service.impl;

import java.util.Optional;

import com.company.usermanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.usermanagement.model.Feature;
import com.company.usermanagement.repository.FeatureRepository;
import com.company.usermanagement.model.Feature;
import com.company.usermanagement.service.FeatureService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Transactional
    public boolean getFeatureByEmail(Feature req) throws Exception {
        Optional<Feature> feature = featureRepository.findFeatureByEmailAndFeatureName(req.getEmail(), req.getFeatureName());
        return feature.isPresent() ? feature.get().isEnable() : false;
    }

    @Transactional
    public boolean createFeature(Feature req) throws Exception {

        try {
            Feature saved = featureRepository.save(req);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occurred while saving the feature");
        }
    }

    @Transactional
    public boolean updateFeature(Feature req) throws Exception {
        Optional<Feature> feature = featureRepository.findById(req.getFeatureId());
        if (feature.isPresent()) {
            try {
                Feature entity = feature.get();
                entity.setEnable(req.isEnable());
                featureRepository.save(entity);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Error occurred while updating the Feature");
            }
            return true;
        } else
            throw new ResourceNotFoundException("Feature does not exist for the id = " + req.getFeatureId());
    }
}
