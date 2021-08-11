package problem04;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        /* Задача:
                Создайте программу, которая будет:
                    - подсчитывать количество гласных в словах
                    - выводить слова отсортированным списком по количеству гласных (сначала те, у которых больше
                    гласных)
                    - делать первую гласную букву в слове заглавной
                Предложение вводится пользователем вручную русскими буквами. Разделитель пробел (" ").


                Так как иное не оговорено, предполагаем что пользователь может вводить предложение как строчными так и
                прописными буквами. Исходя из задания само количество гласных не выводится
         */
        //определение ввода
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //читаем строку
        System.out.print("Введите предложение: ");
        String inputString = reader.readLine();


        //String inputString = "выводить слова отсортированным    с  у  п списком по количеству гласных";


        //удалим начинающие и завершающие пробелы(чтобы последующий сплит не давал пустых("") слов)
        inputString = inputString.trim();
        //убираем задвоенные пробелы в середине текста(чтобы последующий сплит не давал пустых("") слов)
        while (inputString.indexOf("  ")>-1)
                    inputString = inputString.replaceAll("  "," ");
        //разбиваем строку на массив слов
        String[] arrWords = inputString.trim().split(" ");//проработать подряд идущие пробелы

        //создаем массив объектов классаWordWithVowel
        WordWithVowel[] Words = new WordWithVowel[arrWords.length];
        //наполняем массив объектами, создаваемыми на основании массива слов
        int i = 0;
        for (String s : arrWords) {
            Words[i++] = new WordWithVowel(s);
        }
        //сортируем по убыванию количества гласных
        Arrays.sort(Words,Collections.reverseOrder());
        //выводим отсортированный массив с заглавными первыми гласными
        for (WordWithVowel W: Words){
            System.out.println(W.getWordWithBigFirstVowel() );
        }
    }
}

