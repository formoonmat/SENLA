package problem06;


import java.util.ArrayList;
import java.util.Collections;

//класс сейфа
public class Safe {
    //объем сейфа
    private int V;
    ArrayList<Item> Items = new ArrayList<Item>();

    //конструктор
    public Safe(int V){
        this.V = V;
    }

    //конструктор для решения со skillbox.ru
    public Safe(Item[] Items, int V){
        this.V = V;
        this.Items = new ArrayList<Item>();
        if (Items==null) return;
        if (Items.length>0)
            Collections.addAll(this.Items, Items);
    }

    public Item[] getItems(){
        return Items.toArray(Item[]::new);
    }

    //геттер V
    public int getV(){
        return V;
    }

    //возвращает оставшееся свободное место
    public int getFreeSpace(){
        //оставшийся объем приравниваем к объему сейфа
        int freeV = V;
        //в цикле уменьшаем оставшийся объем на объем уже положенных вещей
        for (Item i: Items){
            freeV-=i.getV();
        }
        return freeV;
    }

    //возвращает ценность вещей
    public long getCost(){
        long res = 0;
        //
        for (Item i: Items){
            res += i.getCost();
        }
        return res;
    }

    //можно ли поместить вещь в сейф (true если можно)
    public boolean canPutItem(Item i){
        return getFreeSpace()>=i.getV();
    }

    //помещаем вещь в сейф
    public boolean putItem(Item i){
        //проверяем не переполнится ли сейф, если места уже нет возвращаем false
        if (!canPutItem(i)) return false;
        //"упаковываем" вещь в сейф
        return Items.add(i);
    }

    //перегрузка toString
    public String toString(){
        //если в предметах ничего нет то и говорим что пусто
        if (Items.size()<1) return "Сейф пуст";
        //итоговая ценность сейфа
        long sumCost = 0;
        //Начальный текст для вывода
        String res = "Содержимое сейфа объемом " + V + ":\n";
        //Цикл по всем вещам
        for(Item i: Items){
            //добавляем вещь в выводимый текст
            res+=i+"\n";
            sumCost += i.getCost();
        }
        //добавляем информацию по итоговой ценности
        res += "Ценность содержимого сейфа - " + sumCost + ", незанятый объем - " + getFreeSpace();
        return res;
    }

}
