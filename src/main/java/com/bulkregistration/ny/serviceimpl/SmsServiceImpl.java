package com.bulkregistration.ny.serviceimpl;

import com.bulkregistration.ny.entity.MemberEntity;
import com.bulkregistration.ny.repository.MemberRepository;
import com.bulkregistration.ny.service.SmsService;
import com.bulkregistration.ny.type.GeneralEnumerationDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsServiceImpl implements SmsService {
  private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class.getName());
  @Autowired
  MemberRepository memberRepository;

  @Override
  public String sendSmsForNews(String msisdn, String gender, String name) {
    if (gender.equals(GeneralEnumerationDefinition.GENDER_TYPES.M.getGenderTypeCode())) {
      System.out.println("Welcome to Our Service Mr. " + name);
      LOGGER.error("Welcome to Our Service Mr. " + name);
    } else {
      System.out.println("Welcome to Our Service Miss. " + name);
      LOGGER.error("Welcome to Our Service Miss. " + name);
    }
    return "Message Send Succes";

  }

  @Override
  public String sendSmsForAllUsers(String message) {
    List<MemberEntity> memberEntityList = memberRepository.findAll();
    memberEntityList.forEach(e -> System.out.println(e.getName() + " " + message));
    return "Success";
  }
}
