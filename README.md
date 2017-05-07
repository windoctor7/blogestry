# Blogestry

![Image](https://github.com/windoctor7/windoctor7.github.io/raw/master/static/img/blogestry-retina2.png)

Blogestry es una aplicación web de blogging, sencilla y simple pero bonita. 

Está construida con las siguientes herramientas:

1. Spring Boot usando Kotlin como lenguaje
1. MongoDB como base de datos
1. Tapestry para el front-end usando Groovy como lenguaje.

El proyecto consta de 2 módulos,

1. El **back-end** es un pequeño API REST en Spring Boot y Kotlin que persiste la información en una base de datos MongoDB
1. El **front-end** se encuentra en Tapestry y Groovy.

![Image](https://github.com/windoctor7/windoctor7.github.io/raw/master/static/img/blogestry_diagrama.png)


Algunas características son:

* Sencillo administrador de publicaciones para publicar nuevas entradas o editar existentes, las publicaciones del blog se escriben en [Markdown](http://commonmark.org/help/) lo que lo hace realmente cómodo.
* El resaltado de sitnaxis de código se hace mediante [PrismJS ](http://prismjs.com)
* Los comentarios no son administrados por el sitio, en su lugar se utiliza [disqus](disqus.com)

Esta idea nacio con el único fin de aprender un poco más de estas herramientas.

# ¿Como ejecutar el proyecto?
1. Crea una base de datos en mongo llamada "blogestry" sin autenticación.
1. Situate en la carpeta blogestry-rest y ejecuta el comando

``./gradlew bootRun`` o ``gradlew.bat bootRun`` si estás en windows

Esto lanzará el api por el puerto 8081. En la consola deberás ver algo como esto,

> 2017-05-07 12:24:36.478  INFO 29596 --- [           main] i.g.w.api.BlogestryRestApplicationKt     : Started BlogestryRestApplicationKt in 5.971 seconds (JVM running for 6.563)

3. Situate en la carpeta blogestry-tp y ejecuta el comando

``./gradlew jettyRun`` o ``gradlew.bat jettyRun`` si estás en windows

Esto lanzará la aplicación web por el puerto 8080

4. Accede por el navegador a la dirección [http://localhost:8080/blogestry-tp/](http://localhost:8080/blogestry-tp/)

![Image](https://github.com/windoctor7/windoctor7.github.io/raw/master/static/img/blogestry-retina1.png)
![Image](https://github.com/windoctor7/windoctor7.github.io/raw/master/static/img/blogestry-retina3.png)

