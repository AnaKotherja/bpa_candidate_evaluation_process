package io.camunda;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
@Deployment(resources = "Process-Automation-Exercise.bpmn")
public class CandidateEvaluationProcessApplication implements CommandLineRunner {

    private final ZeebeClient zeebeClient;
    private final static Logger LOG = LoggerFactory.getLogger(CandidateEvaluationProcessApplication.class);

    @Autowired
    public CandidateEvaluationProcessApplication(ZeebeClient zeebeClient) {
        this.zeebeClient = zeebeClient;
    }
    public static void main(String[] args) {
        SpringApplication.run(CandidateEvaluationProcessApplication.class, args);
    }

    @Override
    public void run(final String... args) {
        try {
            var bpmnProcessId = "candidate_evaluation_process";
            double yearsExperience = 3.0;
            String applicantId = RandomStringUtils.randomAlphabetic(20);
            var event = zeebeClient.newCreateInstanceCommand()
                    .bpmnProcessId(bpmnProcessId)
                    .latestVersion()
                    .variables(Map.of(
                            "applicantId", applicantId,
                            "applicantExperience", yearsExperience,
                            "applicantName", "Ana Kotherja"
                    ))
                    .send()
                    .join();
            LOG.info("Started a process instance: {}", event.getProcessInstanceKey());
        } catch (Exception exception) {
            LOG.error("Error occurred when starting process instance", exception);
        }
    }

}
