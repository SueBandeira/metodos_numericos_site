package com.metodos.metodos_numericos.metodos;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

  @GetMapping("/bisseccao")
  public ResponseEntity<Double> encontrarRaizBissecao(
      @RequestParam double a,
      @RequestParam double b,
      @RequestParam(defaultValue = "0.000001") double tolerancia,
      @RequestParam(defaultValue = "100") int maxInteracoes) {

    try {
      double raiz = metodosService.calcularRaizBissecao(a, b, tolerancia, maxInteracoes);
      return ResponseEntity.status(HttpStatus.OK).body(raiz);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  @GetMapping("/secante")
  public ResponseEntity<Double> encontrarRaizSecante(
      @RequestParam double a,
      @RequestParam double b,
      @RequestParam(defaultValue = "0.000001") double tolerancia,
      @RequestParam(defaultValue = "100") int maxInteracoes) {

    try {
      double raiz = metodosService.calcularRaizSecante(a, b, tolerancia, maxInteracoes);
      return ResponseEntity.status(HttpStatus.OK).body(raiz);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  @GetMapping("/newton-rapshon")
  public ResponseEntity<Double> encontrarRaizNewtonRapshon(
      @RequestParam double a,
      @RequestParam(defaultValue = "0.000001") double tolerancia,
      @RequestParam(defaultValue = "100") int maxInteracoes) {

    try {
      double raiz = metodosService.calcularRaizNewtonRapshon(a, tolerancia, maxInteracoes);
      return ResponseEntity.status(HttpStatus.OK).body(raiz);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

}
