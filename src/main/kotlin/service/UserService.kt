package service

import entity.User

interface UserService{
    fun getUsers():List<User>
    fun getUserByUsername(username:String):User
    fun getUserById(id:Int):User
    fun updateUser(id:Int,user: User):User
    fun removeUser(id: Int):User
    fun addUser(user: User):User
}