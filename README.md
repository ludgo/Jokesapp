# Jokesapp

This is a fully educational Android application based on free course materials at Udacity platform. The aim is to provide users with jokes pulled from Google server.

## Dependencies

From the user perspective, only [app](https://github.com/ludgo/Jokesapp/tree/master/app) and [androidjokelib](https://github.com/ludgo/Jokesapp/tree/master/androidjokelib) modules are necessary. Following libraries are included in these two:

- v7 Appcompat library `com.android.support:appcompat-v7:23.1.0`
- Google Mobile Ads `com.google.android.gms:play-services-ads:8.3.0`

Testing dependencies:

- AndroidJUnitRunner `com.android.support.test:runner:0.4`
- Annotations `com.android.support:support-annotations:23.1.0`
- Google Play Services `com.google.android.gms:play-services:8.3.0`
- JUnit `junit:junit:4.12`

For development, the newest Android SDK and Google App Engine Java Endpoints Module were used.

## Functionality flow

1. An activity offering a joke with banner advertising is displayed
2. User presses the button
3. An interstitial ad is displayed (free flavor only)
4. App sends a request to the App Engine deployed backend server
5. A joke is pulled from the model Java library
6. Another activity from Android library displays the joke

## Motivation

The app focuses on multi-modularity and functionality, rather than on design or UX. When extending the initial version of the app, the implementation steps followed Udacity's *Gradle for Android and Java* [project](https://github.com/udacity/ud867/tree/master/FinalProject) (see also commits).

## Acknowledgments

Special thanks to Udacity for providing free access to the course materials prepared in collaboration with Gradle team.
