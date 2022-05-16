package com.phan.instagram.screens.search

import android.util.Log
import androidx.lifecycle.Observer
import com.phan.instagram.common.BaseEventListener
import com.phan.instagram.common.Event
import com.phan.instagram.common.EventBus
import com.phan.instagram.data.SearchRepository
import com.phan.instagram.models.SearchPost

class SearchPostsCreator(searchRepo: SearchRepository) : BaseEventListener() {
    init {
        EventBus.events.observe(this, Observer {
            it?.let { event ->
                when (event) {
                    is Event.CreateFeedPost -> {
                        val searchPost = with(event.post) {
                            SearchPost(
                                    image = image,
                                    caption = caption,
                                    postId = id)
                        }
                        searchRepo.createPost(searchPost).addOnFailureListener {
                            Log.d(TAG, "Failed to create search post for event: $event", it)
                        }
                    }
                    else -> {
                    }
                }
            }
        })
    }

    companion object {
        const val TAG = "SearchPostsCreator"
    }
}