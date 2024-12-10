package io.camunda;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SendRejectionMessageWorker {

    private final static Logger LOG = LoggerFactory.getLogger(SendRejectionMessageWorker.class);

    @JobWorker(type = "send-rejection-message")
    public Map<String, Object> sendRejectionMessage(@Variable(name = "applicantId") String applicantId) {
        LOG.info("Sending rejection message for applicant: {}", applicantId);
        if (applicantId == null || applicantId.isEmpty()) {
            throw new IllegalArgumentException("Applicant ID cannot be null or empty");
        }
        String message = "Dear " + applicantId + ", we regret to inform you that we are unable to consider your application in the application process.";
        return Map.of("rejectionMessage", message);
    }
}
