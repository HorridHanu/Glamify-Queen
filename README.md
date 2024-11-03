# 👑 Glamify-Queen

**Glamify Queen** is an Android app project designed as a feature-rich application for learning and exploring key functionalities of modern mobile app development. This project serves as a hands-on experience, incorporating essential components such as managing UI elements, handling user interactions, and seamless navigation between multiple modules.

Whether you're a beginner learning Android development or an experienced developer looking for an engaging project, **Glamify Queen** provides a structured environment to explore advanced Android features.

---

## ✨ Features

This project is packed with essential and advanced Android features that demonstrate best practices for developing modern mobile applications. Key functionalities include:

### 🏠 Dashboard
- **Feature Description**: A dynamic dashboard that showcases featured content, categories, and banners for promotions or announcements.
- **Purpose**: Helps users quickly navigate through the app with a clean and organized layout.
- **Development Insights**: You'll learn how to build a responsive and engaging UI using traditional XML layouts.



### 🔍 Search Functionality
- **Feature Description**: A powerful search feature enabling users to find specific items or content.
- **Purpose**: Provides quick access to the desired content using search queries.
- **Development Insights**: This feature involves setting up **RecyclerView**, **ViewModel**, and **LiveData** to implement search results efficiently.



### 🛒 Cart Functionality
- **Feature Description**: A cart functionality where users can add and review items.
- **Purpose**: Simulates shopping cart-like behavior for tracking items selected for further action.
- **Development Insights**: You'll explore the management of **local storage**, building **RecyclerView adapters**, and handling user interactions.



### 👤 Profile Management
- **Feature Description**: Manage and update user profiles with personal details.
- **Purpose**: Allows users to view and update their account information and preferences.
- **Development Insights**: You'll learn about **data binding**, **form handling**, and user data management in a mobile context.


### 🔐 Account Authentication
- **Feature Description**: Secure user authentication through email, password, Google, and GitHub login, along with a forgot password feature.
- **Purpose**: Ensures that user accounts are secure and easily accessible.
- **Development Insights**: Learn about integrating **Firebase Authentication** to handle user credentials and maintain session management.


### 🚚 Order Tracking
- **Feature Description**: Real-time tracking for orders placed or ongoing processes within the app.
- **Purpose**: Keeps users informed of the status of their actions.
- **Development Insights**: Focus on **Firebase Realtime Database** to fetch and update order status data dynamically.

---

### 📸 Application Preview

Here is a quick preview of the **Glamify Queen** app:

<p align="center">
  <img src="https://github.com/user-attachments/assets/e9bed5dc-63cc-4965-96a0-7cdefbdb5e48" alt="Screenshot 1" width="150" />
  <img src="https://github.com/user-attachments/assets/0437de0b-4eab-467b-aa6a-2023bfdef6a0" alt="Screenshot 2" width="150" />
  <img src="https://github.com/user-attachments/assets/796e2d8e-fde1-4c46-a026-39a5c3677fb5" alt="Screenshot 3" width="150" />
  <img src="https://github.com/user-attachments/assets/42f9e52f-a317-455b-83a7-d4b74fcf763a" alt="Screenshot 4" width="150" />
  <img src="https://github.com/user-attachments/assets/ebac4d3f-1db3-4974-aaf2-c538bcb177d7" alt="Screenshot 5" width="150" />
  <img src="https://github.com/user-attachments/assets/dec065f7-a1f2-46ae-a6cd-f751ede76347" alt="Screenshot 6" width="150" />
  <img src="https://github.com/user-attachments/assets/1e326da9-a238-4a00-89fb-0c3eabe8f929" alt="Screenshot 7" width="150" />
</p>


---

## 🛠 Tech Stack

The **Glamify Queen** project is built using modern technologies, following the latest Android development standards:

- **Language**: Java – One of the official languages for Android development, widely supported and reliable.
- **Architecture**: MVVM (Model-View-ViewModel) – This architecture separates business logic from UI, making the app easier to maintain and scale.
- **UI Components**: 
  - **XML Layouts** – Traditional Android layouts for building user interfaces.
  - **RecyclerView** – For handling dynamic lists of data efficiently.
- **Data Handling**: 
  - **Firebase Realtime Database** – For storing and syncing app data in real-time.
  - **Firebase Cloud Firestore** – For scalable, cloud-based data storage.
- **State Management**: 
  - **LiveData** – For managing UI-related data in a lifecycle-conscious way.
  - **ViewModel** – For storing UI-related data that survives configuration changes.

### Firebase Services

- **Firebase Authentication** – For user authentication with email, password, Google, GitHub, and password recovery.
- **Firebase Realtime Database** – For real-time data sync and storage.
- **Firebase Storage** – For storing user-generated content such as images.
- **Firebase Analytics** – For tracking user interactions and understanding app usage.

---

## 📝 Prerequisites

Before setting up the project, ensure you have the following installed:

- **Android Studio** (latest version recommended)
- **Java SDK** (required version for Android Studio)
- A **Firebase account** to set up authentication, databases, and storage.

---

## 🚀 Getting Started

To get a local copy of the **Glamify Queen** project up and running, follow these simple steps:

### 1. Open in Android Studio
- Open **Android Studio**.
- Select **File** > **Open** and navigate to the project directory.
- Let Android Studio configure the project and install the required dependencies.

### 2. Configure Firebase
- Go to the **[Firebase Console](https://firebase.google.com/)**.
- Create a new project and configure **Firebase Authentication**, **Realtime Database**, **Cloud Firestore**, and **Firebase Storage**.
- Download the `google-services.json` file and add it to your project in the `app/` directory.

### 3. Build and Run
- Ensure you have an Android Virtual Device (AVD) or physical device connected.
- Click on the **Run** button in Android Studio to build and deploy the app on your emulator/device.

---

## 🧪 Running Tests

The **Glamify Queen** project includes unit and UI tests to ensure the app works as expected.

### Unit Tests
Run unit tests with the following command:

```bash
./gradlew test
