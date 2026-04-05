Here is your **FINAL COMPLETE DEVOPS FLOW (with Git + all codes)**
Application-specific, clean, and ready to **copy in exam / README**

---

````markdown id="l4o2hv"
# DevOps Flow - Student Marks Service (Complete with Git)

This project implements:

- Total Marks
- Average Marks
- Pass/Fail Result

Full DevOps pipeline:

Local Code → Git → GitHub → Jenkins → Maven → Docker → Kubernetes

---

## Repository Structure

student-marks-service/
│
├── pom.xml
├── Dockerfile
├── Jenkinsfile
├── k8s/
│   └── deployment.yaml
│
└── src/
    ├── main/java/com/devops/StudentMarksProcessor.java
    └── test/java/com/devops/StudentMarksProcessorTest.java

---

## Phase 1: Clone Repository

```bash
git clone https://github.com/Tharun-coder-hash/maven_app.git
cd maven_app
code .
````

---

## Phase 2: Java Application

### StudentMarksProcessor.java

```java
package com.devops;

public class StudentMarksProcessor {

    public static int calculateTotal(int[] marks) {
        int total = 0;
        for (int m : marks) {
            total += m;
        }
        return total;
    }

    public static double calculateAverage(int[] marks) {
        if (marks.length == 0) return 0;
        return (double) calculateTotal(marks) / marks.length;
    }

    public static String checkResult(int[] marks) {
        double avg = calculateAverage(marks);
        return (avg >= 40) ? "Pass" : "Fail";
    }

    public static void main(String[] args) {
        System.out.println("Student Marks Service Running...");
    }
}
```

---

## Phase 3: JUnit Test

### StudentMarksProcessorTest.java

```java
package com.devops;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StudentMarksProcessorTest {

    @Test
    void testTotal() {
        int[] marks = {50, 60, 70};
        assertEquals(180, StudentMarksProcessor.calculateTotal(marks));
    }

    @Test
    void testAverage() {
        int[] marks = {40, 40, 40};
        assertEquals(40.0, StudentMarksProcessor.calculateAverage(marks));
    }

    @Test
    void testResult() {
        int[] passMarks = {50, 60, 70};
        int[] failMarks = {20, 30, 35};

        assertEquals("Pass", StudentMarksProcessor.checkResult(passMarks));
        assertEquals("Fail", StudentMarksProcessor.checkResult(failMarks));
    }
}
```

---

## Phase 4: Maven (pom.xml)

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.devops</groupId>
    <artifactId>student-marks-service</artifactId>
    <version>1.0</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.devops.StudentMarksProcessor</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## Phase 5: Dockerfile

```dockerfile
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/student-marks-service-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

## Phase 6: Jenkinsfile

```groovy
pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "student-marks:latest"
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/Tharun-coder-hash/maven_app.git'
            }
        }

        stage('Build & Test') {
            steps {
                dir('maven_app') {
                    bat 'mvn clean test package'
                }
            }
        }

        stage('Docker Build') {
            steps {
                dir('maven_app') {
                    bat "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Kubernetes Deploy') {
            steps {
                dir('maven_app') {
                    bat "kubectl apply -f k8s/deployment.yaml"
                }
            }
        }
    }
}
```

---

## Phase 7: Kubernetes Deployment

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: student-marks-deployment

spec:
  replicas: 2

  selector:
    matchLabels:
      app: student-marks

  template:
    metadata:
      labels:
        app: student-marks

    spec:
      containers:
      - name: student-marks-container
        image: student-marks:latest
        imagePullPolicy: Never
        ports:
        - containerPort: 8080
```

---

## Phase 8: Git Commands (Push Code)

```bash
git add .
git commit -m "Student Marks DevOps setup"
git push
```

---

## Phase 9: Build and Deploy Commands

```bash
mvn clean test
mvn clean package

docker build -t student-marks:latest .

kubectl apply -f k8s/deployment.yaml
kubectl get pods
```

---

## Final Flow

Local Code
→ git add
→ git commit
→ git push
→ GitHub
→ Jenkins (Checkout)
→ Maven Build & Test
→ Docker Build
→ Kubernetes Deployment (2 Pods)

---

## Important Notes

* Git pushes latest code to GitHub
* Jenkins pulls updated code
* Maven creates JAR file
* Docker builds image from JAR
* Kubernetes deploys 2 replicas
* Configuration is application-specific

```

---

This is now:
- Complete (Git included)
- Application-specific
- Non-reusable (correct)
- Exam ready
- End-to-end consistent

If you want, I can compress this into a **1-page answer sheet format**.
```
