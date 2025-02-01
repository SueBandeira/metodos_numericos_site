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

/*
⌈============================⌉
        DADOS DE ENTRADA:
—————————————————————————————
    Matriz A = [1,3;4,9]
    Matriz b = [3;4]
—————————————————————————————
    Matriz expandida: Matriz 1
⌈============================⌉
        1ª Iteração
⌊============================⌋

    L2 ⇐ L2 - L1 × (a21/a11)

    Matriz 2

    m1:
    1 3 | 3
    4 9 | 4
    m2:
    1 3  |  3
    0 -3 | -8
    s:
    -5.0000
    2.6667
 */

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

    public double[] calcularEliminacaoGaussiana(double[][] a, double[] b) {
        int n = a.length;

        // Criando a matriz aumentada [A|B]
        double[][] matrixAumentada = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(a[i], 0, matrixAumentada[i], 0, n);
            matrixAumentada[i][n] = b[i]; // Adiciona a coluna de termos independentes
        }

        // Transformação para a forma triangular superior
        for (int k = 0; k < n; k++) {
            // Pivoteamento parcial
            int maxRow = k;
            for (int i = k + 1; i < n; i++) {
                if (Math.abs(matrixAumentada[i][k]) > Math.abs(matrixAumentada[maxRow][k])) {
                    maxRow = i;
                }
            }
            // Troca de linhas se necessário
            double[] temp = matrixAumentada[k];
            matrixAumentada[k] = matrixAumentada[maxRow];
            matrixAumentada[maxRow] = temp;

            // Verifica se o sistema tem solução única
            if (matrixAumentada[k][k] == 0) {
                throw new IllegalArgumentException("O sistema não tem solução única.");
            }

            // Eliminação
            for (int i = k + 1; i < n; i++) {
                double fator = matrixAumentada[i][k] / matrixAumentada[k][k];
                for (int j = k; j <= n; j++) { // Vai até n para incluir a coluna de termos independentes
                    matrixAumentada[i][j] -= fator * matrixAumentada[k][j];
                }
            }
        }

        // Resolução por substituição regressiva
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            x[i] = matrixAumentada[i][n];
            for (int j = i + 1; j < n; j++) {
                x[i] -= matrixAumentada[i][j] * x[j];
            }
            x[i] /= matrixAumentada[i][i];
        }

        return x;
    }

}
