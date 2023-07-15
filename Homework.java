import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Homework {

    /**
     * Создать класс Контейнер - Container.
     * В контейнере могут быть ящики (класс Box).
     *
     * У каждого ящика есть вес.
     * У каждого контейнера есть метод "получить вес" - это сумма всех весов ящиков,
     * которые находятся в контейнере.
     *
     * 1. Класс Контейнер должен быть Comparable. Сравнивать он должен по весам
     * контейнера (чем меньше вес, тем меньше контейнер).
     * 2. Класс ContainerCountComparator - Comparator, который сравнивает контейнеры
     * по количеству ящиков (чем меньше ящиков, тем меньше контейнер).
     * 3. Класс контейнер должен быть Iterable - итерирование должно происходить по
     * ящикам внутри контейнера.
     * <code>
     *     Container c = new Container(...);
     *     // ...
     *     for (Box box: c) {
     *         box - это контейнер
     *     }
     * </code>
     */

    public static void main(String[] args) {

        Container c = new Container();
        Box b1 = new Box();
        Box b2 = new Box();
        Box b3 = new Box();

        c.boxes.add(b1);
        c.boxes.add(b2);
        c.boxes.add(b3);

        Container c1 = new Container();
        Box b4 = new Box();
        Box b5 = new Box();
        Box b6 = new Box();
        Box b7 = new Box();

        c1.boxes.add(b4);
        c1.boxes.add(b5);
        c1.boxes.add(b6);
        c1.boxes.add(b7);

        System.out.println(b1.getBoxWeight());
        System.out.println(b2.getBoxWeight());
        System.out.println(b3.getBoxWeight());
        int cw = c.getContainerWeight();
        System.out.println("Вес первого контейнера: " + cw);

        System.out.println(b4.getBoxWeight());
        System.out.println(b5.getBoxWeight());
        System.out.println(b6.getBoxWeight());
        System.out.println(b7.getBoxWeight());
        int c1w = c1.getContainerWeight();
        System.out.println("Вес второго контейнера: " + c1w);

        int p = c.compareTo(c1);
        System.out.println(p);

        System.out.println("Ящиков в первом контейнере: " + c.getBoxesCount());
        System.out.println("Ящиков во втором контейнере: " + c1.getBoxesCount());        

        Container.containerCountComparator m = new Container.containerCountComparator();
        int p1 = m.compare(c, c1);

        System.out.println(p1);

    }

    static class Container implements Iterable<Box>, Comparable<Container> {

        private List<Box> boxes = new ArrayList<>();

        public void addBox(Box box) {
            boxes.add(box);
        }

        @Override
        public Iterator<Box> iterator() {
            return boxes.iterator();
        }

        public int getContainerWeight() {
            Iterator<Box> iter = boxes.iterator();
            int containerWeight = 0;
            while (iter.hasNext()) {
                containerWeight += (iter.next().getBoxWeight());
            }
            return containerWeight;
        }

        public int getBoxesCount() {
            Iterator<Box> iter = boxes.iterator();
            int BoxesCount = 0;
            while (iter.hasNext()) {
                BoxesCount += 1;
                iter.next();
            }
            return BoxesCount;
        }

        @Override
        public int compareTo(Container o) {
            if (this.getContainerWeight() > o.getContainerWeight())
                return 1;
            else if (this.getContainerWeight() < o.getContainerWeight())
                return -1;
            else
                return 0;
        }

        static class containerCountComparator implements Comparator<Container> {
            @Override
            public int compare(Container c, Container c1) {
                return c.getBoxesCount() - c1.getBoxesCount();
            }
        }

    }

    static class Box {
        int weight = ThreadLocalRandom.current().nextInt(1, 10);

        @Override
        public String toString() {
            return "[" + weight + "]";
        }

        public int getBoxWeight() {
            return this.weight;
        }
    }

}