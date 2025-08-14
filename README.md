# Smart Habit Tracker

A **Java Swing** desktop application to track daily habits and moods, storing data in an **SQLite database** with smart suggestions based on mood patterns.

## Features
- **Add Habits**: Store and manage your daily habits in a local SQLite database.
- **Log Mood**: Track daily moods with a simple dropdown selector.
- **Smart Suggestions**: Get quick, helpful suggestions based on your current mood.
- **View All Data**: Display all stored habits and moods in the GUI.
- **Lightweight & Offline**: Runs locally without an internet connection.

## Tech Stack
- **Java 8+**
- **Swing** for GUI
- **SQLite** for database
- **JDBC** (sqlite-jdbc driver) for database connectivity

## Project Structure
SmartHabitTracker/
sqlite-jdbc-3.50.3.0.jar
src/
DBHelper.java
HabitTrackerGUI.java
Main.java
MoodSuggestions.java

## How to Run

### 1️⃣ Prerequisites
- Install [Java JDK 8+](https://www.oracle.com/java/technologies/javase-downloads.html)
- Download the [SQLite JDBC driver](https://github.com/xerial/sqlite-jdbc)  
  *(already included in this project as `sqlite-jdbc-3.50.3.0.jar`)*

### 2️⃣ Compile the Project
Open a terminal or PowerShell in the project folder:
javac -cp ".;sqlite-jdbc-3.50.3.0.jar" src/*.java
````

### 3️⃣ Run the Project
java -cp ".;sqlite-jdbc-3.50.3.0.jar;src" Main

⚠ On Mac/Linux, replace `;` with `:` in the `-cp` command.

### 4️⃣ Using the App

* Enter a habit in the text box → click **Add Habit**
* Select your mood from the dropdown → click **Log Mood**
* Click **View Data** to see all entries

## Example Use

1. Habit: `Drink Water` → **Add Habit**
2. Mood: `Stressed` → **Log Mood**
3. Suggestion: *"Take a 5-minute breathing break or go for a short walk."*
4. View all data in the text area

## Screenshots

![UI Screenshot](screenshot.png)
![alt text](<Screenshot 2025-08-14 212916.png>)

## License

This project is open-source and free to use.