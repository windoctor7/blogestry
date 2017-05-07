package io.github.windoctor7.api

//import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@SpringBootApplication
class BlogestryRestApplication

fun main(args: Array<String>) {
    SpringApplication.run(BlogestryRestApplication::class.java, *args)



}
