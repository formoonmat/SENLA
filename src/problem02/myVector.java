package problem02;

public class myVector { //наш класс вектора
    private int x1,y1,x2,y2; //координаты вектора
    public myVector(int x1, int y1, int x2, int y2){ //конструктор с инициализацией координат
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double Length(){//метод возвращающий длину вектора
        return Math.sqrt(Math.pow(this.x2-this.x1,2)+Math.pow(this.y2-this.y1,2));
    }

    public String toString(){//перегрузка toString() для класса вектора
        return "начало (" + this.x1 + ", " + this.y1 + ") конец (" + this.x2 + ", " + this.y2 + ")";
    }

    public myVector mulVector(myVector v2){//метод возвращающий вектор-произведение вектора-объекта на
        //вектор-параметр
        //алгоритм пока не ясен
        return v2;
    }

}
