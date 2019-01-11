package oth.archaeologicalfieldwork.models.sessions

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import oth.archaeologicalfieldwork.models.sites.SiteModel
import oth.archaeologicalfieldwork.models.users.UserModel

@Parcelize
data class LoginSessionModel(
    var user: UserModel,
    var sites: SiteModel
) : Parcelable