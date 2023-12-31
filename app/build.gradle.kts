plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.api_call"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.api_call"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
//    implementation ("com.example:checkview-library:1.0.0")
//    implementation ("com.toptoche.searchablespinner:searchablespinnerlibrary:1.3.1")
//    implementation("com.squareup.okhttp3:okhttp:4.11.0")
//    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation ("com.squareup.retrofit2:converter-gson:2.5.0")
    implementation ("com.squareup.retrofit2:retrofit:2.8.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.8.1")
    implementation ("com.google.code.gson:gson:2.8.6")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.5.0")
//    implementation(project(mapOf("path" to ":app")))
//    implementation ("com.bumptech.glide.request.RequestOptions")
   implementation ("com.github.bumptech.glide:glide:4.14.2")
    testImplementation("junit:junit:4.13.2")
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.23")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}