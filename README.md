# Server Driven UI With Compose

Server-driven UI is an architectural pattern where the user interface (UI) of an application is determined and managed by the server rather than being hard-coded in the client-side codebase. In this approach, the server sends data and instructions on how to construct the UI to the client application, and the client dynamically renders the UI accordingly.

In a server-driven UI architecture, the client-side application acts as a rendering engine, responsible for displaying the UI elements sent by the server. The server holds control over the UI logic and can make real-time changes to the UI without requiring the client app to be updated. The server can customize the UI for different users, and scenarios, or even perform A/B testing by serving different UI variations to different users.

Key features of server-driven UI include:

- <h4> Dynamic UI Generation</h4>:The UI components are generated on the server-side and sent to the client as data. This enables real-time updates and UI changes without requiring app updates.
