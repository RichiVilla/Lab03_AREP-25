package com.mycompany.microspring.server;

import java.util.concurrent.atomic.AtomicLong;


@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public static String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "Hola " + name;
	}
        
        @GetMapping("/pi")
	public static String pi(@RequestParam(value = "Richi", defaultValue = "World") String name) {
		return Double.toString(Math.PI);
	}
}