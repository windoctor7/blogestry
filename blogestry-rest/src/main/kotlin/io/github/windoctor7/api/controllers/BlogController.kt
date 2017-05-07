package io.github.windoctor7.api.controllers

import io.github.windoctor7.api.repositorio.ArticuloRepo
import io.github.windoctor7.api.repositorio.CategoriaRepo
import io.github.windoctor7.api.repositorio.SitioRepo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 01/05/17.
 */
@RestController
class BlogController(val categorias:CategoriaRepo,
                     val sitio:SitioRepo) {

    @GetMapping("/categorias")
    fun categorias() = categorias.findAll()

    @GetMapping("/categorias/{nombre}")
    fun nombreCategoria(@PathVariable nombre:String) = categorias.findByNombre(nombre)

    @GetMapping("/configuracion")
    fun configuracion() = sitio.findFirstByOrderByNombre()
}