package kebacoinCasino.controller

import kebacoinCasino.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import kebacoinCasino.service.UserService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import java.util.*
import kebacoinCasino.exception.UserNotFoundException
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping



@RestController
class UserController {

    @Autowired
    lateinit var userService: UserService

    companion object {
        const val basePath: String = "/user"
    }

    @GetMapping("$basePath")
    fun getUsers(): List<UserDto> {
        return userService.getUsers().map { UserDto(it) }
    }

    @GetMapping("$basePath/{id}")
    fun getUser(@PathVariable id: Int): UserDto {
        return UserDto(userService.getUserById(id))
    }

    @PostMapping("$basePath")
    fun addUser(@RequestBody user: RegUser): UserDtoWithBalance {
        return UserDtoWithBalance(userService.addUser(user.convertToUser()))
    }

    @PutMapping("$basePath/{id}")
    fun updateUser(@RequestBody user: User, @PathVariable id: Int): UserDto {
        return UserDto(userService.updateUser(id, user))
    }

    @DeleteMapping("$basePath/{id}")
    fun deleteUser(@PathVariable id: Int): UserDto {
        return UserDto(userService.removeUser(id))
    }

    @GetMapping("$basePath/profile")
    fun getAuthenticatedUser(@AuthenticationPrincipal user: User?): UserDtoWithBalance {
        if (user == null) {
            throw UserNotFoundException()
        }
        return UserDtoWithBalance(user)
    }

    @PostMapping("$basePath/{id}/update")
    fun updateBalance(@RequestBody balanceUpdate: BalanceUpdate, @PathVariable id:Int):UserDtoWithBalance{
        return UserDtoWithBalance(userService.updateBalance(id,balanceUpdate.balance))
    }

    open inner class UserDto(user:User){
        val id: Int=user.id
        val firstName: String=user.firstName
        val lastName: String=user.lastName
        val username: String=user.username
        val email: String=user.email
        val birthDate: Date=user.birthDate
    }

    inner class UserDtoWithBalance(user: User) : UserDto(user) {
        val balance:Int=user.balance
    }

    class BalanceUpdate(var balance:Int=-1)

    class RegUser(private val firstName: String,
                  private val lastName: String,
                  private val username: String,
                  private val email: String,
                  private val birthDate: Date,
                  private val password:String){
        fun convertToUser():User{
            return User().apply {
                this.firstName=this@RegUser.firstName
                this.lastName=this@RegUser.lastName
                this.email=this@RegUser.email
                this.username=this@RegUser.username
                this.birthDate=this@RegUser.birthDate
                this.password=this@RegUser.password
            }
        }
    }
}