Android Shared Preferences Helper
===============

[![Build Status](https://travis-ci.org/SeanZoR/shared-preferences-helper.svg)](https://travis-ci.org/SeanZoR/shared-preferences-helper)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-shared--preferences--helper-green.svg?style=flat)](https://android-arsenal.com/details/1/2471)

Android Library to handle SharedPreferences boilerplate code and other tools

## Download
```groovy
dependencies {
  compile 'com.github.seanzor:shared-preferences-helper:1.0.1'
}
```

## Usage
Define the preferences keys in a resource file:

```xml
<resources>
    <string name="pref_number_of_runs">pref_number_of_runs</string>
</resources>
```

And use them:

```java
// As in most cases, create a default shared preference
final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

// Create the helper which will wrap the SharedPreferences we just created
mPref = new SharedPrefHelper(getResources(), defaultSharedPreferences);

// Sample for number of times the app ran, deafult of 0
int numberOfTimesRan = mPref.getInt(R.string.pref_number_of_runs, 0);
numberOfTimesRan++;

// Now apply the change to be persistent
mPref.applyInt(R.string.pref_number_of_runs, numberOfTimesRan);

// Display the result
((TextView)findViewById(R.id.activityMainResultField)).setText(
        String.valueOf(numberOfTimesRan));

```


## License

    Copyright 2015 Sean Katz

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
