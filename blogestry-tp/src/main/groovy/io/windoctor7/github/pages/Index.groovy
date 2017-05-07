package io.windoctor7.github.pages

import io.windoctor7.github.model.Articulo
import io.windoctor7.github.model.ArticuloDTO
import io.windoctor7.github.services.rest.IArticulos
import io.windoctor7.github.utils.Pagina
import io.windoctor7.github.utils.Paginable
import org.apache.tapestry5.PersistenceConstants
import org.apache.tapestry5.annotations.DiscardAfter
import org.apache.tapestry5.annotations.Persist
import org.apache.tapestry5.ioc.annotations.Inject

import java.util.concurrent.atomic.AtomicInteger

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 01/05/17.
 */
class Index {

    AtomicInteger atomicInteger = new AtomicInteger(1)

    @Inject IArticulos articulos;

    //@Persist
    List<ArticuloDTO> listaArticulos;

    @Persist
    Paginable paginable

    ArticuloDTO articulo
    String tag

    Pagina pagina

    void onActivate(){
        println("onActivate() -> ${atomicInteger.getAndIncrement()}" )
        //if(paginable == null) {
            println "se invoca de nuevo getArticulos"
            paginable = new Paginable(Arrays.asList(articulos.getArticulos()), 4)
            listaArticulos = paginable.get(1)

        //}
    }


    def getPaginas(){
        paginable.paginas
    }

    def getPaginaActual(){
        paginable.actual
    }

    void onGet(int pagina){
        listaArticulos = paginable.get(pagina)
    }

    void onSiguiente(){
        listaArticulos = paginable.get(getPaginaActual()+1)
    }

    void onAnterior(){
        listaArticulos = paginable.get(getPaginaActual()-1)
    }


}
