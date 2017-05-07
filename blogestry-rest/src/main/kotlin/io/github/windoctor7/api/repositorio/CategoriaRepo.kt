package io.github.windoctor7.api.repositorio

import io.github.windoctor7.api.entidades.Categoria
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 01/05/17.
 */
@Repository
interface CategoriaRepo : MongoRepository<Categoria,String>{
    fun findByNombre(nombre:String):Categoria
}