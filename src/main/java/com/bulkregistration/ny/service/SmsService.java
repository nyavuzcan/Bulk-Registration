package com.bulkregistration.ny.service;

public interface SmsService {
  String sendSmsForNews(String msisdn, String gender, String name);
  String sendSmsForAllUsers(String message);
}
