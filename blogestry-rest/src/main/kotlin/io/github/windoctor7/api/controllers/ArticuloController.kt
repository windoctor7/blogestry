package io.github.windoctor7.api.controllers

import io.github.windoctor7.api.entidades.Articulo
import io.github.windoctor7.api.repositorio.ArticuloRepo
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 30/04/17.
 */
@RestController
class ArticuloController(val repository: ArticuloRepo, val mongo:MongoOperations) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/articulos")
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody articulo: Articulo) = repository.insert(articulo)

    @GetMapping("/articulos")
    fun findAll(@RequestParam(defaultValue = "0") limit:Int,
                @RequestParam(defaultValue = "*") fields:String) : List<Articulo>{

        val query:Query = Query()
        query.with(Sort(Sort.Direction.DESC,"fecha"))

        if(limit > 0)
            query.limit(limit)

        // se filtra por los campos que se indican
        if(!"*".equals(fields)) {
            query.fields().include("_id")
            fields.split(",").forEach {
                query.fields().include(it)
            }
        }

        return mongo.find(query,Articulo::class.java,"articulos")
    }

    @GetMapping("/articulos/count")
    fun count() = repository.count()

    // TODO Con findById de MongoRepository no obtiene nada.
    @GetMapping("/articulos/{id}")
    fun findById(@PathVariable id:String):Articulo? {
        val query = Query.query(Criteria.where("_id").`is`(id))
        return mongo.findOne(query,Articulo::class.java,"articulos")
    }


    @PutMapping("/articulos/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun addVisit(@RequestBody articulo: Articulo) = mongo.save(articulo)


    @GetMapping("/articulos/ultimo")
    fun getUltimo() = repository.findFirstByOrderByBlogIdDesc()

}