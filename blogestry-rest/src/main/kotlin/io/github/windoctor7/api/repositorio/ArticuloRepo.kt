package io.github.windoctor7.api.repositorio

import io.github.windoctor7.api.entidades.Articulo

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository


/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 30/04/17.
 */
@Repository
interface ArticuloRepo : MongoRepository<Articulo,String>{
    fun findFirstByOrderByBlogIdDesc():Articulo
}

