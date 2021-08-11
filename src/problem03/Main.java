package problem03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        /* Задача:
                Создайте программу, которая будет вычислять и выводить на экран простые множители, из которых состоит
                целое число, введенное пользователем. Если введенное число не целое, то нужно сообщать пользователю об
                ошибке.

                ограничений на размер введенного число в задаче нет, примим long
                возможны ошибки округления при определении целостности числа, например для 1.0E-3000
                допускаем что не положительных быть не может
                считаем что 1 простой множитель для любого целого положительного числа, т.е. его выводим по умолчанию
         */
        //определение ввода
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //читаем строку
        System.out.print("Введите целое число: ");
        String inputString = reader.readLine();
        inputString = inputString.trim();

        //парсим число из строки, в случае неудачи выходим с сообщением
        double doubleValue;
        try {
            doubleValue = Double.parseDouble(inputString);
        } catch  (NumberFormatException e){
            System.out.println("Вы ввели не число");
            return;
        }

        //преобразуем число в целое
        long val = (long) doubleValue;

        //проверяем на целое (возможны ошибки округления при значениях очень близких к целым)
        if (val != doubleValue){
            System.out.println("Введенное число не целое");
            return;
        }

        if (val<1) {
            System.out.println("Введенное число не положительное");
            return;
        }

        System.out.println("Простые делители для числа " + val + ": ");
        System.out.print("1 ");

        //исходное число, которое будем делить в процессе вычисления
        long tmpVal = val;
        //делитель
        long i = 2;


        //искать делители для ускорения стоит только до квадратного корня от текущего остатка
        //если делителя не находится, то в остатке простое число
        while (i <= Math.pow(tmpVal,0.5)){
            //простых делителей одной величины может быть несколько, вычисляем их
            while (tmpVal % i == 0){
                //выводим очередной делитель
                System.out.print(i + " ");
                //уменьшаем текущий остаток
                tmpVal/=i;
            }
            //переходим к следующему делителю
            i++;
        }
        //оставшееся в остатке большее единицы число является простым, так же его выводим
        if (tmpVal>1) System.out.print(tmpVal);
    }
}

