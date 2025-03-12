plugins {
    kotlin("jvm") version "1.9.23"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("io.ktor:ktor-server-core:1.6.7")
    implementation("io.ktor:ktor-server-netty:1.6.7")
    implementation("io.ktor:ktor-gson:1.6.7") 
    implementation("io.ktor:ktor-serialization:1.6.7")
    implementation("com.auth0:java-jwt:3.18.1")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}