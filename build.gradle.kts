plugins {
    kotlin("jvm") version "1.6.20-RC"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":script-definition"))
    implementation(project(":host"))
    implementation(kotlin("script-runtime"))
}
