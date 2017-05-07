package io.windoctor7.github.pages.admin

import io.windoctor7.github.model.ArticuloDTO
import io.windoctor7.github.services.rest.IArticulos
import io.windoctor7.github.services.rest.IBlog
import org.apache.shiro.authz.annotation.RequiresAuthentication
import org.apache.tapestry5.annotations.PageActivationContext
import org.apache.tapestry5.beaneditor.Validate
import org.apache.tapestry5.ioc.annotations.Inject
import org.apache.tapestry5.services.SelectModelFactory

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 02/05/17.
 */
@RequiresAuthentication
class Index {

    @Inject IArticulos service
    @Inject IBlog blog
    @Inject SelectModelFactory modelFactory
    @PageActivationContext String id

    ArticuloDTO articulo

    @Validate("required") String categoria


    void onPrepare(){
        articulo = new ArticuloDTO()

        if(id?.trim()) {
            println "id = ${id}"
            articulo = service.getArticulo(id)
            categoria = articulo.categoria
        }
    }

    void onSuccess(){
        articulo.categoria = categoria
        if(!articulo.id)
            service.crear(articulo)
        else
            service.actualizar(articulo)
    }

    def getCategorias(){
        println "se llama a getCategorias"
        blog.categorias.collect {c -> c.nombre}
    }
}
