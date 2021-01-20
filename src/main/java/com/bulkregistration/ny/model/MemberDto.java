package com.bulkregistration.ny.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByPosition;

import java.util.Date;

public class MemberDto {

  @CsvBindByPosition(position = 0)
  private String msisdn;
  @CsvBindByPosition(position = 1)
  private String simType;
  @CsvBindByPosition(position = 2)
  private String name;
  @CsvBindByPosition(position = 3)
  private String dateOfBirth;
  @CsvBindByPosition(position = 4)
  private String gender;
  @CsvBindByPosition(position = 5)
  private String address;
  @CsvBindByPosition(position = 6)
  private String idNumber;

  public MemberDto(String msisdn, String simType, String name, String dateOfBirth, String gender, String address, String idNumber) {
    this.msisdn = msisdn;
    this.simType = simType;
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.address = address;
    this.idNumber = idNumber;
  }

  public MemberDto() {
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getSimType() {
    return simType;
  }

  public void setSimType(String simType) {
    this.simType = simType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(String idNumber) {
    this.idNumber = idNumber;
  }

  @Override
  public String toString() {
    return "MemberDto{" +
        "msisdn='" + msisdn + '\'' +
        ", simType='" + simType + '\'' +
        ", name='" + name + '\'' +
        ", dateOfBirth='" + dateOfBirth + '\'' +
        ", gender='" + gender + '\'' +
        ", address='" + address + '\'' +
        ", idNumber='" + idNumber + '\'' +
        '}';
  }
}
