package com.company.usermanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aspectj.lang.annotation.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.company.usermanagement.UserManagementApplication;
import com.company.usermanagement.request.FeatureRequest;
import com.company.usermanagement.response.FeatureResponse;
import com.company.usermanagement.service.FeatureService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = UserManagementApplication.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeatureControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private FeatureService featureService;

	@InjectMocks
	private FeatureController featureController;

	@org.junit.Before
	public void setup() {

		// this must be called for the @Mock annotations above to be processed
		// and for the mock service to be injected into the controller under
		// test.
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.standaloneSetup(featureController).build();

	}

	public static String toJson(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T fromJson(final String json, Class<T> clazz) {
		try {
			return new ObjectMapper().readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void createFeature() throws Exception {

		when(featureService.createFeature("feature123", "email1@email.com", true)).thenReturn(true);

		FeatureRequest feature = new FeatureRequest();
		feature.setFeatureName("feature123");
		feature.setEmail("email1@email.com");
		feature.setEnable(true);

		mockMvc.perform(post("/feature").contentType(MediaType.APPLICATION_JSON).content(toJson(feature))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void updateFeature() throws Exception {
		when(featureService.updateFeature("feature123", "email1@email.com", false)).thenReturn(true);

		FeatureRequest feature = new FeatureRequest();
		feature.setFeatureName("feature123");
		feature.setEmail("email1@email.com");
		feature.setEnable(false);

		mockMvc.perform(put("/feature").contentType(MediaType.APPLICATION_JSON).content(toJson(feature))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getFeature() throws Exception {
		when(featureService.getFeatureByEmail("feature1", "email@email.com")).thenReturn(true);

		MvcResult mvcResult = mockMvc.perform(get("/feature?featureName=feature1&email=email@email.com")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		ObjectMapper objectWrapper = new ObjectMapper();
		FeatureResponse reseponse = objectWrapper.readValue(content, FeatureResponse.class);
		assertEquals(true, reseponse.isCanAcess());
	}

}
