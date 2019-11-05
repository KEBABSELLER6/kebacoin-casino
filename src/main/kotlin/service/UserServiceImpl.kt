package service

import dao.UserDao
import entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Transactional
@Service
class UserServiceImpl(
    @Autowired
    private val userDao: UserDao
) : UserService {
    override fun addUser(user: User): User = userDao.addUser(user)

    override fun updateUser(id:Int,user: User): User = userDao.updateUser(id,user)

    override fun getUsers(): List<User> = userDao.getUsers()

    override fun getUserByUsername(username: String): User = userDao.getUserByUserName(username)[0]

    override fun getUserById(id: Int): User = userDao.getUserById(id)

    override fun removeUser(id: Int): User = userDao.removeUser(id)

}