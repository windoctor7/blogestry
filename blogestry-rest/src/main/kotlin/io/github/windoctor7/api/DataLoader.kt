package io.github.windoctor7.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 03/05/17.
 */
@Configuration
class DataLoader{
    @Bean
    fun repositoryPopulator(): Jackson2RepositoryPopulatorFactoryBean {
        val sourceData: Resource = ClassPathResource("sitio.json");
        val factory:Jackson2RepositoryPopulatorFactoryBean = Jackson2RepositoryPopulatorFactoryBean()
        factory.setResources(arrayOf(sourceData))
        return factory;
    }
}

