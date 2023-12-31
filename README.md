# Server Driven UI With Compose

Server-driven UI is an architectural pattern where the user interface (UI) of an application is determined and managed by the server rather than being hard-coded in the client-side codebase. In this approach, the server sends data and instructions on how to construct the UI to the client application, and the client dynamically renders the UI accordingly.

In a server-driven UI architecture, the client-side application acts as a rendering engine, responsible for displaying the UI elements sent by the server. The server holds control over the UI logic and can make real-time changes to the UI without requiring the client app to be updated. The server can customize the UI for different users, and scenarios, or even perform A/B testing by serving different UI variations to different users.

Key features of server-driven UI include:

- <h4>Dynamic UI Generation</h4> The UI components are generated on the server-side and sent to the client as data. This enables real-time updates and UI changes without requiring app updates.
- <h4>Flexibility and Customization</h4> The server can customize the UI based on user preferences, roles, locations, or other criteria.
- <h4>Faster Iterations</h4>  Since UI changes are managed on the server-side, updates and improvements can be rolled out quickly without waiting for the client app to be updated.
- <h4>Reduced App Size</h4>  The client app can be lightweight as the UI logic is offloaded to the server.
- <h4>Personalization</h4> The server can tailor the UI to individual users, providing a more personalized experience.


## Check this [Video](https://streamable.com/jfi0la)


## ⚒️: Tech Stack.
1. [Kotlin](https://developer.android.com/kotlin)
2. [Jetpack Compose](https://developer.android.com/jetpack/compose/setup?authuser=1#:~:text=%20To%20create%20a%20new%20project%20that%20include,Kotlin%20is%20the...%206%20Click%20Finish.%20See%20More.)
3. [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
4. [Firebase as basckend](https://firebase.google.com/)


## Prerequisites
Before running the app, ensure that you have the following:

- Android Studio (version 2022.2.1 or higher).
- Java Development Kit (JDK) (version 1.8 or higher).
- Create project on firebase and add all dependencies necessary.


## Support
If you found this repository helpful and learned even a little something from it, then consider supporting me by giving this repo a Star ⭐️
