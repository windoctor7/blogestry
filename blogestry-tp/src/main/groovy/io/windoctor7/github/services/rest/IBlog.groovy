package io.windoctor7.github.services.rest

import io.windoctor7.github.model.Articulo
import io.windoctor7.github.model.Categoria
import io.windoctor7.github.model.Sitio

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 01/05/17.
 */
interface IBlog {
    Categoria[] getCategorias()
    Categoria getCategoria(String nombre)
    Sitio getConfiguracion()
}