package com.phan.instagram.screens.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.phan.instagram.R
import com.phan.instagram.models.Notification
import com.phan.instagram.models.NotificationType
import com.phan.instagram.screens.common.SimpleCallback
import com.phan.instagram.screens.common.loadImageOrHide
import com.phan.instagram.screens.common.loadUserPhoto
import com.phan.instagram.screens.common.setCaptionText
import kotlinx.android.synthetic.main.notification_item.view.*

class NotificationsAdapter : RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    private var notifications = listOf<Notification>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.notification_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = notifications[position]
        with(holder.view) {
            user_photo.loadUserPhoto(notification.photo)
            val notificationText = when (notification.type) {
                NotificationType.Comment -> context.getString(R.string.commented, notification.commentText)
                NotificationType.Like -> context.getString(R.string.liked_your_post)
                NotificationType.Follow -> context.getString(R.string.started_following_you)
            }
            notification_text.setCaptionText(notification.username, notificationText,
                    notification.timestampDate())
            post_image.loadImageOrHide(notification.postImage)
        }
    }

    override fun getItemCount(): Int = notifications.size

    fun updateNotifications(newNotifications: List<Notification>) {
        val diffResult = DiffUtil.calculateDiff(SimpleCallback(notifications, newNotifications) { it.id })
        this.notifications = newNotifications
        diffResult.dispatchUpdatesTo(this)
    }
}