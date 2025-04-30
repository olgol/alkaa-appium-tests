plugins {
    kotlin("jvm") version "1.9.24"
    id("io.qameta.allure") version "2.8.1"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // JUnit 5
    implementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    //Appium + Selenide
    implementation("io.appium:java-client:8.1.1")
    implementation("com.codeborne:selenide:6.17.0")
    implementation("com.codeborne:selenide-appium:6.17.2")
    implementation("org.seleniumhq.selenium:selenium-java:4.2.1")
    runtimeOnly("org.aspectj:aspectjweaver:1.9.9")

    // Allure
    testImplementation("io.qameta.allure:allure-junit5:2.22.0")
    implementation("io.qameta.allure:allure-selenide:2.23.0")
}


tasks.test {
    useJUnitPlatform()
}

allure {
    aspectjVersion = "1.9.9"
    aspectjweaver = true
    autoconfigure = true
    version = "2.22.0"
    resultsDir = file("build/allure-results")
    reportDir = file("build/allure-report")
    configuration = "testImplementation"
}

kotlin {
    jvmToolchain(17)
}