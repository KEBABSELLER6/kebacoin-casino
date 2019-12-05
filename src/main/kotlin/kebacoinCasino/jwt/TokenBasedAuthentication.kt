package kebacoinCasino.jwt


import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails

class TokenBasedAuthentication(private val token: String, private val userDetails: UserDetails) :
    AbstractAuthenticationToken(userDetails.authorities) {

    override fun getCredentials(): Any {
        return token
    }

    override fun getPrincipal(): Any {
        return userDetails
    }

}
