package br.com.fiap.GerenciadorDeCursos.exceptions;

import lombok.Getter;

@Getter
public class CourseFullException extends Exception {

    private String message;

    public CourseFullException(String message) {
        super(message);
        this.message = message;
    }
}
