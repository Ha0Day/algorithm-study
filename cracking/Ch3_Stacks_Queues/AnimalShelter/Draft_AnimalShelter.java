package Ch3_Stacks_Queues.AnimalShelter;

import java.util.LinkedList;

//3.6 Animal Shelter
//먼저 들어온 동물이 먼저 나가는 동물 보호소가 있다.
//이 보호소는 개와 고양이만 수용
//사람들은 가장 오래된 동물부터 입양 가능
//개와 고양이 중 어떤 동물 데려갈지는 선택 가능
//이 시스템을 자료구조로 구현하라
//이 자료구조는 enqueue, dequeueAny, dequeueDog, dequeueCat의 연산을 제공해야 한다.
//기본적으로 탑재되어 있는 LinkedList 자료구조 사용 가능


//Draft code
public class Draft_AnimalShelter {

    public static void main(String[] args) {
        AnimalShelterSystem system = new AnimalShelterSystem();

        system.enqueue(new Dog("Ralph"));
        system.enqueue(new Dog("Ray"));
        system.enqueue(new Cat("Hao"));
        system.enqueue(new Cat("Hale"));
        system.enqueue(new Dog("Choco"));
        system.enqueue(new Dog("Shiba"));

        System.out.println(system.dequeueDog().name);

        System.out.println(system.dequeueCat().name);

        System.out.println(system.dequeueDog().name);

        Animal animal = system.dequeueAny();
        System.out.println(animal.name);


    }

    static class AnimalShelterSystem {
        LinkedList<Cat> cats = new LinkedList();
        LinkedList<Dog> dogs = new LinkedList();
        int order = 0;

        void enqueue(Animal animal) {
            if (animal instanceof Dog) {
                dogs.add((Dog) animal);
            } else {
                cats.add((Cat) animal);
            }
            animal.setOrder(order++);
        }

        Animal dequeueAny() {
            Animal animal;
            if (!dogs.isEmpty() && !cats.isEmpty()) {
                if (dogs.get(0).order > cats.get(0).order) {
                    return cats.get(0);
                } else {
                    return dogs.get(0);
                }
            } else if (dogs.isEmpty() && !cats.isEmpty()) {
                animal = cats.get(0);
                cats.remove(0);
                return animal;
            } else if (!dogs.isEmpty() && cats.isEmpty()) {
                animal = dogs.get(0);
                dogs.remove(0);
                return animal;
            }
            return null;
        }

        Dog dequeueDog() {
            Dog dog;
            if (!dogs.isEmpty()) {
                dog = dogs.get(0);
                dogs.remove(0);
                return dog;
            }
            return null;
        }

        Cat dequeueCat() {
            Cat cat;
            if (!cats.isEmpty()) {
                cat = cats.get(0);
                cats.remove(0);
                return cat;
            }
            return null;
        }
    }

    static class Animal {
        String name;
        int order;

        void setOrder(int order) {
            this.order = order;
        }
    }

    static class Dog extends Animal {
        Dog(String name) {
            this.name = name;
        }
    }

    static class Cat extends Animal {
        Cat(String name) {
            this.name = name;
        }
    }
}
