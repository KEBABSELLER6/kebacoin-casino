package kebacoinCasino.service

import kebacoinCasino.dao.UserDao
import kebacoinCasino.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import javax.transaction.Transactional
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.beans.factory.annotation.Qualifier
import kebacoinCasino.exception.UsernameNotUniqueException


@Transactional
@Service("CustomAuthenticationManager")
@Qualifier("CustomAuthenticationManager")
class UserServiceImpl(
    @Autowired
    private val userDao: UserDao
) : UserService, UserDetailsService {
    override fun getUserByUsername(username: String): User {
       val users=userDao.getUserByUsername(username)
        return if(users.size==1){
            users[0]
        }else throw UsernameNotUniqueException()
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val users = userDao.getUserByUsername(username)
        return when {
            users.isEmpty() -> throw UsernameNotFoundException("User not found.")
            users.size == 1 -> users[0]
            else -> throw UsernameNotUniqueException()
        }
    }

    override fun addUser(user: User): User = userDao.addUser(user)

    override fun updateUser(id: Int, user: User): User = userDao.updateUser(id, user)

    override fun getUsers(): List<User> = userDao.getUsers()

    override fun getUserById(id: Int): User = userDao.getUserById(id)

    override fun removeUser(id: Int): User = userDao.removeUser(id)

}