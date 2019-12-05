package kebacoinCasino.entity

import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import java.io.Serializable
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.FetchType
import javax.persistence.ManyToMany
import javax.validation.constraints.*

@Entity
@Table(name = "users")
class User: UserDetails,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    @Pattern(regexp = "[\\p{L}]*", message = "First name has invalid characters!")
    @NotNull(message = "First name is mandatory!")
    @NotBlank(message = "First name is mandatory!")
    @Column(name = "firstName")
    lateinit var firstName: String
    @Pattern(regexp = "[\\p{L}]*", message = "Last name has invalid characters!")
    @NotNull(message = "Last name is mandatory!")
    @NotBlank(message = "Last name is mandatory!")
    @Column(name = "lastName")
    lateinit var lastName: String
    @Size(min = 4, max = 15, message = "Username must be between 4 and 10 characters!")
    @NotNull(message = "Username is mandatory")
    @Column(name = "username")
    private lateinit var username: String
    @Email(message = "Email has invalid characters!")
    @Column(name = "email")
    lateinit var email: String
    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    lateinit var birthDate: Date
    @Column(name = "balance")
    var balance: Int = 0
    @NotNull
    @NotBlank
    @Column(name = "password")
    private lateinit var password: String
    @Column(nullable = false, columnDefinition = "bit default 0")
    private var enabled:Boolean = false
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "USER_AUTHORITY",
        joinColumns=[JoinColumn(name = "USER_ID")],
        inverseJoinColumns = [JoinColumn(name = "AUTHORITY_ID")])
    private lateinit var authorities: List<Authority>

    override fun getPassword()=password

    override fun getUsername() = username

    override fun getAuthorities()= authorities

    override fun isEnabled()=enabled

    override fun isCredentialsNonExpired()=true

    override fun isAccountNonExpired()=true

    override fun isAccountNonLocked()=true

    fun setPassword(password:String){
        this.password=password
    }

    fun setUsername(username:String){
        this.username=username
    }

    override fun toString(): String {
        return "User(id=$id, firstName='$firstName', lastName='$lastName', username='$username', email='$email', birthDate=$birthDate, balance=$balance, password='$password', enabled=$enabled)"
    }

}