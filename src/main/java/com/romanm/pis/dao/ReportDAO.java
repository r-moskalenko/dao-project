package com.romanm.pis.dao;

import com.romanm.pis.domain.Report;

import java.util.List;
import java.util.Optional;

public interface ReportDAO {
    Report save(Report report);

    Optional<Report> findById(Long id);

    List<Report> findAllByEventId(Long eventId);

    List<Report> findAll();
}
