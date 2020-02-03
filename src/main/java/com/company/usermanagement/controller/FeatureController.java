package com.company.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.usermanagement.request.FeatureRequest;
import com.company.usermanagement.response.FeatureResponse;
import com.company.usermanagement.service.FeatureService;

@RestController
public class FeatureController {

	@Autowired
	private FeatureService featureService;

	@GetMapping("/feature")
	public ResponseEntity<FeatureResponse> getFeatureByEmail(FeatureRequest request) {
		boolean access = featureService.getFeatureByEmail(request.getFeatureName(), request.getEmail());
		FeatureResponse response = new FeatureResponse();
		response.setCanAcess(access);
		return ResponseEntity.ok(response);

	}

	@PostMapping("/feature")
	public ResponseEntity<?> createtFeature(@RequestBody FeatureRequest request) {
		boolean updated = featureService.createFeature(request.getFeatureName(), request.getEmail(),
				request.isEnable());

		return updated == true ? new ResponseEntity<Object>(HttpStatus.OK)
				: new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

	}

	@PutMapping("/feature")
	public ResponseEntity<?> updateFeature(@RequestBody FeatureRequest request) {
		boolean updated = featureService.updateFeature(request.getFeatureName(), request.getEmail(),
				request.isEnable());

		return updated == true ? new ResponseEntity<Object>(HttpStatus.OK)
				: new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);

	}

}
