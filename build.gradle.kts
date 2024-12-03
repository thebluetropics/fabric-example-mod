plugins {
  id("fabric-loom") version "1.8-SNAPSHOT"
  id("maven-publish")
}

// Maven
val artifactName        = properties["artifact_name"]         as String

// Mod
val modId               = properties["mod_id"]                as String

// Minecraft
val minecraftVersion    = properties["minecraft_version"]     as String

// Fabric
val fabricLoaderVersion = properties["fabric_loader_version"] as String
val fabricApiVersion    = properties["fabric_api_version"]    as String

// Mappings
val mappingsVersion     = properties["mappings_version"]      as String

// Github
val githubPackagesUrl   = properties["github_packages_url"]   as String

base {
  archivesName = "${artifactName}-fabric-${minecraftVersion}"
}

dependencies {
  minecraft("com.mojang:minecraft:${minecraftVersion}")
  mappings("net.fabricmc:yarn:${mappingsVersion}:v2")
  modImplementation("net.fabricmc:fabric-loader:${fabricLoaderVersion}")
  modImplementation("net.fabricmc.fabric-api:fabric-api:${fabricApiVersion}")
}

loom {
  splitEnvironmentSourceSets()

  runs {
    named("client") {
      runDir = "run/client"
    }
    named("server") {
      runDir = "run/server"
    }
  }

  mods {
    create(modId) {
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
      "LICENSE-${artifactName}"
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
      url = uri(githubPackagesUrl)
      credentials {
        username = System.getenv("GITHUB_ACTOR")
        password = System.getenv("GITHUB_TOKEN")
      }
    }
  }
}
