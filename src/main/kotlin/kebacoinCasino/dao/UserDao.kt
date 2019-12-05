package kebacoinCasino.dao

import kebacoinCasino.entity.User

interface UserDao {
    fun getUsers(): List<User>
    fun getUserById(id: Int): User
    fun getUserByUsername(username: String): List<User>
    fun addUser(user: User): User
    fun removeUser(id: Int): User
    fun updateUser(id: Int, user: User): User
}