package io.github.windoctor7.api.entidades

import org.springframework.data.mongodb.core.mapping.Document

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 03/05/17.
 */
@Document(collection = "sitio")
class Sitio{
    val nombre:String = ""
    val autor:String = ""
    val github:String = ""
    val twitter:String = ""
    val youtube:String = ""
    val perfil:String = ""
    val ubicacion:String = ""
    val disqus:String = ""
    val articulosXPagina:Int = 5
}