import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
    kotlin("kapt") version "1.5.30"
    application
}

group = "me.skyle"
version = "1.0-SNAPSHOT"
val arrowVersion = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.arrow-kt:arrow-optics:$arrowVersion")
    kapt("io.arrow-kt:arrow-meta:$arrowVersion")
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

application {
    mainClass.set("MainKt")
}