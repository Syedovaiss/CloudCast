# CloudCast 🌤️

CloudCast is a modern weather application built with Kotlin Multiplatform and Jetpack Compose, providing a seamless weather experience across multiple platforms.

## Features

- 🌡️ Real-time weather information
- 📱 Cross-platform support (Android, iOS, Desktop)
- 🎨 Modern Material 3 design
- 🔄 Automatic weather updates
- 📍 Location-based weather
- 🌦️ Detailed weather conditions
- 💨 Wind speed information
- 💧 Humidity levels
- 🌡️ Temperature in both Celsius and Fahrenheit
- 🚀 Built with Kotlin Multiplatform and Jetpack Compose

## Tech Stack

- **Kotlin Multiplatform**: For cross-platform development
- **Jetpack Compose**: For modern UI development
- **Kotlin Coroutines**: For asynchronous programming
- **Kotlin Flow**: For reactive programming
- **Material 3**: For modern design system
- **Clean Architecture**: For maintainable and scalable codebase

## Project Structure

```
composeApp/
├── src/
│   ├── commonMain/           # Shared code
│   │   ├── kotlin/
│   │   │   └── com/ovais/cloudcast/
│   │   │       ├── core/     # Core functionality
│   │   │       ├── home/     # Home screen
                ├── settings/     # Settings screen
│   │   │       └── utils/    # Utility functions
│   │   ├── androidMain/          # Android-specific code
│   └── desktopMain/          # Desktop-specific code
└── build.gradle.kts         # Project configuration
```

### Prerequisites

- Kotlin 1.9.0 or higher
- Android Studio Hedgehog or higher
- JDK 17 or higher
- Gradle 8.0 or higher

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/CloudCast.git
```

2. Open the project in Android Studio

3. Sync the project with Gradle files

4. Run the application:
  - For Android: Select an Android device/emulator and click Run
  - For Desktop: Select the desktop configuration and click Run

## Building

### Android
```bash
./gradlew :composeApp:assembleDebug
```

### Desktop
```bash
./gradlew :composeApp:run
```

## Architecture

The project follows Clean Architecture principles:

- **Presentation Layer**: Contains UI components and ViewModels
- **Domain Layer**: Contains business logic and use cases
- **Data Layer**: Handles data operations and network calls

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [MIT LICENSE](LICENSE.txt) file for details.

## Acknowledgments

- Weather data provided by [OpenWeatherMap](https://www.weatherapi.com/api-explorer.aspx/)

## Contact

Syed Ovais Akhtar - [@syedovaisakhtar](https://linkedin.com/in/syedovaisakhtar)