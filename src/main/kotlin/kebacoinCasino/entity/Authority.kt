package kebacoinCasino.entity

import org.springframework.security.core.GrantedAuthority
import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "authorities")
class Authority : Serializable, GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    lateinit var name: String
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
    lateinit var users: List<User>

    override fun getAuthority(): String {
        return this.name
    }
}