package com.souza.charles.digitalMenu.service.exceptions;
/*
 Tutorial title: Building a Full-Stack Application with Java Spring and React
 Instructor: Fernanda Kipper - kipperDev
 Project adapted by: Charles Fernandes de Souza
 Date: February 08, 2025
 */
public class DuplicateImageException extends RuntimeException {
    public DuplicateImageException(String image) {
        super("The image uri address '" + image + "' is already associated with an existing food.");

    }
}