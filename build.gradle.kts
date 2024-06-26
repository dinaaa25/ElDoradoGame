plugins {
    id("java")
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("jacoco")

}

group = "org.utwente"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.1")
    //https://java.testcontainers.org/
    // probably not needed when running pure java but allows for integration tests esp when using external services
    implementation(platform("org.testcontainers:testcontainers-bom:1.19.8"))
    testImplementation("org.testcontainers:junit-jupiter")
    // lombok
    // generates getters and setters to avoid writing boilerplate code.
    compileOnly("org.projectlombok:lombok:1.18.32")
	annotationProcessor("org.projectlombok:lombok:1.18.32")
	testCompileOnly("org.projectlombok:lombok:1.18.32")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.32")
   // dotenv
    implementation("io.github.cdimascio:dotenv-java:3.0.0")
    // mockito
    testImplementation("org.mockito:mockito-core:5.11.0")
    implementation("org.mockito:mockito-junit-jupiter:5.11.0")
    // logging
    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("ch.qos.logback:logback-classic:1.5.6")
}



tasks.test {
    useJUnitPlatform()
    jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
    finalizedBy(tasks.jacocoTestReport) 
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Xlint:unchecked")
}


jacoco {
    toolVersion = "0.8.12"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) 

    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}


tasks {
    val javadoc by getting(Javadoc::class) {
        source = sourceSets["main"].allJava

        classpath = sourceSets["main"].compileClasspath


        destinationDir = layout.buildDirectory.dir("docs/javadoc").get().asFile

        options {
             val opts = this as StandardJavadocDocletOptions
            opts.encoding = "UTF-8"
            opts.charSet = "UTF-8"
            opts.links("https://docs.oracle.com/javase/21/docs/api/")        }
    }
}




application {
    mainClass.set("org.utwente.Main")
}

tasks.shadowJar {
    archiveFileName.set("ElDorado-1.0-SNAPSHOT-all.jar")
    manifest {
        attributes["Main-Class"] = "org.utwente.Main"
    }
}
