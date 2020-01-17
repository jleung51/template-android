# Template Android Application

Template Android application.

___

## Template Quickstart

### Application Basics

In `app/src/main/AndroidManifest.xml`:
* Refactor the package (use Smart Rename)
* Remove permissions which are not required

In `app/src/main/res/values/strings.xml`:
* Set the application name

In `app/src/main/java/com/APPNAME/application/SplashActivity.java`:
* Set the next activity after the Splash screen

Replace `app/src/main/res/logo.xml` with your application logo.

Add a new application launcher icon.

### Google Maps

In `app/src/debug/res/values/google_maps_api.xml`:
* Change the Google Maps key to an updated one

In `app/src/main/java/com/APPNAME/application/MapsActivity.java`:
* Modify the Google Maps start location and zoom

In `app/src/main/res/raw/google_maps_styling.json`:
* Modify the Google Maps styling file as per your preference

### Fonts

In `app/src/main/res/font/`:
* Add the `.ttf` files for your preferred fonts

In `app/src/main/res/values/styles.xml`:
* Configure `PrimaryFont` and `PrimaryFontBold` to use the new fonts

In `app/src/main/res/values/colors.xml`:
* Change the colors to the preferred style

### NFC Scanner

In `app/src/main/java/com/APPNAME/application/NfcScanActivity.java`:
* Implement an action and error handling upon reading a new NFC tag (see `processError()` and `processData()`)

In `app/src/main/res/layout/activity_scan.xml`:
* Customize the layout and content

### Nearby Connections

Copy and modify the sample payload callback function in `app/src/main/java/com/APPNAME/application/NearbyConnections.java` to execute an action upon receiving data.

Call the public functions of the class to initialize and advertise/discover other devices.

Add methods to create operations which interact with the other devices.

### Repeating Services

In `app/src/main/java/com/APPNAME/application/RepeatingService.java`:
* Set the interval of recurrence
* Set a task to be repeatedly executed

Start the service in an Activity:
```java
startService(new Intent(this, RepeatingService.class));
```

### Settings

In `app/src/main/res/xml/root_preferences.xml`:
* Change the preferences to the appropriate values and types
* Access the preferences using the `SettingsManager.java` class

___

## Setup

### Android

Install [Android Studio](https://developer.android.com/studio/).

#### Google Maps API Key (for development builds)

Generate a [Google Maps SDK API key (step 1)](https://developers.google.com/maps/documentation/android-sdk/get-api-key).

In the file `app/src/debug/res/values/google_maps_api.xml`, replace the value of the placeholder key with your API key.

Build and run the Android application on a physical or virtual device.

#### Creating a Production Release

##### Step 1: Building a Release Variant

Create a new file in the root directory to hold the secure keystore details:
```shell
cp keystore.properties.template keystore.properties
```

This file will be ignored by Git. Set your keystore information in this file.

Under _Build_, click _Select Build Variant_.

In the _Build Variants_ view which appears, change the **Active Build Variant** from _debug_ to _release_.

Build the application normally to your phone. The keystore credentials will be verified and applied.

##### Step 2: Creating the Signed App

Under _Build_, click _Generate Signed Bundle / APK_.

Follow the instructions, inputting your keystore path and credentials, to create a signed app which can be uploaded to the app store.