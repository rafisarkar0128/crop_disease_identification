# Crop Disease Identification

Starter JavaFX application for a crop disease identification project.

The current app is a minimal two-scene JavaFX UI used as a foundation:

- `primary.fxml` controlled by `PrimaryController`
- `secondary.fxml` controlled by `SecondaryController`
- scene switching handled through `Main.setRoot(...)`

## Tech Stack

- Java 25
- Maven
- JavaFX 26 (`javafx-controls`, `javafx-fxml`)

## Project Structure

```text
src/
	main/
		java/
			com/main/
				Main.java
				PrimaryController.java
				SecondaryController.java
		resources/
			com/main/
				primary.fxml
				secondary.fxml
```

## Prerequisites

Make sure you have:

- JDK 25 installed
- Maven installed and available in your PATH

Check versions:

```bash
java -version
mvn -version
```

## Run the Application

From the project root:

```bash
mvn clean javafx:run
```

The app starts with the Primary view and lets you switch between Primary and Secondary scenes.

## Build

```bash
mvn clean package
```

Compiled outputs are generated under `target/`.
