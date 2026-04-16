transmit-between-ports/
│── pom.xml
│
└── src/
├── main/java/com/socketapp/
│ Server.java
│ Client.java 
│
└── test/java/com/socketapp/ AppTest.java

pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.socketapp</groupId>
    <artifactId>transmit-between-ports</artifactId>
    <version>1.0-SNAPSHOT</version>
    <dependencies>
        <!-- JUnit -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <!-- Runs tests -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0</version>
            </plugin>
        </plugins>
    </build>
</project>

Server.java
package com.socketapp;
import java.io.*;
import java.net.*;
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started on port 5000...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String message = input.readLine();
            System.out.println("Received: " + message);
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

Client.java
package com.socketapp;
import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            output.println("Hello from Client");
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

AppTest.java
package com.socketapp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class AppTest {
    @Test
    void testBasic() {
        assertEquals(2, 1 + 1);
    }
}

IN TERMINAL -> mvn clean , mvn compile , mvn test , mvn package

CREATE AND ADD REPO

PIPELINE SCRIPT
pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                git branch: 'main',
                url: 'https://github.com/Srilaya18/transmit_bw_port.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }

    }
}

Dockerfile
FROM eclipse-temurin:17
WORKDIR /app
COPY target/transmit-between-ports-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-cp", "app.jar", "com.socketapp.Server"]

PUSH TO GIT
git add .
git commit -m “2”
git push

UPDATE SCRIPT
pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                git branch: 'main',
                url: 'https://github.com/Srilaya18/transmit_bw_port.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }
        stage('Docker Build') {
            steps {
                bat 'docker build -t transmit-app .'
            }
        }
        stage('Docker Run') {
            steps {
                bat 'docker run -d -p 5000:5000 transmit-app'
            }
        }
    }
}


SHOW IMAGE IN DOCKER -> run get CONTAINER

THEN IN TERMINAL
docker images
docker ps
docker logs <container_id>

cd transmit-between-ports
java -cp target/classes com.socketapp.Client

docker logs <container_id>




cd src/main/java
javac com/socketapp/Client.java
java -cp . com.socketapp.Client

docker logs <container_id>
