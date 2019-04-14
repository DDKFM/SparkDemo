package de.ddkfm.sparkdemo

import spark.Spark

fun main(args : Array<String>) {
    Spark.get("/calculator") { req, resp ->
        "<html><body>" +
                "<h1>Ich bin ein einfacher Taschenrechner</h1>" +
                "<form action=\"/calculator/add\" method=\"POST\">" +
                "<label>a</label><input type=\"text\" name=\"a\"/><br/>" +
                "<label>b</label><input type=\"text\" name=\"b\"/><br/>" +
                "<button type=\"submit\">Addieren</button>" +
                "</form>" +
        "</body></html>"
    }

    Spark.post("/calculator/add") {req, resp ->
        val a = req.queryParams("a")?.toDoubleOrNull()
        val b = req.queryParams("b")?.toDoubleOrNull()
        if(a == null || b == null) {
            resp.redirect("/calculator")
            return@post ""
        }
        val c = a + b
        return@post "<html><body>" +
                "<h1>Ich bin ein einfacher Taschenrechner</h1>" +
                "<form action=\"/calculator/add\" method=\"POST\">" +
                "<label>a</label><input type=\"text\" name=\"a\"/><br/>" +
                "<label>b</label><input type=\"text\" name=\"b\"/><br/>" +
                "<button type=\"submit\">Addieren</button><br/>" +
                "</form>" +
                "<h3> Dein Ergebnis lautet $c</h3>" +
                "</body></html>"
    }
}