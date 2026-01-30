RevHire – Console Based Job Portal Application

Project Summary
RevHire is a console-based Job Portal application developed using Java and Oracle Database. The application is designed to represent a real-world hiring platform where job seekers and employers interact through job postings and applications.
Job seekers can register, build their resumes, search for available jobs, apply for positions, and track application statuses. Employers can register company accounts, post job openings, view applicants, and review applications. A notification system keeps both users informed about important events such as job applications, withdrawals, and application status updates.
The project follows a layered architecture with clear separation between service logic and database access. JDBC is used for database interaction, and proper validation, logging, and security mechanisms are implemented to ensure data integrity and reliability.

ER Diagram 

Below is the Entity Relationship Diagram representing the database design of the RevHire application.
This diagram focuses on entities, attributes, and relationships, not control flow.

+-------------------+            +-------------------+
|   JOB_SEEKERS     |            |    EMPLOYERS      |
+-------------------+            +-------------------+
| PK job_seeker_id  |            | PK employer_id    |
| full_name         |            | company_name      |
| email (unique)    |            | email (unique)    |
| password          |            | password          |
| phone             |            | industry          |
| location          |            | company_size      |
| experience_years  |            | website           |
| security_question |            | location          |
| security_answer   |            | description       |
+-------------------+            | security_question |
        |                        | security_answer   |
        |                        +-------------------+
        |                                   |
        |                                   |
        |                                   |
        |                +------------------v------------------+
        |                |               JOBS                  |
        |                +-------------------------------------+
        +--------------< | PK job_id                            |
                         | FK employer_id                       |
                         | title                                |
                         | description                          |
                         | location                             |
                         | salary                               |
                         | job_type                             |
                         | status                               |
                         | created_date                         |
                         +------------------+------------------+
                                            |
                                            |
                                            |
                         +------------------v------------------+
                         |            APPLICATIONS              |
                         +-------------------------------------+
                         | PK application_id                    |
                         | FK job_id                            |
                         | FK job_seeker_id                     |
                         | status                               |
                         | applied_date                         |
                         +------------------+------------------+
                                            |
                                            |
                                            |
                         +------------------v------------------+
                         |              RESUMES                 |
                         +-------------------------------------+
                         | PK resume_id                         |
                         | FK job_seeker_id                     |
                         | objective                            |
                         | education                            |
                         | experience                           |
                         | skills                               |
                         | projects                             |
                         | updated_date                         |
                         +-------------------------------------+

+---------------------------------------------------------------+
|                        NOTIFICATIONS                           |
+---------------------------------------------------------------+
| PK notification_id                                             |
| user_type  (JOBSEEKER / EMPLOYER)                              |
| user_id   (job_seeker_id or employer_id)                       |
| message                                                        |
| is_read                                                        |
| created_date                                                   |
+---------------------------------------------------------------+

Relationship Explanation
One employer can post multiple jobs
One job seeker can apply to multiple jobs
Each job belongs to a single employer
Each application links a job seeker to a job
Each job seeker can maintain one resume
Notifications are generated for both job seekers and employers based on system events

Technologies Used
Java
Oracle Database (JDBC)
Log4j for logging
JUnit and Mockito for unit testing
Eclipse IDE

Author
Regula Venkata Dharani Kumar
