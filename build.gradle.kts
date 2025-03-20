plugins {
	id("fabric-loom") version "1.8-SNAPSHOT"
	id("maven-publish")
}

val modId = properties["mod_id"] as String
val minecraftVersion = properties["minecraft_version"] as String

base {
	archivesName = "${properties["artifact_name"]}-fabric-${minecraftVersion}"
}

dependencies {
	minecraft("com.mojang:minecraft:${minecraftVersion}")
	mappings("net.fabricmc:yarn:${properties["mappings_version"]}:v2")
	modImplementation("net.fabricmc:fabric-loader:${properties["fabric_loader_version"]}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${properties["fabric_api_version"]}")
}

loom {
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
	toolchain.languageVersion = JavaLanguageVersion.of(8)

	withSourcesJar()
}

tasks.withType(JavaCompile::class.java).configureEach {
	options.encoding = "UTF-8"
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
		mavenLocal()
	}
}
