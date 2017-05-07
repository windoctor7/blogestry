package io.github.windoctor7.api.repositorio

import io.github.windoctor7.api.entidades.Sitio
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 03/05/17.
 */
interface SitioRepo : MongoRepository<Sitio,String>{
    fun findFirstByOrderByNombre():Sitio
}