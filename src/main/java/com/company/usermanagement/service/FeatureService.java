package com.company.usermanagement.service;

import com.company.usermanagement.request.FeatureRequest;

public interface FeatureService {
	public boolean getFeatureByEmail(FeatureRequest request);
	public boolean createFeature(FeatureRequest request);
	public boolean updateFeature(FeatureRequest request);
}
