package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import src.main.java.com.example.App;

class AppTest {

    private final App app = new App();

    @Test
    void shouldGreetName() {
        assertEquals("Hello, Nandhi!", app.greet("Nandhi"));
    }

    @Test
    void shouldHandleBlankName() {
        assertEquals("Hello, Guest!", app.greet(" "));
    }

    @Test
    void shouldHandleNullName() {
        assertEquals("Hello, Guest!", app.greet(null));
    }

    @Test
    void shouldAddNumbers() {
        assertEquals(30, app.add(10, 20));
    }
}
