package com.romanm.pis.repository;

import com.romanm.pis.domain.Report;

import java.util.List;
import java.util.Optional;

public interface ReportRepository {
    Report save(Report report);

    Optional<Report> findById(Long id);

    List<Report> findAll();
}
