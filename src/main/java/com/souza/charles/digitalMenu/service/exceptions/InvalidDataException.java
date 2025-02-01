package com.souza.charles.digitalMenu.service.exceptions;
/*
 Tutorial title: Building a Full-Stack Application with Java Spring and React
 Instructor: Fernanda Kipper - kipperDev
 Project adapted by: Charles Fernandes de Souza
 Date: January 31, 2025
 */

public class InvalidDataException extends RuntimeException {
    public InvalidDataException() {
        super("Data integrity violation. Check the provided data.");
    }
}