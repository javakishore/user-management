package com.company.usermanagement.service;

import com.company.usermanagement.model.Feature;
import com.company.usermanagement.repository.FeatureRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeatureServiceTest {

    @Autowired
    public FeatureService featureService;

    @MockBean
    FeatureRepository featureRepository;

    Feature request = null;
    Feature response = null;

    @Before
    public void setup() {
        request = new Feature();
        request.setFeatureName("feature123");
        request.setEmail("email1@email.com");
        request.setEnable(true);

        response = new Feature();
        response.setFeatureName("feature123");
        response.setEmail("email1@email.com");
        response.setEnable(true);
    }

    @Test
    public void getFeatureByEmailTest() throws Exception {
        Mockito.when(featureRepository.findFeatureByEmailAndFeatureName(request.getEmail(),
                request.getFeatureName())).thenReturn(Optional.of(response));
        boolean flag = featureService.getFeatureByEmail(request);

        assertEquals(true, flag);
    }

    @Test
    public void createFeatureTest() throws Exception {
        Mockito.when(featureRepository.save(request)).thenReturn(response);
        boolean flag = featureService.createFeature(request);
        assertEquals(true, flag);
    }

    @Test
    public void updateFeature() throws Exception {
        Mockito.when(featureRepository.findById(request.getFeatureId())).thenReturn(Optional.of(response));
        Mockito.when(featureRepository.save(request)).thenReturn(response);
        boolean flag = featureService.updateFeature(request);
        assertEquals(true, flag);
    }
}