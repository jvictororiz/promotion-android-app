package br.com.promotion.model.mapper

import br.com.promotion.model.data.UserDTO
import br.com.promotion.model.domain.User


fun User.toUserDTO() = UserDTO(name, email, password)

fun UserDTO.toUser() = User(name, email, password)