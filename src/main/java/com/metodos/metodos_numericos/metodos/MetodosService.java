package com.metodos.metodos_numericos.metodos;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MetodosService { // todo adicionar os métodos e condições devidas para guia no controller

    public double sum(double num1, double num2) {
        return num1 + num2;
    } // método de exemplo para teste de endpoint
}
