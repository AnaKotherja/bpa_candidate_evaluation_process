# Candidate Evaluation Process

## Overview
This project implements an application for evaluating job candidates. It uses the Camunda Zeebe Workflow Engine to find tasks such as background checks, applicant screening, and technical interviews.
The workflow is improved with custom task workers using the @JobWorker annotation, which helps handle specific tasks.

## Technologies Used

- **Camunda Zeebe Workflow Engine**: For managing the process workflow.
- **Spring Boot**: Provides the framework for building the application.
- **Java**: The programming language used for development.
- **Maven**: For project build and dependency management.
- **SLF4J**: Used for logging application activities.

## Components

1. **BackgroundCheckWorker**:
    - Validates an applicant's background based on their ID.
    - Sends a rejection or passes the background check result.

2. **ScreenApplicantWorker**:
    - Screens applicants based on their years of experience.

3. **InterviewApplicantWorker**:
    - Conducts technical interviews by scoring applicants.
    - Determines pass/fail status based on total scores.

4. **SendRejectionMessageWorker**:
    - Sends rejection messages to disqualified applicants.

## Main Application

**The CandidateEvaluationProcessApplication** initializes the application and starts a process instance. It also configures Zeebe to connect to the self-managed Camunda engine.

## Configuration
- **ZeebeConfiguration**  Contains health indicator properties for monitoring.
-  application.yaml define the connection details for Zeebe.

## Process Model

In the BPMN file Process-Automation-Exercise.bpmn is the proces workflow:
1. Start Event
2. Background Check Task 
3. Screen Applicant Task
4. Technical Interview Task
5. Decision Gateways
6. Rejection or Acceptance Notifications
7. End Event

## Running the application
### Prerequisites

- Java 17+
- Maven 3.6+

Camunda Zeebe engine running locally (ensure the gRPC server is active at http://127.0.0.1:26500).

### Steps to run
1. Clone the repository.
``` git clone https://github.com/AnaKotherja/bpa_candidate_evaluation_process.git ```
2. Navigate to the project directory. ``` cd bpa_candidate_evaluation_process2 ```
3. Build the project. ``` mvn clean install ```
4. Start the application. ``` mvn spring-boot:run ```
5. Once the application is running, got to http://localhost:8080/operate/login and log in with the username *demo* and password *demo*.
6. You can now see and interact with the running process. 

## Deploying the BPMN Model

The BPMN file is automatically deployed during application startup. Ensure that the Zeebe engine is running.

## Starting a Process Instance

A process instance can be started programmatically using the CandidateEvaluationProcessApplication. The initial variables include:
- applicantId: Randomly generated string.
- applicantExperience: Number of years of experience.
- applicantName

## Contact
For any queries or support, contact the project maintainers.
