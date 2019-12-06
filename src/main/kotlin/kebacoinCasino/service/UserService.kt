package kebacoinCasino.service

import kebacoinCasino.entity.User
import org.springframework.security.access.prepost.PreAuthorize
import javax.validation.Valid

interface UserService{
    fun getUsers():List<User>
    fun getUserById(id:Int): User
    @PreAuthorize("#id == authentication.principal.id")
    fun updateUser(id:Int,@Valid user: User): User
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun removeUser(id: Int): User
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun addUser(@Valid user: User): User
    fun getUserByUsername(username:String):User
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun updateBalance(id: Int,balance:Int):User
}