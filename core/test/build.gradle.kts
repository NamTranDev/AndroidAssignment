plugins {
    id("java-library")
    id("java-test-fixtures")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies{
    api(libs.junit)
    api("org.mockito:mockito-core:5.13.0")
    api("androidx.arch.core:core-testing:2.1.0")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.1")
}
