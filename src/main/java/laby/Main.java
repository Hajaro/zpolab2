package laby;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        //Zad 3
        Integer min = arrayList.stream()
                .min(Integer::compareTo)
                .get();

        //Zad 4
        ArrayList<Integer> evenArrayList = arrayList.stream()
                .filter(x -> x % 2 == 0)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        //Zad 5
        Person person1 = new Person("Jan", 20);
        Person person2 = new Person("Marek", 40);
        Person person3 = new Person("Krzysztof", 30);

        ArrayList<Person> personArrayList = new ArrayList<>();
        personArrayList.add(person1);
        personArrayList.add(person2);
        personArrayList.add(person3);
        ArrayList<Person> personSortedByNick = personArrayList.stream()
                .sorted(Comparator.comparing(Person::getNick))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        personSortedByNick.forEach(System.out::println);

        ArrayList<Person> personSortedByAge = personArrayList.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        personSortedByAge.forEach(System.out::println);

        //zad 6
        PunktXYZ punktXYZ1 = new PunktXYZ(1, 2, 3);
        PunktXYZ punktXYZ2 = new PunktXYZ(4, 5, 6);
        PunktXYZ punktXYZ3 = new PunktXYZ(7, 8, 9);
        ArrayList<PunktXYZ> punktXYZArrayList = new ArrayList<>();
        punktXYZArrayList.add(punktXYZ1);
        punktXYZArrayList.add(punktXYZ2);
        punktXYZArrayList.add(punktXYZ3);
        ArrayList<PunktXY> punktXYArrayList = punktXYZArrayList.stream()
                .map(x -> new PunktXY(x.getX(), x.getY()))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        for(PunktXY punktXY : punktXYArrayList){
            System.out.println(punktXY);
        }

        //zad 7
        List<Person> personList = Arrays.asList(person1, person2, person3);
        Group group = new Group("Grupa 1", personArrayList);
        Person person4 = new Person("Kamil", 25);
        Person person5 = new Person("Krzysztof", 30);
        Person person6 = new Person("Marek", 40);
        List<Person> personList2 = Arrays.asList(person4, person5, person6);
        Group group2 = new Group("Grupa 2", personList2);
        List<Group> groupList = Arrays.asList(group, group2);
        List<Person> allMembers = groupList.stream()
                .map(Group::getMembers)
                .flatMap(Collection::stream)
                .toList();
        allMembers.forEach(System.out::println);

        //Zad 8
        Optional<Integer> sum = arrayList.stream()
                .reduce((x, y) -> x + y);

        Integer multiplication = arrayList.stream()
                .reduce(1, (x, y) -> x * y);

        //Zad 9
        ArrayList<String> uids = new ArrayList<>();
        for(int i = 0; i < 1000000; i++){
            uids.add(UUID.randomUUID().toString());
        }
        long start = System.currentTimeMillis();
        uids.stream().sorted().collect(Collectors.toList());
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Time elapsed: " + timeElapsed);
        start = System.currentTimeMillis();
        uids.parallelStream().sorted().collect(Collectors.toList());
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.println("Time elapsed: " + timeElapsed);
    }
}