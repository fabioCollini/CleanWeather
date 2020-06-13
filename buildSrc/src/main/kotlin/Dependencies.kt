object Versions {
    const val dagger = "2.28"
    const val hilt = "1.0.0-SNAPSHOT"
    const val hiltAndroid = "2.28-alpha"
    const val appCompat = "1.1.0"
    const val retrofit = "2.9.0"
    const val coroutines = "1.3.6"
    const val okHttp = "4.7.2"
    const val junit = "4.13"
    const val assertk = "0.10"
    const val lifecycle = "2.2.0"
    const val constraintLayout = "1.1.3"
    const val androidTest = "1.2.0"
    const val espresso = "3.2.0"
    const val mockk = "1.8.13.kotlin13"
    const val playServices = "17.0.0"
    const val mockito = "2.28.2"
    const val mockitoKotlin = "2.2.0"
}

object Libs {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.appCompat}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroid}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroid}"
    const val hiltCommon = "androidx.hilt:hilt-common:${Versions.hilt}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hilt}"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt}"
    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Versions.hiltAndroid}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    const val junit = "junit:junit:${Versions.junit}"
    const val assertK = "com.willowtreeapps.assertk:assertk:${Versions.assertk}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val retrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
    const val testRunner = "androidx.test:runner:${Versions.androidTest}"
    const val testCore = "androidx.test:core:${Versions.androidTest}"
    const val testRules = "androidx.test:rules:${Versions.androidTest}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockito}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"

    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val playServicesLocation = "com.google.android.gms:play-services-location:${Versions.playServices}"
}