package io.windoctor7.github.pages

import io.windoctor7.github.model.Articulo
import io.windoctor7.github.services.rest.IArticulos
import org.apache.tapestry5.annotations.Import
import org.apache.tapestry5.annotations.SetupRender
import org.apache.tapestry5.ioc.annotations.Inject

import java.util.concurrent.atomic.AtomicInteger

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 01/05/17.
 */
@Import(stylesheet = "context:css/prism.css",
        library = "context:js/prism.js"
)
class Post {

    AtomicInteger atomicInteger = new AtomicInteger(1)

    @Inject IArticulos service;

    Articulo articulo

    String id

    void onActivate(String id){
        this.id = id
    }

    Object onActivate(){
        if(!id)
            return Index
    }


    void setupRender(){
        articulo =  service.getArticulo(id)
    }

    boolean isGitHub(){
        assert articulo != null
        boolean s = articulo.github?.trim()
        return s
    }

}
