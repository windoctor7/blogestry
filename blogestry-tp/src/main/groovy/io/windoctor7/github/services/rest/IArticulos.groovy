package io.windoctor7.github.services.rest

import io.windoctor7.github.model.Articulo
import io.windoctor7.github.model.ArticuloDTO

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 02/05/17.
 */
interface IArticulos {
    ArticuloDTO crear(ArticuloDTO articulo)
    void actualizar(ArticuloDTO articulo)
    ArticuloDTO[] getArticulos()
    ArticuloDTO getArticulo(String id)
    int count()
}