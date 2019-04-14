package de.ddkfm.sparkdemo

import spark.Spark

fun main(args : Array<String>) {
    Spark.get("/calculator") { req, resp ->
        return@get "<html><body><h1>Ich bin ein einfacher Taschenrechner</body></html>"
    }
}