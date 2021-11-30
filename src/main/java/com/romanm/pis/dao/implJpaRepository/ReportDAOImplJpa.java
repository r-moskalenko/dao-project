package com.romanm.pis.dao.implJpaRepository;

import com.romanm.pis.dao.ReportDAO;
import com.romanm.pis.domain.Report;
import com.romanm.pis.repository.ReportRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ReportDAOImplJpa implements ReportDAO {

    private static final Logger logger = LogManager.getLogger(ReportDAOImplJpa.class);

    private final ReportRepository reportRepository;

    public ReportDAOImplJpa(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report save(Report report) {
        reportRepository.save(report);

        return report;
    }

    @Override
    public Optional<Report> findById(Long id) {
        Optional<Report> report = reportRepository.findById(id);
        logger.debug(report);
        return report;
    }

    @Override
    public List<Report> findAll() {
        List<Report> reports = reportRepository.findAll();
        return reports;
    }

    @Override
    public List<Report> findAllByEventId(Long eventId) {
        List<Report> reports = reportRepository.findAllByQuery(eventId);
        return reports;
    }
}
