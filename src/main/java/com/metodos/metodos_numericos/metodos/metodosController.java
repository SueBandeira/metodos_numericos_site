package com.metodos.metodos_numericos.metodos;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/metodos"})
@AllArgsConstructor
public class metodosController {

    private final MetodosService metodosService;

    @GetMapping("/soma")
    public ResponseEntity<Double> sum(@RequestParam double num1, @RequestParam double num2) { // endpoint de exemplo
        double resultado = metodosService.sum(num1, num2);
        return ResponseEntity.ok(resultado);
    }
}
