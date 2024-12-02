plugins {
  id("fabric-loom") version "1.8-SNAPSHOT"
  id("maven-publish")
}

base {
  archivesName = "${properties["artifact_name"].toString()}-fabric"
}

dependencies {
  minecraft("com.mojang:minecraft:${properties["minecraft_version"].toString()}")
  mappings("net.fabricmc:yarn:${properties["mappings_version"].toString()}:v2")
  modImplementation("net.fabricmc:fabric-loader:${properties["fabric_loader_version"].toString()}")
  modImplementation("net.fabricmc.fabric-api:fabric-api:${properties["fabric_api_version"].toString()}")
}

loom {
  splitEnvironmentSourceSets()

  mods {
    create(properties["mod_id"].toString()) {
      sourceSet("main")
    }
  }
}

java {
  toolchain.languageVersion = JavaLanguageVersion.of(21)

  sourceCompatibility = JavaVersion.VERSION_21
  targetCompatibility = JavaVersion.VERSION_21

  withSourcesJar()
}

tasks.withType(JavaCompile::class.java).configureEach {
  options.encoding = "UTF-8"
  options.release = 21
}

tasks.processResources {
  filesMatching("fabric.mod.json") {
    filter { line ->
      Regex("%([a-z_]+)%").replace(line) { match ->
        properties[match.groupValues[1]]?.toString() ?: match.value
      }
    }
  }
}

tasks.named<Jar>("jar") {
  from("LICENSE") {
    rename {
      "LICENSE-${properties["artifact_name"]}"
    }
  }
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      groupId    = project.group.toString()
      artifactId = project.base.archivesName.get()
      version    = project.version.toString()

      from(components["java"])
    }
  }

  repositories {
    maven {
      name = "Mods"
      url = uri("file://${projectDir}/repository")
    }
    maven {
      name = "GithubPackages"
      url = uri(properties["github_packages_url"].toString())
      credentials {
        username = System.getenv("GITHUB_ACTOR")
        password = System.getenv("GITHUB_TOKEN")
      }
    }
  }
}
