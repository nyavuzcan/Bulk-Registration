package com.bulkregistration.ny.repository;

import com.bulkregistration.ny.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
  MemberEntity findByMsisdn(String number);
}
