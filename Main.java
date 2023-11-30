package plr;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class Main {

    public static void verValor(String etiqueta, double valor) {
        System.out.println(etiqueta + ":  " + valor);
    }

    public static void printMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int X = 0;
        int Y = 1;

        int grado = 2;
        int tamaño = grado + 1;

        Matriz mX = new Matriz(tamaño, tamaño, grado);
        Matriz mY = new Matriz(tamaño, 1, 0);

        verValor("Matriz X", 0);
        mX.llenarMatriz(X);
        double[][] matriz = mX.getMatriz();

        verValor("Matris X", tamaño);
        printMatriz(matriz);
        System.out.println("");
        // mX.imprimirMatriz();

        verValor("Matriz Y", 0);
        mY.llenarMatrizY(grado);
        mY.imprimirMatriz();
        System.out.println("");

        verValor("Matriz Transpuesta", tamaño);
        double[][] transpuesta = mX.TranspuestaMatriz();
        printMatriz(transpuesta);
        System.out.println("");

        verValor("Producto", tamaño);
        double[][] producto = mX.ProductoMatriz(matriz, transpuesta);
        printMatriz(producto);
        System.out.println("");

        verValor("Trasnpuesta Producto", tamaño);
        double[][] prod_trans = mX.matrizTranspuesta(producto);
        printMatriz(prod_trans);
        System.out.println("");

        verValor("Adjunta", tamaño);
        double[][] adjunta = mX.obtenerMatrizAdjunta(prod_trans);
        printMatriz(adjunta);
        System.out.println("");

        double determinante = mX.determinante(producto);
        verValor("Determinante", determinante);
        System.out.println("");

        verValor("Inversa", tamaño);
        double[][] inversa = mX.matrizInversa(producto);
        printMatriz(inversa);
        System.out.println("");

        verValor("Producto Transpuesta", tamaño);
        double[][] producto2 = mX.ProductoMatriz(inversa, transpuesta);
        printMatriz(producto2);
        System.out.println("");

        verValor("Multiplicar Matrices", tamaño);
        double[][] y = mY.getMatriz();
        printMatriz(producto2);
        printMatriz(y);
        double[][] Tpory = mX.ProductoMatriz(producto2, y);
        printMatriz(Tpory);

        // Crear un objeto DecimalFormat con el formato deseado
        DecimalFormat formato = new DecimalFormat("###.##");

        // Formatear el número

        String formula = "";

        for (int i = 0; i < Tpory.length; i++) {
            for (int j = 0; j < Tpory[0].length; j++) {
                // System.out.print("Iter" + i + ": " + Tpory[i][j] + " ");;
                if (i == 0) {
                    formula += Tpory[i][j];
                    continue;
                }
                if (Tpory[i][j] < 0) {
                    formula += Tpory[i][j] + "x^" + i;
                } else {
                    formula += "+" + Tpory[i][j] + "x^" + i;
                }
            }
        }

        System.out.println(formula);

        System.out.println(Tpory.length);

        float[] predict = new float[Tpory.length - 1];

        double suma = 0;
        for (int i = 0; i < Tpory.length; i++) {

            if (i == 0) {
                suma += Tpory[i][0];
                continue;
            }
            double valor = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingresa El Valor De X" + i));
            suma += Tpory[i][0] * Math.pow(valor, i);

        }
        System.out.print("Prediccion: " + suma);

    }
}