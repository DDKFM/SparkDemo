package de.ddkfm.sparkdemo;

import spark.ModelAndView;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ExampleWithPathJava {
    public static void main(String[] args) {
        Spark.path("/calculator", () -> {
            Spark.get("", (req, resp) -> {
                resp.redirect("/calculator/add");
                return "";
            });
            Spark.path("/add",() -> {
                getAndPost("add", "Addieren", (a, b) -> a + b);
            });
            Spark.path("/div", () -> {
                getAndPost("div", "Dividieren", (a, b) -> a / b);
            });
            Spark.path("/sub", () -> {
                getAndPost("sub", "Subtrahieren", (a, b) -> a - b);
            });
            Spark.path("/mul", () -> {
                getAndPost("mul", "Multiplizieren", (a, b) -> a * b);
            });
        });
    }
    private static String render(Map<String, Object> model, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    @FunctionalInterface
    private interface Operation {
        Double handle(Double a, Double b);
    }

    private static void getAndPost(String calculationPath, String method, Operation operation) {
        Spark.get("", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("calculationPath", calculationPath);
            model.put("calculationMethod", method);
            return render(model, "templates/index.vm");
        });
        Spark.post("", (req, resp) -> {
            try {
                double a = Double.parseDouble(req.queryParams("a"));
                double b = Double.parseDouble(req.queryParams("b"));
                double c = operation.handle(a, b);
                Map<String, Object> model = new HashMap<>();
                model.put("calculationPath", calculationPath);
                model.put("calculationMethod", method);
                model.put("result", c);
                return render(model, "templates/index.vm");
            } catch (NumberFormatException e) {
                resp.redirect("/calculator");
            }
            return "";
        });
    }
}