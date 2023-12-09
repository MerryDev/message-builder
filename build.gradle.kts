plugins {
    java
    `java-library`
    id("io.freefair.lombok") version "8.4"
}

group = "net.quantrax"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")

    implementation("com.github.seeseemelk:MockBukkit-v1.20:3.58.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    test {
        useJUnitPlatform()
    }
}
