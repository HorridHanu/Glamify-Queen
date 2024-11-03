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

  ![Dashboard Screenshot](path/to/dashboard_screenshot.png)

### 🔍 Search Functionality
- **Feature Description**: A powerful search feature enabling users to find specific items or content.
- **Purpose**: Provides quick access to the desired content using search queries.
- **Development Insights**: This feature involves setting up **RecyclerView**, **ViewModel**, and **LiveData** to implement search results efficiently.

  ![Search Screenshot](path/to/search_screenshot.png)

### 🛒 Cart Functionality
- **Feature Description**: A cart functionality where users can add and review items.
- **Purpose**: Simulates shopping cart-like behavior for tracking items selected for further action.
- **Development Insights**: You'll explore the management of **local storage**, building **RecyclerView adapters**, and handling user interactions.

  ![Cart Screenshot](path/to/cart_screenshot.png)

### 👤 Profile Management
- **Feature Description**: Manage and update user profiles with personal details.
- **Purpose**: Allows users to view and update their account information and preferences.
- **Development Insights**: You'll learn about **data binding**, **form handling**, and user data management in a mobile context.

  ![Profile Screenshot](path/to/profile_screenshot.png)

### 🔐 Account Authentication
- **Feature Description**: Secure user authentication through email, password, Google, and GitHub login, along with a forgot password feature.
- **Purpose**: Ensures that user accounts are secure and easily accessible.
- **Development Insights**: Learn about integrating **Firebase Authentication** to handle user credentials and maintain session management.

![Sign Up](https://github.com/user-attachments/assets/ba5a1947-5c52-4fc7-a6b6-81897685e9d9)
![Sign In](https://github.com/user-attachments/assets/fe3c6c26-729b-47f5-95f3-482b40c37b6f)

### 🚚 Order Tracking
- **Feature Description**: Real-time tracking for orders placed or ongoing processes within the app.
- **Purpose**: Keeps users informed of the status of their actions.
- **Development Insights**: Focus on **Firebase Realtime Database** to fetch and update order status data dynamically.

  ![Order Tracking Screenshot](path/to/order_tracking_screenshot.png)

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
