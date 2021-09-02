/*
 * This file was generated by the Gradle 'init' task.
 *
 * The settings file is used to specify which projects to include in your build.
 *
 * Detailed information about configuring a multi-project build in Gradle can be found
 * in the user manual at https://docs.gradle.org/6.3/userguide/multi_project_builds.html
 */

 import org.danilopianini.VersionAliases.justAdditionalAliases

 plugins {
     id("de.fayard.refreshVersions") version "0.10.1"
 }

 refreshVersions {
     extraArtifactVersionKeyRules = justAdditionalAliases
 }

 buildscript {
     repositories {
         gradlePluginPortal()
         mavenCentral()
     }
     dependencies {
         classpath("org.danilopianini:refreshversions-aliases:+")
     }
 }


rootProject.name = "OOP20-alt-sim"
