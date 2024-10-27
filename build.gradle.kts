plugins {
  id("fabric-loom") version "1.8-SNAPSHOT"
  id("maven-publish")
}

val minecraftVersion = properties["minecraft_version"] as String

base {
  archivesName = "${properties["artifact_name"].toString()}-fabric-${minecraftVersion}"
}

dependencies {
  minecraft("com.mojang:minecraft:${minecraftVersion}")
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
  withSourcesJar()
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      artifactId = base.archivesName.get() + "-${version}"
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

tasks.withType(JavaCompile::class.java).configureEach {
  options.encoding = "UTF-8"
}
