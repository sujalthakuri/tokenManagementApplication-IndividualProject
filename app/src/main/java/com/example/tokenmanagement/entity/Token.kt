package com.example.tokenmanagement.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Token(
    @PrimaryKey
    var _id : String? = "",
    var code : String? = null,
    var phone_number : String? = null,
    var token_number : String? = null
)