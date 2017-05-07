package io.github.windoctor7.api.entidades

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 01/05/17.
 */
@Document(collection = "categorias")
class Categoria(@Id val id:String,
                val nombre:String,
                val descripcion:String,
                val articulosId:Array<String>) {
}