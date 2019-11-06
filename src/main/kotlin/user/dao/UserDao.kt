package user.dao

import user.entity.User

interface UserDao {
    fun getUsers(): List<User>
    fun getUserById(id: Int): User
    fun getUserByUserName(username: String): List<User>
    fun addUser(user: User): User
    fun removeUser(id: Int): User
    fun updateUser(id: Int, user: User): User
}