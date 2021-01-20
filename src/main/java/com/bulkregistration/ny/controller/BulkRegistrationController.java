package com.bulkregistration.ny.controller;

import com.bulkregistration.ny.service.BulkRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bulk")
public class BulkRegistrationController {

  final private BulkRegisterService bulkRegisterService;

  @Autowired
  public BulkRegistrationController(BulkRegisterService bulkRegisterService) {
    this.bulkRegisterService = bulkRegisterService;
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity readCsvAndRegister() {
    bulkRegisterService.readCsvRegister();
    return null;
  }
}
