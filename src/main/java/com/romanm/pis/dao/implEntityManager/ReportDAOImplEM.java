package com.romanm.pis.dao.implEntityManager;

import com.romanm.pis.domain.Report;
import com.romanm.pis.dao.ReportDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ReportDAOImplEM implements ReportDAO {

    private static final Logger logger = LogManager.getLogger(ReportDAOImplEM.class);

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager entityManager;

    @Override
    public Report save(Report report) {
        entityManager.persist(report);

        return report;
    }

    @Override
    public Optional<Report> findById(Long id) {
        Report report = entityManager.find(Report.class, id);

        return Optional.ofNullable(report);
    }

    @Override
    public List<Report> findAll() {
        List<Report> reports = entityManager.createQuery("Select r From Report r", Report.class).getResultList();
        logger.debug("Retrieved list of reports: {}", Arrays.toString(reports.toArray()));

        return reports;
    }

    @Override
    public List<Report> findAllByEventId(Long eventId) {
        List<Report> reports = entityManager.createQuery("Select r From Report r where r.event.id=" + eventId, Report.class).getResultList();
        logger.debug("Retrieved list of reports by eventId={}: {}", eventId, Arrays.toString(reports.toArray()));
        return reports;
    }
}
