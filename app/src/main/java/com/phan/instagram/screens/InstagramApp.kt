package com.phan.instagram.screens

import android.app.Application
import com.phan.instagram.common.firebase.FirebaseAuthManager
import com.phan.instagram.data.firebase.FirebaseFeedPostsRepository
import com.phan.instagram.data.firebase.FirebaseNotificationsRepository
import com.phan.instagram.data.firebase.FirebaseSearchRepository
import com.phan.instagram.data.firebase.FirebaseUsersRepository
import com.phan.instagram.screens.notifications.NotificationsCreator
import com.phan.instagram.screens.search.SearchPostsCreator

class InstagramApp : Application() {
    val usersRepo by lazy { FirebaseUsersRepository() }
    val feedPostsRepo by lazy { FirebaseFeedPostsRepository() }
    val notificationsRepo by lazy { FirebaseNotificationsRepository() }
    val authManager by lazy { FirebaseAuthManager() }
    val searchRepo by lazy { FirebaseSearchRepository() }

    override fun onCreate() {
        super.onCreate()
        NotificationsCreator(notificationsRepo, usersRepo, feedPostsRepo)
        SearchPostsCreator(searchRepo)
    }
}