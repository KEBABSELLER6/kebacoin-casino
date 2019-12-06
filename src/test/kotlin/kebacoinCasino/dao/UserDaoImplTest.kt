package kebacoinCasino.dao

import kebacoinCasino.entity.User
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest

@RunWith(JUnit4::class)
@SpringBootTest
class UserDaoImplTest{

    var userDao:UserDao=Mockito.mock(UserDao::class.java)

    @Before
    fun init(){
        Mockito.`when`(userDao.getUserById(0)).thenReturn(User().apply {
            username="test"
            firstName="test"
            password="test"
            lastName="test"
            email="test@gmail.com"
        })

        Mockito.`when`(userDao.getUserByUsername("test")).thenReturn(listOf(User().apply {
            username="test2"
            firstName="test"
            password="test"
            lastName="test"
            email="test@gmail.com"
        }))

        Mockito.`when`(userDao.getUsers()).thenReturn(listOf(User().apply {
            username="test2"
            firstName="test"
            password="test"
            lastName="test"
            email="test@gmail.com"
        },User().apply {
            username="test"
            firstName="test"
            password="test"
            lastName="test"
            email="test@gmail.com"
        }))

        Mockito.`when`(userDao.removeUser(1)).thenReturn(User().apply {
            username="test2"
            firstName="test"
            password="test"
            lastName="test"
            email="test@gmail.com"
        })
    }

    @Test
    fun testUser(){
        assertNotEquals("asder",userDao.getUserById(0).username)
        assertEquals("test",userDao.getUserById(0).username)
    }

    @Test
    fun testByUsername(){
        assertEquals("test2",userDao.getUserByUsername("test")[0].username)
        assertEquals(1,userDao.getUserByUsername("test").size)
        assertNotEquals("asder",userDao.getUserByUsername("asder"))
    }

    @Test
    fun testUsers(){
        assertEquals(2,userDao.getUsers().size)
        assertEquals("test",userDao.getUsers()[1].username)
        assertNotEquals("test",userDao.getUsers()[0].username)
    }

    @Test
    fun deleteUsers(){
        assertEquals("test2",userDao.removeUser(1).username)
    }

}