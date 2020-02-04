package com.company.usermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.usermanagement.entity.Feature;
import com.company.usermanagement.repository.FeatureRepository;
import com.company.usermanagement.request.FeatureRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeatureServiceTest {

	@Autowired
	public FeatureService featureService;

	@MockBean
	FeatureRepository featureRepository;

	FeatureRequest featureRequest = null;
	Feature feature = null;

	@Before
	public void setup() {
		featureRequest = new FeatureRequest();
		featureRequest.setFeatureName("feature123");
		featureRequest.setEmail("email1@email.com");
		featureRequest.setEnable(true);

		feature = new Feature();
		feature.setFeatureName("feature123");
		feature.setEmail("email1@email.com");
		feature.setEnable(true);
	}

	@Test
	public void getFeatureByEmailTest() {
		Mockito.when(featureRepository.findFeatureByEmailAndFeatureName(featureRequest.getEmail(),
				featureRequest.getFeatureName())).thenReturn(Optional.of(feature));
		boolean flag = featureService.getFeatureByEmail(featureRequest);

		assertEquals(true, flag);
	}

	@Test
	public void createFeatureTest() {
		Mockito.when(featureRepository.save(feature)).thenReturn(feature);
		boolean flag = featureService.createFeature(featureRequest);
		assertEquals(true, flag);
	}

	@Test
	public void updateFeature() {
		Mockito.when(featureRepository.findFeatureByEmailAndFeatureName(featureRequest.getEmail(),
				featureRequest.getFeatureName())).thenReturn(Optional.of(feature));
		Mockito.when(featureRepository.save(feature)).thenReturn(feature);
		boolean flag = featureService.updateFeature(featureRequest);
		assertEquals(true, flag);
	}
}