package com.crystal.tigers.s1.s1ws

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = { DataSourceAutoConfiguration::class.java, JpaRepositoriesAutoConfiguration::class.java})
class S1wsApplication

fun main(args: Array<String>) {
    runApplication<S1wsApplication>(*args)
}
