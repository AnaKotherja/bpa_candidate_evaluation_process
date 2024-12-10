package io.camunda;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ScreenApplicantWorker {

    private final static Logger LOG = LoggerFactory.getLogger(ScreenApplicantWorker.class);

    @JobWorker(type = "screen-the-applicant")
    public Map<String, Object> screenApplicant(@Variable(name = "applicantExperience") Double applicantExperience) {
        LOG.info("Screening applicant with experience: {} years", applicantExperience);
        boolean isQualified = applicantExperience != null && applicantExperience >= 1;
        LOG.info("Applicant qualification status: {}", isQualified);
        return Map.of("isQualified", isQualified);
    }
}
