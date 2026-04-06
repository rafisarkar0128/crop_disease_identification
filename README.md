# Crop Disease Identification

Crop Disease Identification is a JavaFX desktop application that is being built as a metadata-driven crop and disease information system. It combines a Java UI with JSON-based crop metadata to support scalable disease knowledge management.

## Current Status

The project currently provides:

- A working JavaFX application entry point
- Modular Java setup with Java 25 and JavaFX 26
- Resource folders for icons, images, and crop metadata
- Metadata files for rice, including bacterial and fungal disease details

## Tech Stack

- Java 25
- Maven
- JavaFX 26
- JSON metadata resources

## Project Structure

```text
src/
	main/
		java/
			module-info.java
			com/
				crop/
					main/
						Main.java
		resources/
			icons/
				app_logo.png
			images/
				bacterial-blight.jpeg
			metadata/
				metadata-index.json
				rice.json
	test/
```

## Metadata Design

This project uses a lightweight index + per-crop metadata strategy:

- metadata-index.json: catalog of available crop metadata files
- One JSON file per crop, for example rice.json
- Each crop JSON contains core crop information and a diseases array

This approach allows adding new crops without changing Java code each time.

## Prerequisites

Install the following:

- JDK 25
- Maven 3.9+

Verify installation:

```bash
java -version
mvn -version
```

## Run the Application

From the project root:

```bash
mvn clean javafx:run
```

## Build the Project

```bash
mvn clean package
```

Build output is generated under target.

## Resource Handling Guidelines

For reliable execution in both IDE and packaged builds:

- Keep static assets under src/main/resources
- Load resources through classpath APIs
- Avoid file system absolute or relative runtime paths for packaged assets

## Next Milestones

- Add metadata loader service in Java
- Render crop and disease data dynamically in UI
- Add search and filter for diseases by category
- Add unit tests for metadata parsing and validation
- Expand metadata for more crops

## Contributing

Contributions are welcome.

Suggested workflow:

1. Create a feature branch
2. Make focused changes
3. Run mvn clean package locally
4. Open a pull request with clear notes

## License

This project is licensed under the Apache License 2.0.

See [LICENSE](LICENSE) for details.

Project attribution details are provided in [NOTICE](NOTICE).
