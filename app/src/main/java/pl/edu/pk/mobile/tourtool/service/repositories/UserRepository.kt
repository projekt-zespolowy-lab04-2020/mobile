package pl.edu.pk.mobile.tourtool.service.repositories

import java.lang.Exception
import pl.edu.pk.mobile.tourtool.service.model.JWT
import pl.edu.pk.mobile.tourtool.service.model.User

interface UserRepository {
  suspend fun validateCredentials(email: String, password: String): JWT
  suspend fun getUser(token: JWT): User
  suspend fun createUser(user: User)
  suspend fun updateUser(user: User, token: JWT)
}

class WrongCredentialsException(message: String) : Exception(message)
