package com.calyrsoft.ucbp1.features.profile.domain.model

import com.calyrsoft.ucbp1.features.profile.domain.model.Email
import com.calyrsoft.ucbp1.features.profile.domain.model.Username
import android.util.Patterns

data class ProfileModel(
    val userId: String,
    val username: Username,
    val email: Email,
    val profilePictureUrl: String? = null,
    val summary: String? = null
)

// Value Object para el nombre de usuario
@JvmInline
value class Username(val value: String) {
    init {
        require(value.isNotBlank()) {
            "El nombre de usuario no puede estar vacío"
        }
    }
}

// Value Object para el correo electrónico
@JvmInline
value class Email(val value: String) {
    init {
        require(value.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            "El correo electrónico debe ser una dirección válida"
        }
    }
}