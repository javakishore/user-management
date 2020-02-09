package com.company.usermanagement.service;

import com.company.usermanagement.model.Feature;

public interface FeatureService {
    public boolean getFeatureByEmail(Feature request) throws Exception;

    public boolean createFeature(Feature request) throws Exception;

    public boolean updateFeature(Feature request) throws Exception;
}
