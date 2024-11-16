# Métodos Numéricos - Cálculo Numérico interativo

Seja bem-vindo ao **Cálculo Numérico Interativo**, este projeto tem como objetivo desenvolver uma plataforma para
auxiliar estudantes no aprendizado de tópicos de métodos numéricos da disciplina **Cálculo Numérico** que é vista em cursos de
**tecnologia** e de **exatas**. Este site permite que os usuários insiram dados e visualizem como os cálculos são realizados para
três tópicos fundamentais de Cálculo Numérico.

## **Objetivo do Projeto**
Este site tem como objetivo:
- Tornar o aprendizado de Cálculo Numérico mais interativo.
- Ajudar estudantes a compreender os passos e a lógica por trás de cada método.
- Fornecer uma ferramenta prática para visualização de cálculos e resultados, além de poderem verificar esses dados graficamente.

## **Funcionalidades**
O site abrange os seguintes tópicos:

### 1. **Erros em Cálculo Numérico**

| **Erro Absoluto** 	| **Erro Relativo** 	| **Erro de Truncamento** 	|
|:---:	|:---:	|:---:	|
| $E_a = x_{\text{aproximado}} - x_{\text{exato}}$ 	| $E_r = \frac{E_a}{\|x_{\text{exato}}\|}$ 	| O erro de truncamento ocorre devido<br>à interrupção de uma série infinita ou<br>aproximações de métodos numéricos. 	|


### 2. **Resolução Numérica de Equações Não Lineares**

| **Método da Bisseção** 	| **Método de Newton-Raphson** 	|                         **Método das Secantes** 	                      |
|:---:	|:---:	|:----------------------------------------------------------------------:|
| $x_m = \frac{a + b}{2}$ 	| $x_{n+1} = x_n - \frac{f(x_n)}{f'(x_n)}$ 	| $x_{n+1} = x_n - f(x_n) \cdot \frac{x_n - x_{n-1}}{f(x_n) - f(x_{n-1})}$ |

- Na qual, a primeira é a fórmula da aproximação do ponto médio:  
  Onde $\(a\)$ e $\(b\)$ são os limites do intervalo, e $\(f(a) \cdot f(b) < 0\)$.


### 3. **Sistemas de Equações Lineares**

| **Eliminação de Gauss** 	| **Método de Jacobi** 	|                         **Método de Gauss-Seidel** 	                      |
|:---:	|:---:	|:----------------------------------------------------------------------:|
| Este método resolve um sistema $\(Ax = b\)$ transformando a matriz $\(A\)$ em uma matriz triangular superior. | $x_i^{(k+1)} = \frac{1}{a_{ii}} ( b_i - \sum_{j \neq i} a_{ij} x_j^{(k)}$ 	| $x_i^{(k+1)} = \frac{1}{a_{ii}} ( b_i - \sum_{j < i} a_{ij} x_j^{(k+1)} - \sum_{j > i} a_{ij} x_j^{(k)}$ |

## **Como Usar**
1. Insira os dados necessários nos campos do formulário correspondente ao método que deseja usar.
2. Clique em **Calcular** para ver o passo a passo da solução.
3. Visualize os resultados, gráficos e análises de erro (quando aplicável).

## **Tecnologias Utilizadas**
- **Frontend:** HTML, CSS, JavaScript.
- **Backend:** Java.
- **Hospedagem:** --.