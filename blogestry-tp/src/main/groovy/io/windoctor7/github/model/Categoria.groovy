package io.windoctor7.github.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import io.windoctor7.github.services.rest.IBlog
import org.apache.tapestry5.ValueEncoder
import org.apache.tapestry5.beaneditor.Validate
import org.apache.tapestry5.ioc.annotations.Inject

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 01/05/17.
 */
@EqualsAndHashCode
@ToString
class Categoria implements Serializable{
    @Validate("required")
    String nombre
    String descripcion
    String[] articulosId
}

class CategoriaDTO implements ValueEncoder<Categoria>{

    IBlog blog

    CategoriaDTO(IBlog blog) {
        this.blog = blog
    }

    @Override
    String toClient(Categoria categoria) {
        return categoria.nombre
    }

    @Override
    Categoria toValue(String s) {
        def categoria = blog.getCategoria(s)
        return categoria
    }
}