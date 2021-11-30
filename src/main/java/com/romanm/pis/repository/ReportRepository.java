package com.romanm.pis.repository;

import com.romanm.pis.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("Select r From Report r where r.event.id=:eventId")
    List<Report> findAllByQuery(@Param("eventId") Long eventId);
}
