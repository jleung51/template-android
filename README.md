# Template Android Application

Template Android application.

## Template Quickstart

### Code

In `app/src/main/AndroidManifest.xml`:
* Refactor the package (use Smart Rename)
* Remove permissions which are not required

In `app/src/main/java/com/APPNAME/application/SplashActivity.java`:
* Set the next activity after the Splash screen

In `app/src/main/java/com/APPNAME/application/MapsActivity.java`:
* Modify the Google Maps start location and zoom

### Resources

In `app/src/main/res/values/strings.xml`:
* Set the application name

Replace `app/src/main/res/logo.xml` with your application logo.

In `app/src/main/res/raw/google_maps_styling.json`:
* Modify the Google Maps styling file as per your preference

Add a new application launcher icon.


FONT
COLORS

REMOVE ONBOARDINGACTIVITY ASSETS
CHANGE LOCATIONRECORDERACTIVITY

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