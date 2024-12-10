package io.camunda;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BackgroundCheckWorker {

    private final static Logger LOG = LoggerFactory.getLogger(BackgroundCheckWorker.class);

    @JobWorker(type = "background-check-service")
    public Map<String, Object> performBackgroundCheck(@Variable(name = "applicantId") String applicantId, JobClient client, ActivatedJob job) {
        LOG.info("Performing background check for applicant: {}", applicantId);
        if (applicantId != null) {
            boolean backgroundCheckPassed = !applicantId.isEmpty();
            LOG.info("Background check result for {}: {}", applicantId, backgroundCheckPassed ? "Cleared" : "Not Cleared");
            if (!backgroundCheckPassed) {
                LOG.error("Background check failed for applicant {}", applicantId);
                client.newFailCommand(job.getKey())
                        .retries(3)
                        .send()
                        .join();
                throw new RuntimeException("Background check failed for applicant " + applicantId);
            }
            return Map.of("backgroundCheckPassed", true);
        } else {
            LOG.warn("ApplicantId is null. No background check performed.");
            return Map.of("backgroundCheckPassed", false);
        }
    }

}
