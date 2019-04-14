package de.ddkfm.sparkdemo

import spark.ModelAndView
import spark.Spark
import spark.template.velocity.VelocityTemplateEngine

fun main(args : Array<String>) {
    Spark.path("/calculator") {
        Spark.get("") {_,resp -> resp.redirect("/calculator/add")}
        Spark.path("/add") {
            Spark.get("") { req, resp ->
                val model = mapOf(
                    "calculationPath" to "add",
                    "calculationMethod" to "Addieren"
                )
                render(model, "templates/index.vm")
            }
            Spark.post("") { req, resp ->
                val a = req.queryParams("a")?.toDoubleOrNull()
                val b = req.queryParams("b")?.toDoubleOrNull()
                if(a == null || b == null) {
                    resp.redirect("/calculator")
                    return@post ""
                }
                val c = a + b
                val model = mapOf(
                    "calculationPath" to "add",
                    "calculationMethod" to "Addieren",
                    "result" to c
                )
                render(model, "templates/index.vm")
            }
        }
        Spark.path("/div") {
            Spark.get("") { req, resp ->
                val model = mapOf(
                    "calculationPath" to "div",
                    "calculationMethod" to "Dividieren"
                )
                render(model, "templates/index.vm")
            }
            Spark.post("") { req, resp ->
                val a = req.queryParams("a")?.toDoubleOrNull()
                val b = req.queryParams("b")?.toDoubleOrNull()
                if(a == null || b == null) {
                    resp.redirect("/calculator")
                    return@post ""
                }
                val c = a / b
                val model = mapOf(
                    "calculationPath" to "div",
                    "calculationMethod" to "Dividieren",
                    "result" to c
                )
                render(model, "templates/index.vm")
            }
        }
        Spark.path("/sub") {
            Spark.get("") { req, resp ->
                val model = mapOf(
                    "calculationPath" to "sub",
                    "calculationMethod" to "Subtrahieren"
                )
                render(model, "templates/index.vm")
            }
            Spark.post("") { req, resp ->
                val a = req.queryParams("a")?.toDoubleOrNull()
                val b = req.queryParams("b")?.toDoubleOrNull()
                if(a == null || b == null) {
                    resp.redirect("/calculator")
                    return@post ""
                }
                val c = a - b
                val model = mapOf(
                    "calculationPath" to "sub",
                    "calculationMethod" to "Subtrahieren",
                    "result" to c
                )
                render(model, "templates/index.vm")
            }
        }
        Spark.path("/mul") {
            Spark.get("") { req, resp ->
                val model = mapOf(
                    "calculationPath" to "mul",
                    "calculationMethod" to "Multiplizieren"
                )
                render(model, "templates/index.vm")
            }
            Spark.post("") { req, resp ->
                val a = req.queryParams("a")?.toDoubleOrNull()
                val b = req.queryParams("b")?.toDoubleOrNull()
                if(a == null || b == null) {
                    resp.redirect("/calculator")
                    return@post ""
                }
                val c = a * b
                val model = mapOf(
                    "calculationPath" to "mul",
                    "calculationMethod" to "Multiplizieren",
                    "result" to c
                )
                render(model, "templates/index.vm")
            }
        }
    }
}