package plr;

public class Matriz extends DataSet {
    private int filas;
    private int columnas;
    private double[][] elementos;
    private int grado;

    // Constructor
    public Matriz(int filas, int columnas, int grado) {
        this.grado = grado;
        this.filas = filas;
        this.columnas = columnas;
        this.elementos = new double[filas][columnas];
    }

    // Método para obtener el número de filas
    public int obtenerFilas() {
        return filas;
    }

    // Método para obtener el número de columnas
    public int obtenerColumnas() {
        return columnas;
    }

    public double[][] getMatriz() {
        return elementos;
    }

    // Método para establecer un valor en una posición específica de la matriz
    public void establecerValor(int fila, int columna, double d) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            elementos[fila][columna] = d;
        } else {
            System.out.println("Error: Índices fuera de rango");
        }
    }

    // Método para obtener el valor en una posición específica de la matriz
    public double obtenerValor(int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            return elementos[fila][columna];
        } else {
            System.out.println("Error: Índices fuera de rango");
            return -1; // Valor por defecto en caso de error
        }
    }

    // Método para imprimir la matriz
    public void imprimirMatriz() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(elementos[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Método para sumar dos matrices (ejemplo de operación)
    public Matriz sumar(Matriz otraMatriz) {
        if (this.filas == otraMatriz.obtenerFilas() && this.columnas == otraMatriz.obtenerColumnas()) {
            Matriz resultado = new Matriz(this.columnas, this.filas, this.grado);
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    resultado.establecerValor(i, j, this.obtenerValor(i, j) + otraMatriz.obtenerValor(i, j));
                }
            }
            return resultado;
        } else {
            System.out.println("Error: No se pueden sumar matrices de tamaños diferentes");
            return null;
        }
    }

    // Llenar los datos de la matriz
    public void llenarMatriz(int colum_data) {
        for (int i = 0; i < grado + 1; i++) {
            for (int j = 0; j < grado + 1; j++) {
                int potencia = i + j;
                double valor = sumatoria_pow(colum_data, potencia);
                establecerValor(j, i, valor);
                potencia += 1;
            }
        }
    }

    public void llenarMatrizY(int grado) {
        int x = 0;
        for (int i = 0; i < grado + 1; i++) {
            double valor = multiplicar_sum(0, 1, i, 1);
            establecerValor(i, 0, valor);
            // System.out.println(valor);
            // establecerValor(i, 0, x);

        }
        multiplicar_sum(0, 1, x, 1);
    }

    public double[][] TranspuestaMatriz() {
        int filas = this.filas;
        int columnas = this.columnas;

        double[][] transpuesta = new double[columnas][filas];

        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                transpuesta[i][j] = elementos[j][i];
            }
        }

        return transpuesta;
    }

    public double[][] ProductoMatriz(double[][] matriz1, double[][] matriz2) {
        int filasMatriz1 = matriz1.length;
        int columnasMatriz1 = matriz1[0].length;
        int filasMatriz2 = matriz2.length;
        int columnasMatriz2 = matriz2[0].length;

        if (columnasMatriz1 != filasMatriz2) {
            // Las matrices no son compatibles para la multiplicación
            return null; // O podrías lanzar una excepción u tomar alguna otra acción
        }

        double[][] producto = new double[filasMatriz1][columnasMatriz2];

        for (int x = 0; x < filasMatriz1; x++) {
            for (int y = 0; y < columnasMatriz2; y++) {
                double n = 0;
                for (int c = 0; c < columnasMatriz1; c++) {
                    n += matriz1[x][c] * matriz2[c][y];
                }
                producto[x][y] = n;
            }
        }

        return producto;
    }

    public double[][] obtenerMatrizAdjunta(double[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        double[][] adjunta = new double[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                double cofactor = obtenerCofactor(matriz, i, j);
                adjunta[i][j] = cofactor * Math.pow(-1, i + j);
            }
        }

        return adjunta;
    }

    private double obtenerCofactor(double[][] matriz, int fila, int columna) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        double[][] submatriz = new double[filas - 1][columnas - 1];
        int subfila = 0;
        int subcolumna = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i != fila && j != columna) {
                    submatriz[subfila][subcolumna++] = matriz[i][j];

                    if (subcolumna == filas - 1) {
                        subfila++;
                        subcolumna = 0;
                    }
                }
            }
        }

        return calcularDeterminante(submatriz);
    }

    // Calcular determinante
    public double calcularDeterminante(double[][] matriz) {
        if (matriz.length != matriz[0].length) {
            throw new IllegalArgumentException("La matriz debe ser cuadrada");
        }

        return determinanteRecursivo(matriz);
    }

    // Proceso determinante
    private double determinanteRecursivo(double[][] matriz) {
        int n = matriz.length;

        if (n == 1) {
            return matriz[0][0];
        }

        if (n == 2) {
            return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        }

        int determinante = 0;

        for (int j = 0; j < n; j++) {
            determinante += Math.pow(-1, 0 + j) * matriz[0][j] * determinanteRecursivo(submatriz(matriz, 0, j));
        }

        return determinante;
    }

    // Metodo para calcularla submatriz
    private double[][] submatriz(double[][] matriz, int fila, int columna) {
        int n = matriz.length;
        double[][] submatriz = new double[n - 1][n - 1];
        int k = 0, l = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != fila && j != columna) {
                    submatriz[k][l++] = matriz[i][j];

                    if (l == n - 1) {
                        l = 0;
                        k++;
                    }
                }
            }
        }

        return submatriz;
    }

    public double[][] calcularInversa(double[][] matriz, double determinante) {

        System.out.println(determinante);
        if (determinante == 0) {
            return null;
        }

        double[][] adjunta = obtenerMatrizAdjunta(TranspuestaMatriz());
        int filas = adjunta.length;
        int columnas = adjunta[0].length;

        // System.out.println(filas + "," + columnas);

        double[][] inversa = new double[filas][columnas];

        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                inversa[i][j] = adjunta[i][j] / determinante;
            }
        }

        return inversa;
    }

    public double calcularDeterminantePorColumna(double[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        if (filas != columnas) {
            throw new IllegalArgumentException("La matriz no es cuadrada.");
        }

        if (filas == 1) {
            return matriz[0][0];
        }

        double determinante = 0;
        for (int i = 0; i < columnas; i++) {
            determinante += matriz[0][i] * cofactor(matriz, 0, i);
        }

        return determinante;
    }

    public double cofactor(double[][] matriz, int fila, int columna) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        double[][] submatriz = new double[filas - 1][columnas - 1];
        int subfila = 0;
        int subcolumna = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i != fila && j != columna) {
                    submatriz[subfila][subcolumna++] = matriz[i][j];

                    if (subcolumna == filas - 1) {
                        subfila++;
                        subcolumna = 0;
                    }
                }
            }
        }

        return Math.pow(-1, fila + columna) * calcularDeterminantePorColumna(submatriz);
    }

    public double determinante(double[][] matriz) {
        double det;
        if (matriz.length == 2) {
            det = (matriz[0][0] * matriz[1][1]) - (matriz[1][0] * matriz[0][1]);
            return det;
        }
        double suma = 0;
        for (int i = 0; i < matriz.length; i++) {
            double[][] nm = new double[matriz.length - 1][matriz.length - 1];
            for (int j = 0; j < matriz.length; j++) {
                if (j != i) {
                    for (int k = 1; k < matriz.length; k++) {
                        int indice = -1;
                        if (j < i)
                            indice = j;
                        else if (j > i)
                            indice = j - 1;
                        nm[indice][k - 1] = matriz[j][k];
                    }
                }
            }
            if (i % 2 == 0)
                suma += matriz[i][0] * determinante(nm);
            else
                suma -= matriz[i][0] * determinante(nm);
        }
        return suma;
    }

    public double[][] matrizInversa(double[][] matriz) {
        double det = 1 / determinante(matriz);

        double[][] nmatriz = matrizAdjunta(matriz);
        multiplicarMatriz(det, nmatriz);
        return nmatriz;
    }

    public void multiplicarMatriz(double n, double[][] matriz) {
        for (int i = 0; i < matriz.length; i++)
            for (int j = 0; j < matriz.length; j++)
                matriz[i][j] *= n;
    }

    public double[][] matrizAdjunta(double[][] matriz) {
        return matrizTranspuesta(matrizCofactores(matriz));
    }

    public double[][] matrizCofactores(double[][] matriz) {
        double[][] nm = new double[matriz.length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                double[][] det = new double[matriz.length - 1][matriz.length - 1];
                double detValor;
                for (int k = 0; k < matriz.length; k++) {
                    if (k != i) {
                        for (int l = 0; l < matriz.length; l++) {
                            if (l != j) {
                                int indice1 = k < i ? k : k - 1;
                                int indice2 = l < j ? l : l - 1;
                                det[indice1][indice2] = matriz[k][l];
                            }
                        }
                    }
                }
                detValor = determinante(det);
                nm[i][j] = detValor * Math.pow(-1, i + j + 2);
            }
        }
        return nm;
    }

    public double[][] matrizTranspuesta(double[][] matriz) {
        double[][] nuevam = new double[matriz[0].length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++)
                nuevam[i][j] = matriz[j][i];
        }
        return nuevam;
    }

}

// public static double calcularDeterminantePorColumna(double[][] matriz) {
// int filas = matriz.length;
// int columnas = matriz[0].length;

// if (filas != columnas) {
// throw new IllegalArgumentException("La matriz no es cuadrada.");
// }

// if (filas == 1) {
// return matriz[0][0];
// }

// double determinante = 0;
// for (int i = 0; i < columnas; i++) {
// determinante += matriz[0][i] * cofactor(matriz, 0, i);
// }

// return determinante;
// }

// public static double cofactor(double[][] matriz, int fila, int columna) {
// int filas = matriz.length;
// int columnas = matriz[0].length;

// double[][] submatriz = new double[filas - 1][columnas - 1];
// int subfila = 0;
// int subcolumna = 0;

// for (int i = 0; i < filas; i++) {
// for (int j = 0; j < columnas; j++) {
// if (i != fila && j != columna) {
// submatriz[subfila][subcolumna++] = matriz[i][j];

// if (subcolumna == filas - 1) {
// subfila++;
// subcolumna = 0;
// }
// }
// }
// }

// return Math.pow(-1, fila + columna) *
// calcularDeterminantePorColumna(submatriz);
// }
// }

// System.out.println(potencia);

// System.out.println("[" + i + "]" + "[" + j + "]");

// if ((i == 0) && (j == 0)) {
// matriz[0][0] = ins;
// System.out.println("[" + i + "]" + "[" + j + "]");
// System.out.println(ins);

// } else {
// System.out.println("[" + i + "]" + "[" + j + "]");
// matriz[i][j] = sumatoria_pow(i, potencia);
// // System.out.println(i + "," + j + ": " + matriz[i][j]);
// }
