package de.ddkfm.sparkdemo;

import spark.Spark;

public class ExampleWithPost {
    public static void main(String[] args) {
        Spark.get("/calculator", (req, resp) -> {
            return "<html><body>" +
                    "<h1>Ich bin ein einfacher Taschenrechner</h1>" +
                    "<form action=\"/calculator/add\" method=\"POST\">" +
                    "<label>a</label><input type=\"text\" name=\"a\"/><br/>" +
                    "<label>b</label><input type=\"text\" name=\"b\"/><br/>" +
                    "<button type=\"submit\">Addieren</button>" +
                    "</form>" +
                    "</body></html>";
        });
        Spark.post("/calculator/add", (req, resp) -> {
            try {
                double a = Double.parseDouble(req.queryParams("a"));
                double b = Double.parseDouble(req.queryParams("b"));
                double c = a + b;
                return "<html><body>" +
                        "<h1>Ich bin ein einfacher Taschenrechner</h1>" +
                        "<form action=\"/calculator/add\" method=\"POST\">" +
                        "<label>a</label><input type=\"text\" name=\"a\"/><br/>" +
                        "<label>b</label><input type=\"text\" name=\"b\"/><br/>" +
                        "<button type=\"submit\">Addieren</button><br/>" +
                        "</form>" +
                        "<h3> Dein Ergebnis lautet " + c + "</h3>" +
                        "</body></html>";
            } catch (NumberFormatException e) {
                resp.redirect("/calculator");
            }
            return "";
        });
    }
}