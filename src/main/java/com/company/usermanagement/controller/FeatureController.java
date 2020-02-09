package com.company.usermanagement.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.usermanagement.model.Feature;

import com.company.usermanagement.service.FeatureService;

import java.text.MessageFormat;
import java.util.Collections;

@RestController
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @GetMapping(value = "/feature", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFeatureByEmail(Feature request) throws Exception {
        boolean access = featureService.getFeatureByEmail(request);
        return ResponseEntity.ok(Collections.singletonMap("canAccess", access));

    }

    @PostMapping("/feature")
    public ResponseEntity<?> createtFeature(@RequestBody Feature request) throws Exception {
        return featureService.createFeature(request) ? new ResponseEntity<Object>(HttpStatus.OK)
                : new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/feature")
    public ResponseEntity<?> updateFeature(@RequestBody Feature request) throws Exception {
        return featureService.updateFeature(request) ? new ResponseEntity<Object>(HttpStatus.OK)
                : new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);


    }

}
