package dao

import entity.User
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Transactional
@Repository
class UserDaoImpl : UserDao {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun getUsers(): List<User> = entityManager.createQuery("select u from User u", User::class.java).resultList

    override fun getUserById(id: Int): User = entityManager.find(User::class.java, id)


    override fun getUserByUserName(username: String): List<User> =
        entityManager.createQuery("select u from User u where u.username = :username", User::class.java)
            .setParameter("username", username).resultList

    override fun addUser(user: User): User = user.let {
        entityManager.persist(it)
        return it
    }

    override fun removeUser(user: User): User = user.let {
        entityManager.remove(it)
        return it
    }

}