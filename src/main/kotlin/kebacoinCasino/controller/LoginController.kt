package kebacoinCasino.controller

import kebacoinCasino.jwt.JwtAuthenticationRequest
import kebacoinCasino.jwt.JwtAuthenticationToken
import kebacoinCasino.jwt.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import kebacoinCasino.entity.User
import org.springframework.security.authentication.AuthenticationManager

@RestController
class LoginController {

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @PostMapping("/login")
    fun login(@RequestBody authenticationRequest: JwtAuthenticationRequest): JwtAuthenticationToken {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.username,
                authenticationRequest.password
            )
        )
        SecurityContextHolder.getContext().authentication = authentication
        val user = authentication.principal as User

        val token = jwtUtil.generateToken(user)
        return JwtAuthenticationToken(token = token)
    }
}