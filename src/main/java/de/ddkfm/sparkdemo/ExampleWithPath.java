package de.ddkfm.sparkdemo;

import spark.ModelAndView;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class ExampleWithPath {
    public static void main(String[] args) {
        Spark.path("/calculator", () -> {
            Spark.get("", (req, resp) -> {
                resp.redirect("/calculator/add");
                return "";
            });
            Spark.path("/add",() -> {
                Spark.get("", (req, resp) -> {
                    Map<String, Object> model = new HashMap<>();
                    model.put("calculationPath", "add");
                    model.put("calculationMethod", "Addieren");
                    return render(model, "templates/index.vm");
                });
                Spark.post("", (req, resp) -> {
                    try {
                        double a = Double.parseDouble(req.queryParams("a"));
                        double b = Double.parseDouble(req.queryParams("b"));
                        double c = a + b;
                        Map<String, Object> model = new HashMap<>();
                        model.put("calculationPath", "add");
                        model.put("calculationMethod", "Addieren");
                        model.put("result", c);
                        return render(model, "templates/index.vm");
                    } catch (NumberFormatException e) {
                        resp.redirect("/calculator");
                    }
                    return "";
                });
            });
            Spark.path("/div", () -> {
                Spark.get("", (req, resp) -> {
                    Map<String, Object> model = new HashMap<>();
                    model.put("calculationPath", "div");
                    model.put("calculationMethod", "Dividieren");
                    return render(model, "templates/index.vm");
                });
                Spark.post("", (req, resp) -> {
                    try {
                        double a = Double.parseDouble(req.queryParams("a"));
                        double b = Double.parseDouble(req.queryParams("b"));
                        double c = a / b;
                        Map<String, Object> model = new HashMap<>();
                        model.put("calculationPath", "div");
                        model.put("calculationMethod", "Dividieren");
                        model.put("result", c);
                        return render(model, "templates/index.vm");
                    } catch (NumberFormatException e) {
                        resp.redirect("/calculator");
                    }
                    return "";
                });
            });
            Spark.path("/sub", () -> {
                Spark.get("", (req, resp) -> {
                    Map<String, Object> model = new HashMap<>();
                    model.put("calculationPath", "sub");
                    model.put("calculationMethod", "Subtrahieren");
                    return render(model, "templates/index.vm");
                });
                Spark.post("", (req, resp) -> {
                    try {
                        double a = Double.parseDouble(req.queryParams("a"));
                        double b = Double.parseDouble(req.queryParams("b"));
                        double c = a - b;
                        Map<String, Object> model = new HashMap<>();
                        model.put("calculationPath", "sub");
                        model.put("calculationMethod", "Subtrahieren");
                        model.put("result", c);
                        return render(model, "templates/index.vm");
                    } catch (NumberFormatException e) {
                        resp.redirect("/calculator");
                    }
                    return "";
                });
            });
            Spark.path("/mul", () -> {
                Spark.get("", (req, resp) -> {
                    Map<String, Object> model = new HashMap<>();
                    model.put("calculationPath", "mul");
                    model.put("calculationMethod", "Multiplizieren");
                    return render(model, "templates/index.vm");
                });
                Spark.post("", (req, resp) -> {
                    try {
                        double a = Double.parseDouble(req.queryParams("a"));
                        double b = Double.parseDouble(req.queryParams("b"));
                        double c = a * b;
                        Map<String, Object> model = new HashMap<>();
                        model.put("calculationPath", "mul");
                        model.put("calculationMethod", "Multiplizieren");
                        model.put("result", c);
                        return render(model, "templates/index.vm");
                    } catch (NumberFormatException e) {
                        resp.redirect("/calculator");
                    }
                    return "";
                });
            });
        });
    }
    private static String render(Map<String, Object> model, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}