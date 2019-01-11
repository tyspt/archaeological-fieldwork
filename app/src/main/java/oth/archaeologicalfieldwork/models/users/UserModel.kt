package oth.archaeologicalfieldwork.models.users

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    var id: Long = 0,
    var email: String = "",
    var password: String = "" //TODO encrypt passwords in JSON Store!!!!
) : Parcelable