package configuration

import blackjack.BlackjackTable
import blackjack.BlackjackTableImpl
import dao.UserDao
import dao.UserDaoImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor
import roulette.RouletteTable
import roulette.RouletteTableImpl
import slotmachine.SlotMachine
import slotmachine.SlotMachineImpl

@Configuration
class CasinoConfiguration {

    @Bean
    fun initRouletteTables(): List<RouletteTable> = mutableListOf<RouletteTableImpl>()

    @Bean
    fun initSlotMachines(): List<SlotMachine> = mutableListOf<SlotMachineImpl>()

    @Bean
    fun initBlackjackTables(): List<BlackjackTable> = mutableListOf<BlackjackTableImpl>()

    @Bean
    fun initDataSource(): EmbeddedDatabase = EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.HSQL)
        .addScript("create_db.sql")
        .addScript("test_data_insert.sql")
        .build()

    @Bean
    fun initEntityManagerFactory(): LocalContainerEntityManagerFactoryBean =
        LocalContainerEntityManagerFactoryBean().apply {
            dataSource = initDataSource()
            setPackagesToScan("entity")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
        }

    @Bean
    fun initJPATransactionManager(): PlatformTransactionManager =
        JpaTransactionManager(initEntityManagerFactory().`object`)

    @Bean
    fun initMethodValidationPostProcessor(): MethodValidationPostProcessor = MethodValidationPostProcessor()

    @Bean
    fun initTestUserDao(): UserDao = UserDaoImpl()
}