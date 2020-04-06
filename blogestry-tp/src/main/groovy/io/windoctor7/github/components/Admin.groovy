package io.windoctor7.github.components

import io.windoctor7.github.model.Sitio
import io.windoctor7.github.services.rest.IBlog
import org.apache.tapestry5.annotations.Import
import org.apache.tapestry5.ioc.annotations.Inject

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 01/05/17.
 */
@Import(stylesheet = ["main.css",
        "context:css/editor/editor.css","context:css/tokenfield.css"],
        module = ["init"])
class Admin {

    @Inject
    IBlog blog

    Sitio getDatos(){
        return blog.configuracion
    }
}
