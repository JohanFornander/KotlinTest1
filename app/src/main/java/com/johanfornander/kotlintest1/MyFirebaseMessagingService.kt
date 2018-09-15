package com.johanfornander.kotlintest1

import android.app.*
import android.util.Log

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.os.Build
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color


private var TAG = "firebase"


class MyFirebaseMessagingService : FirebaseMessagingService() {

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder : Notification.Builder
    private val channelId = "com.johanfornander.kotlintest1"
    private var description = "Test notification"


    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        Log.d(TAG, "From: " + remoteMessage!!.from)

        if (remoteMessage.data.size > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }


        if (remoteMessage.notification != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body!!)

            description = remoteMessage.notification!!.body!!
            initNotification()
        }

    }


    private fun initNotification() {

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this,LauncherActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
                    .setContentTitle("title")
                    .setContentText("text")
                    .setSmallIcon(R.drawable.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.mipmap.ic_launcher))
                    .setContentIntent(pendingIntent)

        } else {

            builder = Notification.Builder(this)
                    .setContentTitle("title")
                    .setContentText("text")
                    .setSmallIcon(R.drawable.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.mipmap.ic_launcher))
                    .setContentIntent(pendingIntent)

        }

        notificationManager.notify(1234, builder.build())

    }


}
