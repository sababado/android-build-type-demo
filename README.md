android-build-type-demo
=======================

This is a simple Android project to show how to use build types with the Gradle based build system.
*This project can be imported into Android Build Studio but not Eclipse (as of this commit).*

#Custom Build Types
The Android plugin for Gradle allows the creation of custom build types.
Here, a new build type `dev` is created that inherits all of the properties from the default `debug` build type.
```
android {
    compileSdkVersion 18
    buildToolsVersion "18.0.1"

    defaultConfig {
        minSdkVersion 7
        targetSdkVersion 16
    }

    buildTypes {
        dev.initWith(buildTypes.debug)
    }
}
```

This now allows for a new `src` tree to be created in the project structure:

```
BuildTestApp
-->
    src
    -->
	    dev
		main
```

Here, the `dev` source tree contains only one file, and that is a `strings.xml` file. When building for the `dev` build type these strings will override any existing strings in the `main` build type and append
any strings that do not already exist.

#Environment Setup
The only requirement is to have `ANDROID_HOME` defined as an environment variable.

#Building

##Building With Gradle Wrapper
The gradle wrapper allows anyone to run gradle commands without having to install Gradle on their machine.
The gradle wrapper is included with every new Android Build Studio project and can be also run from the command line.
By default, the gradle wrapper (`gradlew.exe`) is in the root of the project directory.

##Build with Dev API keys
To build the app with API keys for a dev environment, run the command:
```
gradlew assembleDev
```

##Build with Production API keys
To build the app with API keys for a production environment, run the command:
```
gradlew assemble
```