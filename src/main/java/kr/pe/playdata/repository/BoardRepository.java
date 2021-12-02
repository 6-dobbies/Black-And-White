package kr.pe.playdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.playdata.model.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
}
