# The Movie App - Mvvm Architecture - Rxjava

The Movie App - App consuming a [TMDB API](https://developers.themoviedb.org/3) to display movie details. The app has been built with mvvm architecture principles usign java language.
This app shows the usage of the rxjava, jetpack navigation architecture and this codebase also includes unit testing.

## App packages:
- data: It contains all the data accessing components.
- repository: Provide business logic and data manipulating components.
- di: Dependency providing classes using Dagger2.
- ui: View related components.
- viewmodel: ViewModels for views.
- utils: Utility classes.

## Screenshots:
<p align="center"><kbd><img src="https://user-images.githubusercontent.com/32154905/218306051-f73995fb-4dfc-4758-9c6a-02b1acbafd03.jpg" width="320"></kbd>&nbsp;&nbsp;&nbsp;&nbsp;<kbd><img src="https://user-images.githubusercontent.com/32154905/218306065-71d9655a-fb9b-4687-9468-0ba10f4d53f8.jpg" width="320"></kbd><p>

## Architecture:
Model-View-ViewModel (ie MVVM) is a template of a client application architecture, proposed by John Gossman as an alternative to MVC and MVP patterns when using Data Binding technology. Its concept is to separate data presentation logic from business logic by moving it into particular class for a clear distinction.
<p align="center"><br><img src="https://user-images.githubusercontent.com/32154905/218304092-a8d672bb-68cc-4976-9b69-0e9f9ed32844.png" width="1020"><p>

## MVVM Best Pratice:
- Avoid references to Views in ViewModels.
- Instead of pushing data to the UI, let the UI observe changes to it.
- Distribute responsibilities, add a domain layer if needed.
- Add a data repository as the single-point entry to your data.
- Expose information about the state of your data using a wrapper or another LiveData.
- Consider edge cases, leaks and how long-running operations can affect the instances in your architecture.
- Don’t put logic in the ViewModel that is critical to saving clean state or related to data. Any call you make from a ViewModel can be the last one.

## RxJava:
RxJava is a Java VM implementation of Reactive Extensions: a library for composing asynchronous and event-based programs by using observable sequences.
It extends the observer pattern to support sequences of data/events and adds operators that allow you to compose sequences together declaratively while abstracting away concerns about things like low-level threading, synchronization, thread-safety and concurrent data structures.

## Tech stack

- [Java](https://www.java.com/en/) - Programming language
- [Rxjava](https://github.com/ReactiveX/RxJava) - Used on Android to simplify code that executes asynchronously.
- [Dagger2](https://dagger.dev/) - Used to handle gradle dependencies and config versions.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - For reactive style programming (from VM to UI).
- [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) - Used get lifecyle event of an activity or fragment and performs some action in response to change.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
- [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) - Used to navigate between fragments.
- [Data Binding](https://developer.android.com/topic/libraries/data-binding) - Used to bind UI components in your XML layouts.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.
- [Retrofit](https://github.com/square/retrofit) - Used for REST api communication.
- [OkHttp](https://square.github.io/okhttp/) - HTTP client that's efficient by default: HTTP/2 support allows all requests to the same host to share a socket
- [Glide](https://bumptech.github.io/glide/) - Glide is a fast and efficient image loading library for Android
- [Junit](https://developer.android.com/training/testing/local-tests) - Used as a unit testing framework
- [Truth](https://truth.dev/) - Fluent assertions for Java and Android
