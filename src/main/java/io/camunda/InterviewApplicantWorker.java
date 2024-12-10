package io.camunda;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class InterviewApplicantWorker {

    private final static Logger LOG = LoggerFactory.getLogger(InterviewApplicantWorker.class);

    @JobWorker(type = "interview-the-applicant-technically")
    public Map<String, Object> interviewApplicant(@Variable(name = "questionScores") List<Integer> questionScores) {
        LOG.info("Interviewing applicant. Scores: {}", questionScores);
        int totalScore = questionScores.stream().mapToInt(Integer::intValue).sum();
        boolean interviewResult = totalScore >= 5;
        LOG.info("Interview result: {}", interviewResult ? "Passed" : "Failed");
        return Map.of("interviewResult", interviewResult);
    }
}
