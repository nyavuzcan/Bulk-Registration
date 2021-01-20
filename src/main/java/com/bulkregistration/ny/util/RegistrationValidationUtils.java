package com.bulkregistration.ny.util;

import com.bulkregistration.ny.entity.MemberEntity;
import com.bulkregistration.ny.model.MemberDto;
import com.bulkregistration.ny.repository.MemberRepository;
import com.bulkregistration.ny.type.GeneralEnumerationDefinition;
import org.jcp.xml.dsig.internal.dom.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegistrationValidationUtils {
  private final Logger LOGGER = LoggerFactory.getLogger(Utils.class.getName());

  @Autowired
   MemberRepository memberRepository;

  public boolean validateMemberFields(MemberDto memberDto) {
    if (checkFields(memberDto))
      if (checkName(memberDto.getName()))
        if (checkDateOfBirth(memberDto.getDateOfBirth()))
          if (checkGenderTypes(memberDto.getGender()))
            if (checkAddress(memberDto.getAddress()))
              if (checkSimType(memberDto.getSimType()))
                if (checkMsisdnNumberIsValid(memberDto.getMsisdn()))
                  return checkMsisdnNumberIsUniq(memberDto.getMsisdn());

    return false;
  }

  public  boolean checkFields(MemberDto memberDto) {
    boolean error = true;
    if (!(Objects.nonNull(memberDto.getMsisdn()) && memberDto.getMsisdn().length() > 0)) {
      LOGGER.error("MSISDN CANNOT BE NULL");
      System.out.println("MSISDN CANNOT BE NULL");
      error = false;
    }
    if (!(Objects.nonNull(memberDto.getSimType()) && memberDto.getSimType().length() > 0)) {
      LOGGER.error("SIM_TYPE CANNOT BE NULL");
      System.out.println("SIM_TYPE CANNOT BE NULL");
      error = false;
    }
    if (!(Objects.nonNull(memberDto.getName()) && memberDto.getName().length() > 0)) {
      LOGGER.error("NAME CANNOT BE NULL");
      System.out.println("NAME CANNOT BE NULL");
      error = false;
    }
    if (!(Objects.nonNull(memberDto.getDateOfBirth()) && memberDto.getDateOfBirth().length() > 0)) {
      LOGGER.error("DATE_OF_BIRTH CANNOT BE NULL");
      System.out.println("DATE_OF_BIRTH CANNOT BE NULL");
      error = false;
    }
    if (!(Objects.nonNull(memberDto.getGender()) && memberDto.getGender().length() > 0)) {
      LOGGER.error("GENDER CANNOT BE NULL");
      System.out.println("GENDER CANNOT BE NULL");
      error = false;
    }
    if (!(Objects.nonNull(memberDto.getAddress()) && memberDto.getAddress().length() > 0)) {
      LOGGER.error("ADDRESS CANNOT BE NULL");
      System.out.println("ADDRESS CANNOT BE NULL");
      error = false;
    }
    if (!(Objects.nonNull(memberDto.getIdNumber()) && memberDto.getIdNumber().length() > 0)) {
      LOGGER.error("ID_NUMBER CANNOT BE NULL");
      System.out.println("ID_NUMBER CANNOT BE NULL");
      error = false;
    }
    return error;
  }

  public  boolean checkName(String memberName) {
    Pattern patternName = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    Matcher nameMatcher = patternName.matcher(memberName);
    boolean isThereSpecialChar = nameMatcher.find();

    if (isThereSpecialChar) {
      LOGGER.error("NAME CAN NOT HAVE ANY SPECIAL CHARACTER");
      System.out.println("NAME CAN NOT HAVE ANY SPECIAL CHARACTER");
      return false;
    }
    return true;
  }

  public  boolean checkDateOfBirth(String memberDateOfBirth) {
    try {
      LocalDate localDateTime = LocalDate.now().minusDays(1);

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate localDate = LocalDate.parse(memberDateOfBirth, formatter);

      if (!localDate.isBefore(localDateTime)) {
        LOGGER.error("DATE_OF_BIRTH shouldn't be TODAY or FUTURE");
        System.out.println("DATE_OF_BIRTH shouldn't be TODAY or FUTURE");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }

  public  boolean checkGenderTypes(String memberGender) {
    if (!memberGender.equals(GeneralEnumerationDefinition.GENDER_TYPES.F.getGenderTypeCode())
       && !memberGender.equals(GeneralEnumerationDefinition.GENDER_TYPES.M.getGenderTypeCode())
    ) {
      LOGGER.error("Gender can only be F or M");
      System.out.println("Gender can only be F or M");
      return false;
    }
    return true;
  }

  public  boolean checkAddress(String memberAddress) {
    if (memberAddress.length() < 20) {
      LOGGER.error("Address must at least be 20 characters long");
      System.out.println("Address must at least be 20 characters long");
      return false;
    }
    return true;
  }

  public  boolean checkSimType(String simType) {
    if (!simType.equals(GeneralEnumerationDefinition.SIM_TYPES.POSTPAID.getSimTypeCode())
        && !simType.equals(GeneralEnumerationDefinition.SIM_TYPES.PREPAID.getSimTypeCode())
    ) {
      LOGGER.error("SIM_TYPE can only be PREPAID or POSTPAID");
      System.out.println("SIM_TYPE can only be PREPAID or POSTPAID");
      return false;
    }
    return true;
  }

  public  boolean checkMsisdnNumberIsValid(String memberMsisdn) {
    String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
    Pattern patternName = Pattern.compile(regex);

    Matcher msisdnMatcher = patternName.matcher(memberMsisdn);
    boolean isItFalse = msisdnMatcher.find();
    if (!isItFalse) {
      LOGGER.error("MSISDN should comply to country's standard (e.g. +66)");
      System.out.println("MSISDN should comply to country's standard (e.g. +66)");
      return false;
    }
    return true;
  }

  public boolean checkMsisdnNumberIsUniq(String memberMsisdn) {
    MemberEntity memberEntity = memberRepository.findByMsisdn(memberMsisdn);
    if (Objects.nonNull(memberEntity)) {
      LOGGER.error("MSISDN can't be duplicated");
      System.out.println("MSISDN can't be duplicated");
      return false;
    }
    return true;
  }

}
