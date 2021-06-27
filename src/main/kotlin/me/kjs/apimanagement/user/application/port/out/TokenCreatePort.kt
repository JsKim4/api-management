package me.kjs.apimanagement.user.application.port.out

import me.kjs.apimanagement.user.domain.vo.Token


interface TokenCreatePort {
	fun generateRefreshToken(): Token
	fun generateAccessToken(id: String): Token

}
