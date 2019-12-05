package kebacoinCasino.jwt

import io.jsonwebtoken.ExpiredJwtException
import kebacoinCasino.controllerAdvice.RestControllerAdvice
import kebacoinCasino.exception.TokenExpiredException
import java.io.IOException
import javax.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import javax.servlet.ServletException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter : OncePerRequestFilter() {

    @Autowired
    private val messageConverter: MappingJackson2HttpMessageConverter? = null

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @Autowired
    @Qualifier("CustomAuthenticationManager")
    private lateinit var userDetailsService: UserDetailsService

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestHeader = request.getHeader("Authorization")
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            val authToken = requestHeader.substring(7)
            logger.warn("JWT: $authToken")
            try {
                val username = jwtUtil.getUsernameFromToken(authToken)
                val userDetails = userDetailsService.loadUserByUsername(username)
                logger.warn("UserDetails: $userDetails")
                if (jwtUtil.isTokenValid(authToken, userDetails)) {
                    val authentication = TokenBasedAuthentication(authToken, userDetails)
                    authentication.isAuthenticated = true
                    SecurityContextHolder.getContext().authentication = authentication
                    filterChain.doFilter(request, response)
                }
//            } catch (e: ExpiredJwtException) {
//                throw TokenExpiredException()
            } catch (e: Exception) {
                logger.warn(e.message)
                filterChain.doFilter(request, response)
            }
        } else {
            logger.warn("Couldn't find bearer string!")
            filterChain.doFilter(request, response)
        }
    }

}
