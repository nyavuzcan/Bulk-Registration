package com.bulkregistration.ny.util;

import com.bulkregistration.ny.model.MemberDto;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.List;

public class FileUtils {

  private final static String FILE_NAME = "membersCsv.csv";


  public static List readCsvFile() throws IOException {

    Resource resource = new ClassPathResource(FILE_NAME);
    File file = resource.getFile();

    return new CsvToBeanBuilder(new FileReader(file))
        .withType(MemberDto.class)
        .withSkipLines(1)
        .build()
        .parse();

  }

  public static boolean writeMsisdnTXT(String memberDtoString, String msisdn){
    try {
      PrintWriter writer = new PrintWriter(msisdn+".txt", "UTF-8");
      writer.println(memberDtoString);
      writer.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    return true;

  }

}
