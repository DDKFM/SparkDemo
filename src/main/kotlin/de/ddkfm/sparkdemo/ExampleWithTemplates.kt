package de.ddkfm.sparkdemo

import spark.ModelAndView
import spark.Spark
import spark.template.velocity.VelocityTemplateEngine

fun main(args : Array<String>) {
    Spark.get("/calculator") {_,resp -> resp.redirect("/calculator/add")}
    Spark.get("/calculator/add") {req, resp ->
        val model = mapOf(
            "calculationPath" to "add",
            "calculationMethod" to "Addieren"
        )
        render(model, "templates/index.vm")
    }
    Spark.post("/calculator/add") {req, resp ->
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

fun render(model : Map<String, Any>, templatePath : String) : String {
    return VelocityTemplateEngine().render(ModelAndView(model, templatePath))
}