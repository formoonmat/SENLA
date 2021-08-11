package problem01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
	/* Задача:
	        Напишите программу, которая возвращает сумму цифр, присутствующих в данной строке. Если цифр нет,
	        то возвращаемая сумма должна быть равна 0.

	   т.к. начальные ограничения не заданы, предпологаем достаточным использование String,
       а ограничение на сумму как максимальная длина String (7FFFFFFF) * максимальное значение цифры (9)
       уже long
       т.к. не определен способ задания строки принимаем следующее - предлагаем ввести строку, в случае пустой
       работаем с "прошитым" примером
	 */
        //строка для примера
        final String exampleString = "Сумма цифр в строке '2345' должна быть равна четырнадцати";
        //определение ввода
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //читаем строку
        System.out.print("Введите строку(или нажмите Enter для отображения примера): ");
        String inputString = reader.readLine();

        //если пустая, то работаем с примером
        if (inputString.isEmpty()) inputString = exampleString;

        //итоговый вывод
        System.out.println("Для строки - "+inputString+" сумма цифр равна "+SumDigits(inputString));
    }

    public static long SumDigits(String s){
        //для суммирования результата
        long res = 0;

        //пробегаем по всем символам в строке s
        for (char symb:s.toCharArray()
             ) {
            //если очередной цифра
            if (Character.isDigit(symb)){
                //то прибавляем ее значение к результату
                res += Character.getNumericValue(symb);
            }
        }
        return res;
    }
}
