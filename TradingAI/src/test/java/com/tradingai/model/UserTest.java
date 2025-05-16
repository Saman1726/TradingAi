package com.tradingai.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void testUserConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String username = "testuser";
        String password = "password123";
        String email = "testuser@example.com";
        String firstName = "John";
        String lastName = "Doe";
        String phone = "1234567890";
        String street = "123 Main St";
        String city = "New York";
        String state = "NY";
        String zip = "10001";

        // Act
        User user = new User(id, username, password, email, firstName, lastName, phone, street, city, state, zip);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(email, user.getEmail());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(phone, user.getPhone());
        assertEquals(street, user.getStreet());
        assertEquals(city, user.getCity());
        assertEquals(state, user.getState());
        assertEquals(zip, user.getZip());
    }

    @Test
    void testUserSetters() {
        // Arrange
        User user = new User();

        // Act
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setEmail("testuser@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPhone("1234567890");
        user.setStreet("123 Main St");
        user.setCity("New York");
        user.setState("NY");
        user.setZip("10001");

        // Assert
        assertEquals(1L, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("testuser@example.com", user.getEmail());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("1234567890", user.getPhone());
        assertEquals("123 Main St", user.getStreet());
        assertEquals("New York", user.getCity());
        assertEquals("NY", user.getState());
        assertEquals("10001", user.getZip());
    }
}