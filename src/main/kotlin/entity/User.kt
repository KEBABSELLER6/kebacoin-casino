package entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name="users")
class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var  id:Int=0
    @Column(name="firstName")
    private lateinit var  firstName:String
    @Column(name="lastName")
    private lateinit var  lastName:String
    @Column(name="username")
    private lateinit var  username:String
    @Column(name="email")
    private lateinit var  email:String
    @Column(name="birthDate")
    private lateinit var  birthDate:Date
    @Column(name="balance")
    private var  balance:Int=0
    @Column(name="password")
    private lateinit var password:String

    override fun toString(): String {
        return "User(id=$id, firstName='$firstName', lastName='$lastName', username='$username', email='$email', birthDate=$birthDate, balance=$balance, password='$password')"
    }

}