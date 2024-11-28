import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args){
        //Залание 1
        out.println("Task 1");
        int n = in.nextInt(); //тк в задании 4 требуется вывести таблицу по СПИРАЛИ с ЦЕНТРАЛЬНОГО элемента, можем утверждать, что значения m и n равны => m не нужна (более того n - нечетная)
        double [][] a = new double[n][n]; //массив с которым мы будем работать
        for (int i = 0; i < a.length; i++) { //заполняем массив
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = in.nextDouble();
            }
        }
        int t = in.nextInt(); //вводим переменную для сравнений
        out.println();

        //Задание 2
        out.println("Task 2");
        double [][] tm = new double[a.length][a[0].length]; //транспонированная матрица (проще работать со строками чем со столбцами)
        for (int i = 0; i<a.length; i++) { //заполняем транспонированную матрицу
            for (int j = 0; j<a[0].length; j++) {
                tm[j][i] = a[i][j];
            }
        }
        double [][] ml = new double[tm.length][2]; //матрица несущая в себе информацию о колличестве элементов в строке tm больше/меньше чем t
        for (int i = 0; i<tm.length; i++) {
            int cntLess = 0, cntMore = 0; //счетчик элементов строки tm больше/меньше t
            for (int j = 0; j<tm[i].length; j++) {
                if (tm[i][j]<t) cntLess++;
                if (tm[i][j]>t) cntMore++;
            }
            ml[i][0] = cntLess; //заполнение массива ml
            ml[i][1] = cntMore; //это тоже
        }
        for (int i = 1; i<ml.length; i++) { //это сортировка вставками
            int j = i;
            while (j>0) {
                if (ml[j-1][0]<ml[j][0] || (ml[j-1][0]==ml[j][0] && ml[j-1][1]<ml[j][1])) { //при выполнении условий для сортировки ml повторяем перестановку в tm
                    double[] s1 = ml[j]; ml[j] = ml[j-1]; ml[j-1] = s1;
                    double[] s2 = tm[j]; tm[j] = tm[j-1]; tm[j-1] = s2;
                    j--;
                } else break;
            }
        }
        for (int i = 0; i<tm.length; i++) {
            for (int j = 0; j<tm[0].length; j++) {
                a[j][i] = tm[tm[0].length-1-i][j];
            }
        }
        for (int i = 0; i < a.length; i++) { //вывод ответа
            for (int j = 0; j < a[i].length; j++) {
                out.print(a[i][j]+" ");
            }
            out.println();
        }
        out.println();

        //Задание 3
        out.println("Task 3");
        double [] mToRow = new double[a.length*a[0].length]; //для удобства запихнем все значения температур из a в одномерный массив
        int cur = 0;
        for (int i = 0; i<a.length; i++) { //заполняем
            for (int j = 0; j<a[i].length; j++) {
                mToRow[cur] = a[i][j];
                cur++;
            }
        }
        double otv = 0; //пойдет в ответ на 4 задание
        int cnt = 0; // счетчик повторений элемента
        for (int i = 0; i<mToRow.length; i++) { //находим наименьший элемент среди наиболее часто повторяющихся элементов
            int cntCur = 0;
            for (int j = 0; j<mToRow.length; j++) {
                if (mToRow[i]==mToRow[j]) cntCur++;
            }
            if (cntCur>cnt || (cnt==cntCur && mToRow[i]<otv)) {otv=mToRow[i];cnt=cntCur;}
        }
        out.println(otv);
        out.println();

        //Задание 4
        out.println("Task 4");

        double [][] ar = new double[a.length][a[0].length];
        for (int i = 0; i<a.length; i++) {
            for (int j = a[i].length-1; j>=0; j--) {
                ar[i][ar[i].length-1-j] = a[i][j];
            }
        }

        double [] spiral = new double[ar.length*ar[0].length]; //одномерный массив для хранения эл-тов а "по спирали"
        int k = 1, sr = ar.length/2, spiralInd = 1; //k - на сколько надо сдвинуть i/j; sr = чтобы понимать гдеБ относительно побочной диагонали мы находимся
        //spiralInd - чтобы заполнять spiral
        int i = sr, j = sr; //для перебора элементов матрицы (начиная со среднего)
        spiral[0] = ar[i][j];
        while (!(i==0 && j==0)) {
            if (i<=sr) {
                for (int x = 0; x<k; x++) {j++; spiral[spiralInd]=ar[i][j]; spiralInd++;}
                for (int x = 0; x<k; x++) {i++; spiral[spiralInd]=ar[i][j]; spiralInd++;}
                k++;
            } else {
                for (int x = 0; x<k; x++) {j--; spiral[spiralInd]=ar[i][j]; spiralInd++;}
                for (int x = 0; x<k; x++) {i--; spiral[spiralInd]=ar[i][j]; spiralInd++;}
                k++;
            }
        }
        for (int x = 1; x<ar[0].length; x++) {spiral[spiralInd]=ar[0][x]; spiralInd++;}
        for (int sp = 0; sp<spiral.length; sp++) out.print(spiral[sp]+" "); //выводим spiral
        out.println("\n");

        //Задание 5
        out.println("Task 5");
        for (int i5 = 0; i5<a.length; i5++) { //просто перебираем
            for (int j5 = 0; j5<a[i5].length; j5++) {
                if (a[i5][j5]<t) a[i5][j5] = t; //если значение меньше то заменяем
            }
        }
        for (int i5 = 0; i5<a.length; i5++) { //выводим
            for (int j5 = 0; j5<a[i5].length; j5++) {
                out.print(a[i5][j5]+" ");
            }
            out.println();
        }
    }
}