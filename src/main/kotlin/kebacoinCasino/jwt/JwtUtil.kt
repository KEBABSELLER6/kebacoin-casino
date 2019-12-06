package kebacoinCasino.jwt

import org.springframework.security.core.userdetails.UserDetails

import org.springframework.security.authentication.BadCredentialsException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import kebacoinCasino.entity.User
import java.util.*

@Component
class JwtUtil {
    private val EXPIRESINSECONDS: Long = 1000

    private val ID = "id"
    @Value("\${user.jwt.secret}")
    private val secret: String? = null

    fun generateToken(user: User): String {
        val claims = Jwts.claims().setSubject(user.username)
        //claims.expiration = generateExpirationDate()
        claims[ID] = user.id
        claims.forEach { println(it) }
        return Jwts.builder()
            .setClaims(claims)
            .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret)
            .compact()
    }

    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body
    }

    fun getUsernameFromToken(token: String): String {
        val username: String?
        val claims = this.getAllClaimsFromToken(token)
        username = claims.subject
        if (username == null) {
            throw BadCredentialsException("The given credentials are not valid!")
        }
        return username
    }

    fun getIdFromToken(token: String): Int {
        val claims = getAllClaimsFromToken(token)
        try {
            return claims[ID] as Int
        } catch (e: Exception) {
            throw BadCredentialsException("The given credentials are not valid!")
        }

    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val user = userDetails as User
        val idFromToken = getIdFromToken(token)
        return idFromToken == user.id
    }

    private fun generateExpirationDate(): Date {
        return Date(Date().time + EXPIRESINSECONDS * 1000)
    }

}
