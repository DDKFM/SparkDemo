package de.ddkfm.sparkdemo

import spark.ModelAndView
import spark.Request
import spark.Spark
import spark.template.velocity.VelocityTemplateEngine

fun main(args : Array<String>) {
    Spark.path("/calculator") {
        Spark.get("") {_,resp -> resp.redirect("/calculator/add")}
        Spark.path("/add") {
            getAndPost("add", "Addieren") { a, b -> a + b}
        }
        Spark.path("/sub") {
            getAndPost("sub", "Subtrahieren") {a, b -> a - b}
        }
        Spark.path("/div") {
            getAndPost("div", "Addieren") {a, b -> a / b}
        }
        Spark.path("/mul") {
            getAndPost("mul", "Multiplizieren") {a, b -> a * b}
        }
    }
}

fun getAndPost(calculationPath : String, method : String, operation : (Double, Double) -> Double) {
    Spark.get("") { req, resp ->
        val model = mapOf(
            "calculationPath" to calculationPath,
            "calculationMethod" to method
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
        val c = operation(a, b)
        val model = mapOf(
            "calculationPath" to calculationPath,
            "calculationMethod" to method,
            "result" to c
        )
        render(model, "templates/index.vm")
    }
}