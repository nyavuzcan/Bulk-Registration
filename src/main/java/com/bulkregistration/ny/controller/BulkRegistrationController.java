package com.bulkregistration.ny.controller;

import com.bulkregistration.ny.service.BulkRegisterService;
import com.bulkregistration.ny.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bulk")
public class BulkRegistrationController {

  final private BulkRegisterService bulkRegisterService;
  final private SmsService smsService;

  @Autowired
  public BulkRegistrationController(BulkRegisterService bulkRegisterService, SmsService smsService) {
    this.bulkRegisterService = bulkRegisterService;
    this.smsService = smsService;
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity readCsvAndRegister() {
    bulkRegisterService.readCsvRegister();
    return new ResponseEntity("Processed All Data", HttpStatus.OK);
  }

  @RequestMapping(value = "/sms", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity readCsvAndRegister(@RequestBody String message) {
   smsService.sendSmsForAllUsers(message);
    return new ResponseEntity("Succes", HttpStatus.OK);
  }
}
