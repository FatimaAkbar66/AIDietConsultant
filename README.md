# VioFit: AI-Powered Hyper-Personalized Diet Consultant 🥗✨

**VioFit** is a cutting-edge Android application designed to bridge the gap between advanced artificial intelligence and personal health management. Leveraging Google’s **Gemini AI Engine**, VioFit generates real-time, data-driven nutritional plans tailored to a user's unique biological profile and fitness objectives.

---

## 🌟 Key Features & Functionalities

### 🧠 1. Intelligent Diet Synthesis (Gemini AI)
- **Real-Time Analysis:** Dynamically generates a 3-meal plan (Breakfast, Lunch, Dinner) based on user-specific inputs like Age, Weight, Name, and Goals.
- **Natural Language Processing:** Processes complex nutritional data into clean, readable, and actionable advice.
- **Smart Formatting:** Custom logic to strip markdown symbols for a clean, professional mobile UI display.

### 🎨 2. Premium Aesthetic UI/UX
- **Violet Gradient Theme:** A modern, cohesive design language using deep purples and soft lavenders to represent wellness and luxury.
- **Card-Based Architecture:** High-elevation Material Design cards for better content scannability and visual hierarchy.
- **Adaptive Splash Screen:** A seamless startup experience with a zoom-out logo animation that matches the Android 12+ system standard.

### 🔒 3. User Authentication & Security
- **Firebase Integration:** Robust Sign-Up and Login system to secure user data.
- **Session Management:** One-tap Logout functionality with a custom-designed header interface.
- **Secure API Infrastructure:** Gemini API keys are shielded using `local.properties` and `BuildConfig` to prevent exposure in public repositories.

### 📊 4. User Profiling & Progress Tracking
- **Dynamic Goal Setting:** Supports Weight Loss, Muscle Gain, and Maintenance tracks.
- **Visual Progress Indicators:** Integrated custom progress bars to visualize daily calorie and water intake goals.

---

## 🛠️ Technical Architecture

| Component | Technology |
| :--- | :--- |
| **Language** | Java (Android SDK) |
| **AI Backend** | Google Gemini Flash 1.5 |
| **Authentication** | Firebase Auth |
| **UI Components** | XML, Material Design 3, CardView, Lottie (Optional) |
| **Security** | Gradle Build Config & Secret Tooling |

---

## 🚀 Installation & Configuration

To run VioFit locally, follow these steps:

1. **Clone the Repository**
   ```bash
   git clone [https://github.com/YourUsername/VioFit.git](https://github.com/YourUsername/VioFit.git)
