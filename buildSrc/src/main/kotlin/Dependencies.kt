object Versions {
    const val hilt = "1.0.0-SNAPSHOT"
    const val hiltAndroid = "2.28-alpha"
    const val activityKtx = "1.1.0"
}

object Libs {
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroid}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroid}"
    const val hiltCommon = "androidx.hilt:hilt-common:${Versions.hilt}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hilt}"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt}"
    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Versions.hiltAndroid}"
}