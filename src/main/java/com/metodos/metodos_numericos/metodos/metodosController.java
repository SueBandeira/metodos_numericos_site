package com.metodos.metodos_numericos.metodos;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/eliminacao-gaussiana")
    public ResponseEntity<double[]> encontrarSolucaoEG(
            @RequestParam(defaultValue = "3,4;7,8") String a,
            @RequestParam(defaultValue = "3;4") String b
    ) {
        try {
            double[][] matrizA = converterParaMatriz(a);
            double[] matrizB = converterParaVetor(b);

            if (matrizA.length != matrizB.length) {
                throw new IllegalArgumentException("O número de linhas da matriz A deve ser igual ao tamanho do vetor B.");
            }

            double[] raiz = metodosService.calcularEliminacaoGaussiana(matrizA, matrizB);
            return ResponseEntity.ok(raiz);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao processar a solicitação.");
        }
    }

    private double[][] converterParaMatriz(String matrizStr) {
        try {
            String[] linhas = matrizStr.split(";");
            int numLinhas = linhas.length;
            int numColunas = linhas[0].split(",").length;

            double[][] matriz = new double[numLinhas][numColunas];
            for (int i = 0; i < numLinhas; i++) {
                String[] valores = linhas[i].split(",");
                if (valores.length != numColunas) {
                    throw new IllegalArgumentException("Todas as linhas da matriz devem ter o mesmo número de colunas.");
                }
                for (int j = 0; j < numColunas; j++) {
                    matriz[i][j] = Double.parseDouble(valores[j]);
                }
            }
            return matriz;
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato inválido para matriz A. Use formato: \"3,4;7,8\"");
        }
    }

    private double[] converterParaVetor(String vetorStr) {
        try {
            String[] valores = vetorStr.split(";");
            double[] vetor = new double[valores.length];
            for (int i = 0; i < valores.length; i++) {
                vetor[i] = Double.parseDouble(valores[i]);
            }
            return vetor;
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato inválido para vetor B. Use formato: \"3;4\"");
        }
    }

}
