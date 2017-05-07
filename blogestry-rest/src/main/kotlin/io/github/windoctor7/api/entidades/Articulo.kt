package io.github.windoctor7.api.entidades

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 30/04/17.
 */
@Document(collection = "articulos")
data class Articulo @PersistenceConstructor constructor(
        @Id val id:String?,
        val blogId:Int?,
        val titulo:String?,
        val resumen:String?,
        val autor:String?,
        val fecha:Date? = Date(),
        val contenido:String?,
        val markdown:String?,
        val github:String?,
        val visitas:Int?,
        val categoria:String?,
        val tagsId:Array<String>?
        ) {
}