package com.bulkregistration.ny.type;

public class GeneralEnumerationDefinition {
  public enum SIM_TYPES {
    PREPAID("PREPAID "),
    POSTPAID("POSTPAID");


    private final String simTypeCode;

    SIM_TYPES(String simTypeCode) {
      this.simTypeCode = simTypeCode;
    }

    public String getSimTypeCode() {
      return simTypeCode;
    }
  }

  public enum  GENDER_TYPES {
    F("F"),
    G("G");
    private final String genderTypeCode;

    GENDER_TYPES(String genderTypeCode) {
      this.genderTypeCode = genderTypeCode;
    }

    public String getGenderTypeCode() {
      return genderTypeCode;
    }
  }
}
