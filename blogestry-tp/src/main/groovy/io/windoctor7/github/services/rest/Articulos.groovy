package io.windoctor7.github.services.rest

import io.windoctor7.github.model.Articulo
import io.windoctor7.github.model.ArticuloDTO
import org.apache.tapestry5.ioc.annotations.Inject
import org.apache.tapestry5.ioc.annotations.Symbol
import org.springframework.web.client.RestTemplate

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 02/05/17.
 */
class Articulos implements IArticulos{

    @Inject @Symbol("api")
    String API

    @Override
    ArticuloDTO crear(ArticuloDTO articulo) {
        def url = API + "articulos"
        fill(articulo)
        RestTemplate template = new RestTemplate();
        def response = template.postForEntity(url,articulo,ArticuloDTO.class)
        return response.body
    }

    @Override
    void actualizar(ArticuloDTO articulo) {
        def url = API + "articulos/${articulo.id}"
        RestTemplate template = new RestTemplate();
        template.put(url, articulo)
    }

    def fill(ArticuloDTO articulo){
        articulo.autor = "Ascari Romo"
        articulo.visitas = 0
        articulo.fecha = new Date()
    }


    ArticuloDTO[] getArticulos() {
        def url = API + "articulos?fields=titulo,fecha,categoria,resumen"
        RestTemplate template = new RestTemplate();
        def entity = template.getForEntity(url, ArticuloDTO[].class)
        return entity.body
    }

    @Override
    ArticuloDTO getArticulo(String id) {
        def url = API + "articulos/${id}"
        RestTemplate template = new RestTemplate();
        def entity = template.getForEntity(url, ArticuloDTO.class)
        return entity.body
    }

    @Override
    int count() {
        def url = API + "articulos/count"
        return new RestTemplate().getForObject(url, Integer.class)
    }
}
