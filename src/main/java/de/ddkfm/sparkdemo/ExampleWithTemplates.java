package de.ddkfm.sparkdemo;

import spark.ModelAndView;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class ExampleWithTemplates {
    public static void main(String[] args) {
        Spark.get("/calculator",(req, resp) -> {
            resp.redirect("/calculator/add");
            return "";
        });
        Spark.get("/calculator/add", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("calculationPath", "add");
            model.put("calculationMethod", "Addieren");
            return render(model, "templates/index.vm");
        });
        Spark.post("/calculator/add", (req, resp) -> {
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
    }
    private static String render(Map<String, Object> model, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}