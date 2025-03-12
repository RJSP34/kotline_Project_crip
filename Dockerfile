# Start with a base image containing JDK 17 and Gradle
FROM gradle:jdk17

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy only the Gradle project files (build.gradle, settings.gradle)
COPY build.gradle.kts settings.gradle.kts ./

# Copy the source code
COPY src ./src

# Build the project
RUN gradle build

# Specify the entry point command to run the application
ENTRYPOINT ["java", "-jar", "build/libs/ClientAPI-1.0-SNAPSHOT.jar"]
