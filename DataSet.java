package plr;

public class DataSet {

    double data[][] = {
            { 2, 1.1 },
            { 5, 2 },
            { 6, 3 },
            { 12, 5 },
            { 18, 7 }

            // { 108, 95 },
            // { 115, 96 },
            // { 106, 95 },
            // { 97, 97 },
            // { 95, 93 },
            // { 91, 94 },
            // { 97, 95 },
            // { 83, 93 },
            // { 83, 92 },
            // { 78, 86 },
            // { 54, 73 },
            // { 67, 80 },
            // { 56, 65 },
            // { 53, 69 },
            // { 61, 77 },
            // { 115, 96 },
            // { 81, 87 },
            // { 78, 89 },
            // { 30, 60 },
            // { 45, 63 },
            // { 99, 95 },
            // { 32, 61 },
            // { 25, 55 },
            // { 28, 56 },
            // { 90, 94 },
            // { 89, 93 }
    };

    public double[][] getData() {
        return data;
    }

    public double sumatoria_pow(int columna, int grado_polinomial) {
        double sumatoria = 0;
        for (int i = 0; i < data.length; i++) {
            double valor = data[i][columna];
            sumatoria += Math.pow(valor, grado_polinomial);
        }
        // System.out.println(sumatoria);
        return sumatoria;
    }

    public double multiplicar_sum(int column1, int column2, int grado1, int grado2) {
        double sumatoria = 0;

        for (int i = 0; i < data.length; i++) {
            double col1 = Math.pow(data[i][column1], grado1);
            double col2 = Math.pow(data[i][column2], grado2);
            sumatoria += col1 * col2;
        }
        return sumatoria;
    }

    public double instancias() {
        return data.length;
    }

}