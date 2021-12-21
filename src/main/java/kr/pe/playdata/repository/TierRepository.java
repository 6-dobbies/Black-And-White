package kr.pe.playdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.domain.Tier;

public interface TierRepository extends JpaRepository<Tier, Long> {
	
	Tier findByMember(Member member);

}
