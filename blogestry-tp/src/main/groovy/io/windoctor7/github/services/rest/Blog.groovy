package io.windoctor7.github.services.rest

import groovy.text.SimpleTemplateEngine
import io.windoctor7.github.model.Articulo
import io.windoctor7.github.model.Categoria
import io.windoctor7.github.model.Sitio
import org.apache.tapestry5.ioc.annotations.Inject
import org.apache.tapestry5.ioc.annotations.Symbol
import org.springframework.web.client.RestTemplate

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 01/05/17.
 */
class Blog implements IBlog {

    @Inject @Symbol("api")
    String API

    @Override
    Categoria[] getCategorias() {
        def url = API + "categorias"
        RestTemplate template = new RestTemplate();
        def entity = template.getForEntity(url, Categoria[].class)
        return entity.body
    }

    @Override
    Categoria getCategoria(String nombre) {
        def url = API + "categorias/" + nombre
        RestTemplate template = new RestTemplate();
        def entity = template.getForEntity(url, Categoria.class)
        return entity.body
    }

    @Override
    Sitio getConfiguracion() {
        def url = API + "configuracion"
        RestTemplate template = new RestTemplate();
        def entity = template.getForEntity(url, Sitio.class)
        return entity.body
    }
}
