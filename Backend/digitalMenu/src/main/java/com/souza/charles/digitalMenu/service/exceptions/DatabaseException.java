package com.souza.charles.digitalMenu.service.exceptions;
/*
 Tutorial title: Building a Full-Stack Application with Java Spring and React
 Instructor: Fernanda Kipper - kipperDev
 Project adapted by: Charles Fernandes de Souza
 Date: February 06, 2025
 */
public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super("Database error detected: " + message + ". Please verify database constraints and configurations.");
    }
}