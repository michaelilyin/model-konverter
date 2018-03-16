import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileOutputStream
import java.net.URI

buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.2.21"

    repositories {
        mavenCentral()
        maven {
            url = URI("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath(kotlinModule("gradle-plugin", kotlin_version))
        classpath("me.champeau.gradle:jmh-gradle-plugin:0.4.5")
    }
}

group = "model-konverter"
version = "1.0-SNAPSHOT"

apply {
    plugin("kotlin")
    plugin("me.champeau.gradle.jmh")
}

repositories {
    mavenCentral()
}

val kotlin_version: String by extra
val kotlin_logging = "1.4.9"

val spek_version = "1.1.5"
val junit_platform_runner = "1.0.0"
val expekt = "0.5.0"
val logback_classic = "1.2.3"

dependencies {
    compile(kotlinModule("stdlib-jdk8", kotlin_version))
    compile(kotlinModule("reflect", kotlin_version))
    compile("io.github.microutils:kotlin-logging:$kotlin_logging")

    testCompile("org.jetbrains.spek:spek-api:$spek_version")
    testCompile("org.jetbrains.spek:spek-junit-platform-engine:$spek_version")
    testCompile("org.junit.platform:junit-platform-runner:$junit_platform_runner")
    testCompile("com.winterbe:expekt:$expekt")
    testCompile("ch.qos.logback:logback-classic:$logback_classic")
    compile("org.openjdk.jmh:jmh-core:1.20")
    //https://habrahabr.ru/post/349914/
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}