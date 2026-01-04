# VioFit: AI-Powered Hyper-Personalized Diet Consultant 🥗✨

**VioFit** is a cutting-edge Android application designed to bridge the gap between advanced artificial intelligence and personal health management. Leveraging Google’s **Gemini AI Engine**, VioFit generates real-time, data-driven nutritional plans tailored to a user's unique biological profile and fitness objectives.



---

## 🌟 Key Features & Functionalities

### 🧠 1. Intelligent Diet Synthesis (Gemini AI)
- **Real-Time Analysis:** Dynamically generates a structured plan (Meals, Hydration, Exercises) based on user-specific inputs like Age, Weight, Stress Level, and Sleep.
- **Natural Language Processing:** Processes complex nutritional data into clean, readable, and actionable advice.
- **Smart Formatting:** Custom logic to strip markdown symbols for a clean, professional mobile UI display.

### 📊 2. Dynamic User Dashboard
- **Persistence Logic:** Uses `SharedPreferences` to save generated plans locally, allowing users to access their diet anytime without re-generating.
- **Smart Day Counter:** An automated tracking algorithm that calculates the difference between the plan's start date and the current date to show progress (e.g., "Day 3 of 14").
- **Visual Progress Hub:** A high-level overview of the current plan with a dynamic progress bar for journey completion.

### 🎨 3. Premium Aesthetic UI/UX
- **Violet Gradient Theme:** A modern design language using deep purples and soft lavenders to represent wellness.
- **3-Dot Overflow Menu:** A clean Toolbar-based navigation for "Create New Plan" and "Logout" to keep the dashboard clutter-free.
- **Card-Based Architecture:** High-elevation Material Design cards for better content scannability and visual hierarchy.

### 🔒 4. User Authentication & Session Management
- **Firebase Integration:** Robust Sign-Up and Login system to secure user data.
- **Intelligent Splash Logic:** State-based routing that detects if a user is logged in and whether they have an active plan, redirecting them to the appropriate screen instantly.

---

## 🛠️ Technical Architecture

| Component | Technology |
| :--- | :--- |
| **Language** | Java (Android SDK) |
| **AI Backend** | Google Gemini Flash 1.5 |
| **Authentication** | Firebase Auth |
| **Local Storage** | Android SharedPreferences |
| **Navigation** | State-based Routing & Overflow Menu |
| **UI Components** | XML, Material Design 3, CardView, Custom Progress Bars |

---

## 🚀 Installation & Configuration

1. **Clone the Repository**
   ```bash
   git clone [https://github.com/YourUsername/VioFit.git](https://github.com/YourUsername/VioFit.git)