package com.metodos.metodos_numericos.metodos;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MetodosService { // todo adicionar os métodos e condições devidas para guia no controller

  private double funcao(double x) {
    return x * x - 4;
  }

  private double derivada(double x) {
    return 2 * x;

  }

  public double sum(double num1, double num2) {
    return num1 + num2;
  } // método de exemplo para teste de endpoint

  public double calcularRaizBissecao(double a, double b, double tolerancia, int maxInteracoes) {

    double fa = funcao(a);
    double fb = funcao(b);

    if (fa * fb >= 0) {
      throw new IllegalArgumentException("O método da bisseção não pode ser aplicado neste intervalo");
    }

    double c = 0;

    for (int i = 0; i < maxInteracoes; i++) {
      c = (a + b) / 2;
      double fc = funcao(c);

      if (Math.abs(fc) < tolerancia) {
        return c;
      }

      if (fa * fc < 0) {
        b = c;
        fb = fc;
      } else {
        a = c;
        fa = fc;
      }
    }
    return c;
  }

  public double calcularRaizSecante(double a, double b, double tolerancia, int maxInteracoes) {

    double fa = funcao(a);
    double fb = funcao(b);

    for (int i = 0; i < maxInteracoes; i++) {

      if (Math.abs(fb) < tolerancia) {
        return b;
      }

      double b2 = b - (fb * (b - a)) / (fb - fa);
      a = b;
      fa = fb;
      b = b2;
      fb = funcao(b);
    }
    return b;
  }

  public double calcularRaizNewtonRapshon(double a, double tolerancia, int maxInteracoes) {

    double b = a;

    for (int i = 0; i < maxInteracoes; i++) {
      double fb = funcao(b);
      double dfb = derivada(b);

      if (Math.abs(dfb) < 1e-10) {
        throw new ArithmeticException("Derivada próxima de zero. O método não pode continuar.");
      }

      double bProximo = b - (fb / dfb);

      if (Math.abs(bProximo - b) < tolerancia) {
        return bProximo;
      }
      b = bProximo;
    }
    return b;
  }
}
