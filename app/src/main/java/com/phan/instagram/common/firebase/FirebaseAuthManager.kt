package com.phan.instagram.common.firebase

import com.phan.instagram.common.AuthManager
import com.phan.instagram.common.toUnit
import com.phan.instagram.data.firebase.common.auth
import com.google.android.gms.tasks.Task

class FirebaseAuthManager : AuthManager {
    override fun signOut() {
        auth.signOut()
    }

    override fun signIn(email: String, password: String): Task<Unit> =
        auth.signInWithEmailAndPassword(email, password).toUnit()
}