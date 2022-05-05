plugins {
    kotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("scripting-common"))
    implementation(kotlin("scripting-jvm"))
    implementation(kotlin("scripting-jvm-host"))
    implementation(project(":script-definition"))
}
