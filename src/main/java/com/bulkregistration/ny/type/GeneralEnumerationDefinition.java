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
}
