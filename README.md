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

## Running the Application

### Prerequisites

- **Java 17+** (or OpenJDK 21+)
- **Maven 3.6+**
- **Camunda Run 8 installed and running locally**:  
   Ensure that the **gRPC server** is active at [http://127.0.0.1:26500](http://127.0.0.1:26500) and the **REST API server** is running at [http://127.0.0.1:8080](http://127.0.0.1:8080).

   #### Steps to Install Camunda 8 Run:
   1. **Download Camunda 8 Run**  
      Visit the official Camunda 8 GitHub releases page:  
      [Camunda Run 8 Releases](https://github.com/camunda/camunda-run/releases)  
      Download the latest release of Camunda 8 Run for your operating system and architecture (e.g., `.tgz` for Linux/macOS or `.zip` for Windows).

   2. **Extract the Camunda 8 Run Files**  
      - On **Linux/macOS**, open the `.tgz` file to extract the Camunda 8 Run script into a new directory.  
      - On **Windows**, open the `.zip` file and extract it to your desired directory.

   3. **Navigate to the Camunda 8 Run Directory**  
      Open a terminal (or Command Prompt on Windows) and navigate to the directory where the Camunda 8 Run files were extracted.

   4. **Start Camunda 8 Run**  
      Run the following command to start Camunda 8 Run:

      - On **Linux/macOS**:

        ```bash
        ./start.sh
        ```

      - On **Windows**:

        ```bash
        .\c8run.exe start
        ```

   5. **Verify the Start Process**  
      After a few moments, the Camunda 8 Run engine will start, and a new **Operate** window should automatically open.  
      - **Operate** can be accessed at [http://localhost:8080/operate](http://localhost:8080/operate).
      - **Monitor** To monitor the tasks that are currently running navigate to [http://localhost:8080/tasklist](http://localhost:8080/tasklist) after starting the CandidateEvaluationProcessApplication.



### Steps to run
1. Clone the repository.
``` git clone https://github.com/AnaKotherja/bpa_candidate_evaluation_process.git ```
2. Navigate to the project directory. ``` cd bpa_candidate_evaluation_process ```
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
