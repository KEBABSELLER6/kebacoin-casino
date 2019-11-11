package user.controller

import user.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import user.service.UserService

@RestController
class UserController {

    @Autowired
    lateinit var userService: UserService

    companion object {
        const val basePath: String = "/user"
    }

    @GetMapping("$basePath")
    fun getUsers(): List<User> {
        return userService.getUsers()
    }

    @GetMapping("$basePath/{id}")
    fun getUser(@PathVariable id: Int): User {
        return userService.getUserById(id)
    }

    @PostMapping("$basePath")
    fun addUser(@RequestBody user: User): User {
        return userService.addUser(user)
    }

    @PutMapping("$basePath/{id}")
    fun updateUser(@RequestBody user: User, @PathVariable id: Int): User {
        return userService.updateUser(id,user)
    }

    @DeleteMapping("$basePath/{id}")
    fun deleteUser(@PathVariable id: Int): User {
        return userService.removeUser(id)
    }

}