package entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    @Column(name = "firstName")
    lateinit var firstName: String
    @Column(name = "lastName")
    lateinit var lastName: String
    @Column(name = "username")
    lateinit var username: String
    @Column(name = "email")
    lateinit var email: String
    @Column(name = "birthDate")
    lateinit var birthDate: Date
    @Column(name = "balance")
    var balance: Int = 0
    @Column(name = "password")
    lateinit var password: String

    override fun toString(): String {
        return "User(id=$id, firstName='$firstName', lastName='$lastName', username='$username', email='$email', birthDate=$birthDate, balance=$balance, password='$password')"
    }

}