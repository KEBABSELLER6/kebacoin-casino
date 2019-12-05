package kebacoinCasino.service

import kebacoinCasino.entity.User
import javax.validation.Valid

interface UserService{
    fun getUsers():List<User>
    fun getUserById(id:Int): User
    fun updateUser(id:Int,@Valid user: User): User
    fun removeUser(id: Int): User
    fun addUser(@Valid user: User): User
    fun getUserByUsername(username:String):User
}