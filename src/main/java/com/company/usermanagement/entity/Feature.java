package com.company.usermanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Feature {

	@Id
	@Column(name = "feature_id")
	private int featureId;

	@Column(name = "feature_name")
	@NotBlank(message = "FeatureName is mandatory")
	private String featureName;

	@Column(name = "email")
	@NotBlank(message = "Email is mandatory")
	private String email;

	@Column(name = "enable")
	@NotNull
	private Boolean enable;

	public int getFeatureId() {
		return featureId;
	}

	public void setFeatureId(int featureId) {
		this.featureId = featureId;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean isEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}
