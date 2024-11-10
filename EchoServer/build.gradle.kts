plugins {
    id("java")
    id("application")
}

group = "il.ac.kinneret.mjmay"
version = "5785"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "il.ac.kinneret.mjmay.udpEchoServer"
    }
}

application{
    mainClass.set("il.ac.kinneret.mjmay.udpEchoServer")
}