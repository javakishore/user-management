package com.company.usermanagement.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.company.usermanagement.model.Feature;
import com.company.usermanagement.service.FeatureService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FeatureControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FeatureService featureService;

	Feature feature = null;
	
	Feature getFeature = null;

	@Before
	public void setup() {
		feature = new Feature();
		feature.setFeatureName("feature123");
		feature.setEmail("email1@email.com");
		feature.setEnable(true);
		
		getFeature = new Feature();
		getFeature.setFeatureName("feature123");
		getFeature.setEmail("email1@email.com");
	}

	@Test
	public void createFeature() throws Exception {

		Mockito.when(featureService.createFeature(feature)).thenReturn(true);

		mockMvc.perform(post("/feature").contentType(MediaType.APPLICATION_JSON).content(toJson(feature))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void updateFeature() throws Exception {
		Mockito.when(featureService.updateFeature(feature)).thenReturn(true);

		mockMvc.perform(put("/feature").contentType(MediaType.APPLICATION_JSON).content(toJson(feature))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getFeature() throws Exception {
		Mockito.when(featureService.getFeatureByEmail(getFeature)).thenReturn(true);

		MvcResult mvcResult = mockMvc
				.perform(get("/feature?featureName=" + feature.getFeatureName() + "&email=" + feature.getEmail())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		String content = mvcResult.getResponse().getContentAsString();

		assertEquals(true, content.contains("true"));
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
}