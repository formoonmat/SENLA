package problem06;

import problem04.WordWithVowel;

//класс предмета
public class Item implements Comparable<Item>{
    //два обязательных параметра - объем и ценность
    private int V, Cost;
    //добавим название вещи
    private String name;

    //конструктор
    public Item(int V, int Cost, String name){
        this.V = V;
        this.Cost = Cost;
        this.name = name;
    }

    //геттер V
    public int getV(){
        return V;
    }

    //геттер Cost
    public int getCost(){
        return Cost;
    }

    //
    public double averageCost(){
        return Cost/V;
    }

    //перегрузка toString
    public String toString(){
        return this.name + "(объем - " + V + ", ценность - " + Cost + ")";
    }

    //реализация compareTo из Comparable для нашего Item
    public int compareTo(Item ItemToCompare) {
        //сравниваем по удельной стоимости
        double dif = Cost/V - ItemToCompare.getCost()/ItemToCompare.getV();
        if (dif<0){
            return -1;
        }else if (dif>0){
            return 1;
        }
        return 0;
    }

}
