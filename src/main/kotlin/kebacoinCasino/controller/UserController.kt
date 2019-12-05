package kebacoinCasino.controller

import kebacoinCasino.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import kebacoinCasino.service.UserService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import java.util.*

@RestController
class UserController {

    @Autowired
    lateinit var userService: UserService

    companion object {
        const val basePath: String = "user"
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
    fun addUser(@RequestBody user: User): UserDto {
        return UserDto(userService.addUser(user))
    }

    @PutMapping("$basePath/{id}")
    fun updateUser(@RequestBody user: User, @PathVariable id: Int): UserDto {
        return UserDto(userService.updateUser(id, user))
    }

    @DeleteMapping("$basePath/{id}")
    fun deleteUser(@PathVariable id: Int): UserDto {
        return UserDto(userService.removeUser(id))
    }

    inner class UserDto(user:User){
        val id: Int=user.id
        val firstName: String=user.firstName
        val lastName: String=user.lastName
        val username: String=user.username
        val email: String=user.email
        val birthDate: Date=user.birthDate
    }
}