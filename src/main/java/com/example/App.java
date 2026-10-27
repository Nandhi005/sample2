package com.example;

public class App {

    public String greet(String name) {
        if (name == null || name.isBlank()) {
            return "Hello, Guest!";
        }
        return "Hello, " + name.trim() + "!";
    }

    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        App app = new App();

        String name = args.length > 0 ? args[0] : "DevOps";

        System.out.println(app.greet(name));
        System.out.println("Addition result: " + app.add(10, 20));
    }
}
