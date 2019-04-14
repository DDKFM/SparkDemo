package de.ddkfm.sparkdemo;

import spark.Spark;

public class SimpleExample {
    public static void main(String[] args) {
        Spark.get("/calculator", (req, resp) -> {
            return "<html><body><h1>Ich bin ein einfacher Taschenrechner</body></html>";
        });
    }
}