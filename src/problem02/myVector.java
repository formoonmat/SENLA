package problem02;

public class myVector { //наш класс вектора
    private int x1,y1,x2,y2,z1,z2; //координаты вектора
    public myVector(int x1, int y1, int x2, int y2){ //конструктор с инициализацией координат (по начальному условию
                                                     // задачи - на плоскости XY)
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = 0;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = 0;
    }

    public myVector(int x1, int y1, int z1, int x2, int y2, int z2){ //конструктор с инициализацией координат (по
                                                                     //уточненному условию задачи - в пространстве)
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    public double Length(){//метод возвращающий длину вектора
        return Math.sqrt(Math.pow(this.x2-this.x1,2)+Math.pow(this.y2-this.y1,2)+Math.pow(this.z2-this.z1,2));
    }

    public String toString(){//перегрузка toString() для класса вектора
        return "начало (" + this.x1 + ", " + this.y1 + ", " + this.z1 + ") конец (" + this.x2 + ", " + this.y2 + ", "
                + this.z2 + ")";
    }

    public myVector mulVector(myVector v2){//метод возвращающий вектор-произведение вектора-объекта на
        //вектор-параметр
        int x,y,z,x1,y1,x2,y2,z1,z2;//координаты векторов индекс 1 - первый множитель, 2 - второй, без индекса результат
        //определение координат вектоар 1
        x1 = this.x2 - this.x1;
        y1 = this.y2 - this.y1;
        z1 = this.z2 - this.z1;
        //координат вектора 2
        x2 = v2.x2 - v2.x1;
        y2 = v2.y2 - v2.y1;
        z2 = v2.z2 - v2.z1;
        //координат вектора произведения
        x = y1*z2 - z1*y2;
        y = z1*x2 - x1*z2;
        z = x1*y2 - y1*x2;
        //возвращае новый вектор, с учетом что его начало совпадает с началом первого вектора
        return new myVector(this.x1, this.y1, this.z1, this.x1+x, this.y1+y, this.z1+z);
    }

}
