package com.calyrsoft.ucbp1.features.profile.data.repository

import com.calyrsoft.ucbp1.features.profile.domain.model.ProfileModel
import com.calyrsoft.ucbp1.features.profile.domain.model.Username
import com.calyrsoft.ucbp1.features.profile.domain.model.Email
import com.calyrsoft.ucbp1.features.profile.domain.repository.IProfileRepository

class ProfileRepository : IProfileRepository {
    override suspend fun fetchData(): Result<ProfileModel> {
        return Result.success(
            ProfileModel(
                userId = "homero_simpson_id",
                username = Username("homer_simpson"),
                email = Email("homero.simpson@springfieldmail.com"),
                profilePictureUrl = "https://www.viaempresa.cat/uploads/s1/43/99/69/homer.jpg", // Agrega el valor para la URL de la imagen
                summary = "Ciudadano de Springfield y dedicado inspector de seguridad en la Planta Nuclear."
            )
        )
    }
}