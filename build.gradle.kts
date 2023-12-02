import io.freefair.gradle.plugins.lombok.LombokPlugin

plugins {
    java
    `java-library`
    id("io.freefair.lombok") version "8.4"
}

group = "net.quantrax"
version = "1.0-SNAPSHOT"


dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}

subprojects {
    apply {
        plugin<JavaPlugin>()
        plugin<JavaLibraryPlugin>()
        plugin<LombokPlugin>()
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    }

    tasks {
        test {
            useJUnitPlatform()
        }
    }
}
