package com.romanm.pis.dto;

public class ReportDto {
    Long reportId;
    String reportTopic;
    String reportText;

    public ReportDto() {
    }

    public ReportDto(Long reportId, String reportTopic, String reportText) {
        this.reportId = reportId;
        this.reportTopic = reportTopic;
        this.reportText = reportText;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getReportTopic() {
        return reportTopic;
    }

    public void setReportTopic(String reportTopic) {
        this.reportTopic = reportTopic;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }
}
