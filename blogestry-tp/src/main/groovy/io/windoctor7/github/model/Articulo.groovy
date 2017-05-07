package io.windoctor7.github.model

import groovy.transform.ToString
import org.apache.tapestry5.beaneditor.Validate
import java.time.LocalDateTime

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 01/05/17.
 */
@ToString(includeNames = true)
class Articulo {
    String id
    String autor
    String categoria
    String github
    String[] tagsId
    Date fecha
    int visitas
    @Validate("required") String markdown
    @Validate("required") String titulo
    @Validate("required") String contenido
    @Validate("required") String resumen


}

class ArticuloDTO extends Articulo{
    @Validate("required") String tags

    String getTags(){
        tagsId?.join(",")
    }

    void setTags(String tags){
        this.tagsId = tags.replaceAll("\\s", "").split(",")
    }

}