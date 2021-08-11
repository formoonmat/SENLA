package problem04;

public class WordWithVowel implements Comparable<WordWithVowel>{
    //определим гласные, static - чтобы не сохранять для каждого объекта
    private static final String Vowels = "АЕЁИОУЫЭЮЯ";
    //тут будем хранить исходное слово
    private String Word;
    //тут количество гласных
    private int cntVowels;

    //конструктор
    public WordWithVowel(String s){
        //сохраняем слово
        this.Word = s;

        //считаем количество гласных
        //переводим все слово к верхнему регистру (т.к. по заданию ограничений на регистр букв нет, а Vowels мы
        //определили в верхнем регистре)
        String bigS = s.toUpperCase();
        //обнуляем счетчик гласных для объекта
        this.cntVowels = 0;
        //в цикле по каждой гласной из Vowels суммируем счетчик по методу: длина исходного слова минус длина слова с
        //удаленной текущей гласной из Vowels
        for(char ch : Vowels.toCharArray())
            this.cntVowels+=bigS.length()-bigS.replaceAll(""+ch,"").length();
    }

    //геттер для cntVowels
    public int getCntVowels(){
        return cntVowels;
    }

    //возвращает хранимое слово в представлении "первая гласная буква в верхнем регистре"
    public String getWordWithBigFirstVowel(){
        //если в слове нет гласных, то возвращаем его исходный вид
        if (this.cntVowels == 0) return this.Word;
        //делаем копию нашего слова с символами в верхнем регистре
        String bigWord = this.Word.toUpperCase();
        //пробегаем по всем символам копии
        for(int i=0;i<bigWord.length();i++) {
            //если в списке гласных (Vowels) есть символ из копии слова (все приведено к верхнему регистру), то
            //возвращаем слово с приведенному к верхнему регистру текущему символу(т.е. первая гласная)
            if (Vowels.indexOf(bigWord.charAt(i))>-1){
                return this.Word.replaceFirst(this.Word.substring(i,i+1),this.Word.substring(i,i+1).toUpperCase());
            }
        }
        //до этого return не дойдет
        return "";
    }

        //реализация compareTo из Comparable для нашего WordWithVowel
        public int compareTo(WordWithVowel WordToCompare) {
            //сравниваем по количеству гласных
            return this.getCntVowels() - WordToCompare.getCntVowels();
        }
}
