package Stacks_Queues.AnimalShelter;

import java.util.LinkedList;

//3.6 Animal Shelter
//먼저 들어온 동물이 먼저 나가는 동물 보호소가 있다.
//이 보호소는 개와 고양이만 수용
//사람들은 가장 오래된 동물부터 입양 가능
//개와 고양이 중 어떤 동물 데려갈지는 선택 가능
//이 시스템을 자료구조로 구현하라
//이 자료구조는 enqueue, dequeueAny, dequeueDog, dequeueCat의 연산을 제공해야 한다.
//기본적으로 탑재되어 있는 LinkedList 자료구조 사용 가능

//Solution
//Draft code와 거의 동일
public class Sol_AnimalShelter {

    public static void main(String[] args) {
        AnimalShelterSystem system = new AnimalShelterSystem();

        system.enqueue(new Dog("Ralph"));
        system.enqueue(new Dog("Ray"));
        system.enqueue(new Cat("Hao"));
        system.enqueue(new Dog("Choco"));
        system.enqueue(new Cat("Hale"));
        system.enqueue(new Dog("Shiba"));

        System.out.println(system.dequeueDog().name);

        System.out.println(system.dequeueCat().name);

        System.out.println(system.dequeueDog().name);

        Animal animal = system.dequeueAny();
        System.out.println(animal.name);
    }

    abstract static class Animal {
        private int order;
        protected String name;

        public Animal(String n) {
            name = n;
        }

        public void setOrder(int ord) {
            order = ord;
        }

        public int getOrder() {
            return order;
        }

        public boolean isOlderThan(Animal a) {
            return this.order < a.getOrder();
        }
    }

    public static class Dog extends Animal {
        //자손 클래스의 생성자 첫 줄에는 반드시 조상 클래스의 생성자가 있어야 함
        public Dog(String n) {
            super(n);
        }
    }

    public static class Cat extends Animal {
        //자손 클래스의 생성자 첫 줄에는 반드시 조상 클래스의 생성자가 있어야 함
        public Cat(String n) {
            super(n);
        }
    }

    static class AnimalShelterSystem {
        LinkedList<Dog> dogs = new LinkedList();
        LinkedList<Cat> cats = new LinkedList();
        private int order = 0;

        public void enqueue(Animal a) {
            a.setOrder(order);
            order++;

            if (a instanceof Dog) dogs.add((Dog) a);
            else if (a instanceof Cat) cats.add((Cat) a);
        }

        public Animal dequeueAny() {
            if (dogs.size() == 0) {
                return dequeueCat();
            } else if (cats.size() == 0) {
                return dequeueDog();
            }

            Dog dog = dogs.peek();
            Cat cat = cats.peek();

            if (dog.isOlderThan(cat)) {
                return dequeueDog();
            } else {
                return dequeueCat();
            }
        }

        private Animal dequeueCat() {
            return cats.poll();
        }

        private Animal dequeueDog() {
            return dogs.poll();
        }
    }
}
