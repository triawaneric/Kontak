package space.erictriawan.kontak.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Kontak(
    @SerializedName("id") @Expose var id: String? = null,
    @SerializedName("firstName") @Expose var fName: String? = null,
    @SerializedName("lastName") @Expose var lName: String? = null,
    @SerializedName("email") @Expose var email: String? = null,
    @SerializedName("phone") @Expose var phone: String? = null,
)
