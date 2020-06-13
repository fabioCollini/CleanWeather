object Libs {
    private const val daggerVersion = "2.28"
    private const val hiltVersion = "1.0.0-SNAPSHOT"
    private const val hiltAndroidVersion = "2.28-alpha"
    private const val appCompatVersion = "1.1.0"
    private const val retrofitVersion = "2.9.0"
    private const val coroutinesVersion = "1.3.6"
    private const val okHttpVersion = "4.7.2"
    private const val junitVersion = "4.13"
    private const val assertkVersion = "0.10"
    private const val lifecycleVersion = "2.2.0"
    private const val constraintLayoutVersion = "1.1.3"
    private const val androidTestVersion = "1.2.0"
    private const val espressoVersion = "3.2.0"
    private const val mockkVersion = "1.8.13.kotlin13"
    private const val playServicesVersion = "17.0.0"
    private const val mockitoVersion = "2.28.2"
    private const val mockitoKotlinVersion = "2.2.0"

    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
    const val activityKtx = "androidx.activity:activity-ktx:$appCompatVersion"

    const val dagger = "com.google.dagger:dagger:$daggerVersion"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"

    const val hiltAndroid = "com.google.dagger:hilt-android:$hiltAndroidVersion"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$hiltAndroidVersion"
    const val hiltCommon = "androidx.hilt:hilt-common:$hiltVersion"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:$hiltVersion"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$hiltVersion"
    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:$hiltAndroidVersion"

    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"

    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"

    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"

    const val junit = "junit:junit:$junitVersion"
    const val assertK = "com.willowtreeapps.assertk:assertk:$assertkVersion"
    const val mockk = "io.mockk:mockk:$mockkVersion"
    const val retrofitMock = "com.squareup.retrofit2:retrofit-mock:$retrofitVersion"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$okHttpVersion"
    const val testRunner = "androidx.test:runner:$androidTestVersion"
    const val testCore = "androidx.test:core:$androidTestVersion"
    const val testRules = "androidx.test:rules:$androidTestVersion"
    const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
    const val mockitoAndroid = "org.mockito:mockito-android:$mockitoVersion"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVersion"

    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:$lifecycleVersion"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"

    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"

    const val playServicesLocation = "com.google.android.gms:play-services-location:$playServicesVersion"
}