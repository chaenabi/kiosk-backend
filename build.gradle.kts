import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.asciidoctor.convert") version "1.5.8"

    val kotlinVersion = "1.6.10"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion

    kotlin("kapt") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    kotlin("plugin.noarg") version kotlinVersion

    kotlin("plugin.serialization") version kotlinVersion
}

group = "com.kiosk"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

allOpen {
    annotation("javax.persistence.Entity")
}

noArg {
    annotation("javax.persistence.Entity")
    invokeInitializers = true
}


repositories {
    mavenCentral()
}

val snippetsDir by extra { file("build/generated-snippets") }

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // devtools
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // queryDSL
    implementation("com.querydsl:querydsl-jpa")
    kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")

    // query tracker
    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.0")

    // databases
    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("io.mockk:mockk:1.12.2")
    testImplementation("com.ninja-squad:springmockk:3.1.0")

    // file upload
    implementation("commons-fileupload:commons-fileupload:1.4")
    implementation("commons-io:commons-io:2.11.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

kotlin.sourceSets.main {
    setBuildDir("$buildDir")
}


tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    outputs.dir(snippetsDir)
}

tasks.asciidoctor {
    inputs.dir(snippetsDir)
    dependsOn(tasks.test)

    doFirst {
        delete {
            file("src/main/resources/static/docs")
        }
    }
}

tasks.register("copyHTML", Copy::class) {
    dependsOn(tasks.asciidoctor)
    from(file("build/asciidoc/html5"))
    into(file("src/main/resources/static/docs"))
}

tasks.build {
    dependsOn(tasks.getByName("copyHTML"))
}

tasks.bootJar {
    dependsOn(tasks.asciidoctor)
    dependsOn(tasks.getByName("copyHTML"))
}