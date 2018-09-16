package com.johanfornander.kotlintest1

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

private var TAG = "firebase"

class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        val refreshedToken = FirebaseInstanceId.getInstance().getToken()
        Log.d(TAG, "Refreshed token: $refreshedToken")
    }


}
