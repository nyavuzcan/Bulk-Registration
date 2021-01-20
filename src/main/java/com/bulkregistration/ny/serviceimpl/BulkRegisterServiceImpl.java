package com.bulkregistration.ny.serviceimpl;

import com.bulkregistration.ny.entity.MemberEntity;
import com.bulkregistration.ny.model.MemberDto;
import com.bulkregistration.ny.repository.MemberRepository;
import com.bulkregistration.ny.service.BulkRegisterService;
import com.bulkregistration.ny.service.SmsService;
import com.bulkregistration.ny.util.RegistrationValidationUtils;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.bulkregistration.ny.util.FileUtils.readCsvFile;
import static com.bulkregistration.ny.util.FileUtils.writeMsisdnTXT;


@Service
public class BulkRegisterServiceImpl implements BulkRegisterService {

  @Autowired
  MemberRepository memberRepository;
  @Autowired
  RegistrationValidationUtils registrationValidationUtils;
  @Autowired
  SmsService smsService;
  @Autowired
  Mapper mapper;

  @Override
  public ResponseEntity readCsvRegister() {
    try {
      List<MemberDto> members = readCsvFile();

      for (MemberDto memberDto : members) {
        if (registrationValidationUtils.validateMemberFields(memberDto)) {

          MemberEntity memberEntity = mapper.map(memberDto, MemberEntity.class);
          memberRepository.save(memberEntity);

          writeMsisdnTXT(memberDto.toString(), memberDto.getMsisdn());

          System.out.println(memberDto.getMsisdn() + ".txt and to DB saved");

          smsService.sendSmsForNews(memberDto.getMsisdn(), memberDto.getGender(), memberDto.getName());

        } else continue;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}
