package com.company.usermanagement.service;

public interface FeatureService {
	public boolean getFeatureByEmail(String featureName,String email);
	public boolean createFeature(String featureName,String email,boolean enable);
	public boolean updateFeature(String featureName,String email,boolean enable);
}
