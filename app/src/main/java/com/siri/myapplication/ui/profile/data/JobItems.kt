package com.siri.myapplication.ui.profile.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobItems(
    val serviceid: String,
    val serviceName: String
) : Parcelable