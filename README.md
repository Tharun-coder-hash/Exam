# DevOps + Git Complete Exam Sheet

---

## 1. GIT COMMANDS

### Setup
git --version  
git config --global user.name "Your Name"  
git config --global user.email "you@example.com"  

---

### Create / Clone Repo
git init  
git clone <repo-url>  

---

### Check Status
git status  

---

### Add Files
git add file.txt  
git add .  

---

### Commit
git commit -m "message"  
git commit --amend  

---

### Push to GitHub
git push origin main  
git push -u origin main  

---

### Pull Changes
git pull origin main  
git pull origin main --rebase  

---

### View History
git log  
git log --oneline  
git log --oneline --graph --all  

---

### Branching
git branch  
git branch new-branch  
git checkout new-branch  
git checkout -b new-branch  

---

### Merge
git merge branch-name  

---

### Remote Commands
git remote add origin <url>  
git remote -v  

---

### Undo / Reset
git restore file.txt  
git restore --staged file.txt  
git reset --soft HEAD~1  
git reset --mixed HEAD~1  
git reset --hard HEAD~1  
git revert <commit-id>  

---

### Stash
git stash  
git stash list  
git stash apply  
git stash pop  

---

### Tagging
git tag v1.0  
git push origin v1.0  

---

## 2. MAVEN COMMANDS

mvn clean test  
mvn clean package  

Output:
target/*.jar  

---

## 3. DOCKER COMMANDS

### Build Image
docker build -t app-name:latest .  

### List Images
docker images  

### Run Container
docker run -p 8080:8080 app-name:latest  

### Running Containers
docker ps  

### Stop Container
docker stop <container_id>  

### Logs
docker logs <container_id>  

### Remove Image
docker rmi app-name:latest  

---

## 4. KUBERNETES COMMANDS

### Start Cluster
minikube start  

### Connect Docker
minikube docker-env  

---

### Deploy Application
kubectl apply -f deployment.yaml  

---

### Check Pods
kubectl get pods  

---

### Check Deployments
kubectl get deployments  

---

### Debug Pod
kubectl describe pod <pod-name>  

---

### Logs
kubectl logs <pod-name>  

---

### Delete Deployment
kubectl delete -f deployment.yaml  

---

### All Resources
kubectl get all  

---

## 5. JENKINS BASIC PIPELINE

pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Build'
            }
        }
        stage('Test') {
            steps {
                echo 'Test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploy'
            }
        }
    }
}

---

## 6. FINAL FLOW

git add  
→ git commit  
→ git push origin main  
→ mvn clean package  
→ docker build  
→ kubectl apply  
→ kubectl get pods  

---

## IMPORTANT NOTES

- Git → version control  
- Maven → build jar  
- Docker → container image  
- Kubernetes → runs 2 replicas  
- Jenkins → automates pipeline  


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
