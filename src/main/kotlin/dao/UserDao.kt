package dao

import entity.User

interface UserDao {
    fun getUsers():List<User>
    fun getUserById(id:Int):User
    fun getUserByUserName(username:String):List<User>
    fun addUser(user:User):User
    fun removeUser(user: User):User
}