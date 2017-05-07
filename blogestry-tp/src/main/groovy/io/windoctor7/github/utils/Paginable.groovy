package io.windoctor7.github.utils

import io.windoctor7.github.model.Articulo
import io.windoctor7.github.model.ArticuloDTO

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 05/05/17.
 */
class Paginable {

    List<ArticuloDTO> coleccion

    int size, pagina

    Paginable(List<Articulo> coleccion, int size) {
        assert "coleccion no puede ser null"

        this.coleccion = coleccion
        this.size = size
    }

    List<ArticuloDTO> get(int pagina){
        if(pagina <= 0)
            pagina = 1

        if(pagina > getPaginas().size())
            pagina = getPaginas().size()

        if(getPaginas().isEmpty())
            pagina = 1

        int inicio = (pagina - 1) * size
        def p = coleccion.subList(inicio, Math.min(inicio + size, coleccion.size()))
        this.pagina = pagina
        return p
    }

    int getActual(){
        return pagina
    }

    def getPaginas(){
        String nPaginas = coleccion.size() / 4
        List<Pagina> paginas = []

        if(!nPaginas.isInteger())
            nPaginas = nPaginas.toDouble() + 1

        nPaginas.toDouble().toInteger().times {
            Pagina p = new Pagina(numero: it+1, elementos: size, clase: getActual() == it+1 ? "active" : "")
            paginas.add(p)
        }

        return paginas
    }



}
