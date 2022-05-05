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
    implementation(kotlin("scripting-dependencies"))
    implementation(kotlin("scripting-dependencies-maven"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
}
