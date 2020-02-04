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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enable == null) ? 0 : enable.hashCode());
		result = prime * result + featureId;
		result = prime * result + ((featureName == null) ? 0 : featureName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feature other = (Feature) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enable == null) {
			if (other.enable != null)
				return false;
		} else if (!enable.equals(other.enable))
			return false;
		if (featureId != other.featureId)
			return false;
		if (featureName == null) {
			if (other.featureName != null)
				return false;
		} else if (!featureName.equals(other.featureName))
			return false;
		return true;
	}

}
