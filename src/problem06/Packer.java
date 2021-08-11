package problem06;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Packer {
    private Item items[];
    private Safe safe;
    private Node maxNode;

    public Packer(Safe safe, Item items[]){
        this.safe = safe;
        this.items = items;
    }

    private class Node {
        public Node parent;
        public Node inSafe;
        public Node outSafe;

        public long currentCost;
        public int currentV;
    }

    private class LinkedItem{
        public LinkedItem child;
        public Item item;
        public LinkedItem(Item item){
            this.item = item;
            child = null;
        }
    }

    private void nextItemPlace(Node currentNode, int currentItem) {
        if(currentItem==items.length) return;
        Node with = new Node();
        if (currentNode.currentV+items[currentItem].getV()>safe.getV()){
            with = null;
            currentNode.inSafe = with;
        }else{
            with.parent = currentNode;
            with.currentV = currentNode.currentV+items[currentItem].getV();
            with.currentCost = currentNode.currentCost+items[currentItem].getCost();
            if (maxNode.currentCost < with.currentCost)
                maxNode = with;
            currentNode.inSafe = with;
            nextItemPlace(currentNode.inSafe,currentItem+1);
        }
        Node without = new Node();
        without.parent = currentNode;
        without.currentV = currentNode.currentV;
        without.currentCost = currentNode.currentCost;
        currentNode.outSafe = without;
        nextItemPlace(currentNode.outSafe,currentItem+1);
        return;
    }

    //поиск искомого набора, перебор вариантов с составлением дерева, исключая ветви на которых превышается допустимый объем
    public Safe Pack(){
        if (items.length == 0) return safe;
        Node firstNode = new Node();
        firstNode.currentCost = 0;
        firstNode.currentV = 0;
        firstNode.parent = null;

        maxNode = firstNode;

        nextItemPlace(firstNode,0);

        Node indNode = maxNode;
        byte X[] = new byte[items.length];
        Arrays.fill(X,(byte)0);
        int ind = 0;
        while (indNode.parent != null){
            if (indNode.parent.currentCost != indNode.currentCost)
                X[ind]=1;
            ind++;
            indNode = indNode.parent;
        }
        for(int i=1;i<=ind;i++){
            if (X[ind-i]==1)
                safe.putItem(items[i-1]);
        }

        return safe;
    }

    //нахождение максимальной ценности для переданного набора вещей и объема
    private long getMaxCost(LinkedItem firstItem, int V){
        //если исходный список нулевой, ценность 0
        if (firstItem == null) return 0;
        //если допустимый объем нулевой, ценность 0
        if (V == 0) return 0;
        //проверка на наличие в списке вещей, вмещающихся в заданный объем, если таковых нет, то ценность 0
        boolean foundCanToPlace = false;
        LinkedItem currentItem = firstItem;
        while (currentItem != null){
            if (currentItem.item.getV()<=V){
                foundCanToPlace = true;
                break;
            }
            currentItem = currentItem.child;
        }
        if (!foundCanToPlace) return 0;

        //текущий элемент списка
        currentItem = firstItem;
        //тут будем хранить ценности для каждого варианта в текущей итерации
        ArrayList<Long> currentCost = new ArrayList<Long>();
        //предшествующий текущему элемент
        LinkedItem privItem = null;
        //флаг для первого прохода цикла (чтобы не изменить ссылку на дочерний элемент в первом элементе списка)
        boolean firstTime = true;
        //цикл по всему связанному списку
        while (currentItem != null){
            //если текущая вещь помещается в заданный объем
            if (V>=currentItem.item.getV()) {
                //исключаем из списка текущий элемент и добавляем в currentCost ценность для случая если currentItem
                //положили в сейф (с учетом рекурсивного вызова getMaxCost с параметрами - список вещей без текущей
                //вещи, допустимый объем за вычетом объема текущей вещи)
                if (!firstTime) {//вариант для всех за исключением первого прохода
                    privItem.child = currentItem.child;
                    currentCost.add(currentItem.item.getCost() + getMaxCost(firstItem, V - currentItem.item.getV()));
                    privItem.child = currentItem;
                }else {//вариант для первого прохода
                    currentCost.add(currentItem.item.getCost() + getMaxCost(firstItem.child, V - currentItem.item.getV()));
                }

            }

            firstTime = false;
            privItem = currentItem;
            currentItem = currentItem.child;
        }

        return Collections.max(currentCost);
    }

    //поиск максимальной ценности из расчета максимальная стоимость(всех вещей, весь объем)= макс(стоимость вещи +
    //максимальная стоимость(всех без одной, объем без одной)), даже без составление
    //перечня вещей по тестам получилось медленнее перебора с деревом
    public Safe PackDP(){
        //создаем связанный список вещей (чтобы не копировать массивы при рекурсивных вызовах)
        if (items.length == 0) return safe;
        LinkedItem firstItem = new LinkedItem(items[0]);
        LinkedItem currentItem = firstItem;
        for (int i=1;i < items.length; i++){
            currentItem.child = new LinkedItem(items[i]);
            currentItem = currentItem.child;
        }
        //ищем максимальную ценность для исходного набора и объема
        long maxcost = getMaxCost(firstItem,safe.getV());
        //вывод на экран тестовой информации
        System.out.println("Максимальная стоимость - "+maxcost);
        System.out.println("Восстанавливать содержимое не имеет смысла из-за времени выполнения");
        return safe;
    }


    //решение с
    //https://skillbox.ru/media/code/dinamicheskoe_programmirovanie_eto_prosto_reshaem_zadachu_o_ryukzake/
    public Safe PackDP1(){
        int n = items.length;
        int k = safe.getV();

        Safe[][] bp = new Safe[n+1][k+1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < k + 1; j++) {
                if (i == 0 || j == 0) { //нулевую строку и столбец заполняем нулями
                    bp[i][j] = new Safe(new Item[]{}, k);
                } else if (i == 1) {
                    /*первая строка заполняется просто: первый предмет кладём или не кладём в зависимости от веса*/
                    bp[1][j] = items[0].getV() <= j ? new Safe(new Item[]{items[0]}, k)
                            : new Safe(new Item[]{}, k);
                } else {
                    if (items[i - 1].getV() > j) //если очередной предмет не влезает в рюкзак,
                        bp[i][j] = bp[i - 1][j];    //записываем предыдущий максимум
                    else {
                        /*рассчитаем цену очередного предмета + максимальную цену для (максимально возможный для рюкзака вес − вес предмета)*/
                        long newPrice = items[i - 1].getCost() + bp[i - 1][j - items[i - 1].getV()].getCost();
                        if (bp[i - 1][j].getCost() > newPrice) //если предыдущий максимум больше
                            bp[i][j] = bp[i - 1][j]; //запишем его
                        else {
                            /*иначе фиксируем новый максимум: текущий предмет + стоимость свободного пространства*/
                            bp[i][j] = new Safe(Stream.concat(Arrays.stream(new Item[]{items[i - 1]}),
                                    Arrays.stream(bp[i - 1][j - items[i - 1].getV()].getItems())).toArray(Item[]::new), k);
                        }
                    }
                }
            }
        }

        List<Safe> lastColumn = Arrays.stream(bp).map(row -> row[row.length - 1]).collect(Collectors.toList());


        return  lastColumn.stream().max(Comparator.comparing(Safe::getCost)).orElse(new Safe(null, k));
    }
}
