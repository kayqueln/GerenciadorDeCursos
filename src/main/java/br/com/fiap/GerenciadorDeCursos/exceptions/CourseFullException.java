package br.com.fiap.GerenciadorDeCursos.exceptions;

public class CourseFullException extends Exception {

    private String message;

    public CourseFullException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
