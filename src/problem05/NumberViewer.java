package problem05;

public class NumberViewer {
    //массив объектов цифр
    private Digit Digs[];
    //будет содержать наибольшую цифру числа
    private byte bigDig;
    //высота цифроместа
    private static final byte height = 8;

    //конструктор
    public NumberViewer(int dig){
        //переводим полученное число в строковое представление
        String sDig = ((Integer)dig).toString();
        //создаем массив символов из переведенного в строку числа
        char chDigs[] = sDig.toCharArray();
        //создаем массив объектов Digit из предыдущего массива
        Digs = new Digit[chDigs.length];
        bigDig = 0;
        for (int i=0;i<chDigs.length;i++){
            //создание очередного объекта
            Digs[i] = new Digit((byte)(chDigs[i]-48));
            //определение максимальной цифры (сравнение с текущей максимальной и переприсваивание при необходимости)
            if (bigDig < (byte)chDigs[i]-48) bigDig = (byte)(chDigs[i]-48);
        }
    }

    //возвращает строку итогового псевдографического представления
    private String getViewLine(byte Line){
        if ((Line<1) || (Line>height)) Line = 0; //если вдруг вышли за пределы, пусть будет 0
        String digSpace = "   ";
        String res = "";
        for (Digit dig: Digs){
            if (dig.getDig() == this.bigDig){
                res+=dig.getViewLine(Line,(char)(this.bigDig+48)) + digSpace;
            }else{
                res+=dig.getViewLine(Line) + digSpace;
            }
        }
        res=res.substring(0,res.length()-1);
        return res;
    }


    public void PrintNumber(){
        for(int i=1;i<=this.height;i++){
            System.out.println(getViewLine((byte) i));
        }
    }

}
