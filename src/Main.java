import java.util.*;

public class Main {
    private static Person mom;
    private static Person son;
    private static Map<Person, List<Person>> persons = new HashMap<>();

    public static void main(String[] args) {

//1 тест-------------------------------------------------------------------
        mom = new PersonBuilder()
                .setName("Анна")
                .setSurname("Петрова")
                .setAge(32)
                .setAddress("Сидней")
                .build();
        son = mom.newChildBuilder()
                .setName("Антошка")
                .build();
        fillingListPerson(mom, son);
        son = mom.newChildBuilder()
                .build();

//2 тест-------------------------------------------------------------------
        mom = new PersonBuilder()
                .setName("Анна")
                .setSurname("Петрова")
                .setAge(32)
                .setAddress("Орел")
                .build();
        son = mom.newChildBuilder()
                .setName("Петр")
                .build();

        fillingListPerson(mom, son);
        son = mom.newChildBuilder()
                .build();

//3 тест-------------------------------------------------------------------
        mom = new PersonBuilder()
                .setName("Ольга")
                .setSurname("Иванова")
                .setAge(31)
                .setAddress("Питер")
                .build();
        fillingListPerson(mom, son);
        son = mom.newChildBuilder()
                .build();
//4 тест-----------------------------------------------------------------
        mom = new PersonBuilder()
                .setName("Майя")
                .setSurname("Семенова")
                //.setAge(31)
                .setAddress("Новосибирск")
                .build();
        son = mom.newChildBuilder()
                .setName("Вася")
                .build();
        fillingListPerson(mom, son);

        // Печать списка людей
        System.out.println("Список :");
        for (Map.Entry<Person, List<Person>> entry : persons.entrySet()) {

            System.out.println("-   " + entry.getValue().get(0).city + ",   " + entry.getValue().get(0).name + ",   "
                    + entry.getValue().get(0).surname + ",   " + entry.getValue().get(0).age + ",   "
                    + entry.getValue().get(1).name);
        }

        // Проверка незаполненности обязаттельных полей

        try {
            if (mom.name == null | mom.surname == null) {
                throw new InternalError("Не все поля заполнены");
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        //Проверка, что возраст положительное число
        try {
            if (mom.age < 0)
                throw new InternalError("возраст не может быть отрицательным или равен нулю");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

    //Проверка наличия человека в списке
    public static void fillingListPerson(Person mom, Person son) {
        if (!addPerson(new Person(mom.name, mom.surname), Arrays.asList(mom, son))) {
            System.out.println("Такой человек есть в списке, обновление данных: ");
            Person finalMom = mom;
            Person finalSon = son;

            //Смена адреса человка в списке
            persons.merge(new Person(mom.name, mom.surname),
                    persons.get(new Person(mom.name, mom.surname)),
                    (a, b) -> Arrays.asList(finalMom, finalSon));

            //   Если возраст человека известен, то с момента создания объекта он может быть изменён только
            //   увеличением на единицу
            if (mom.hasAge()) {
                mom = new PersonBuilder()
                        .setName(mom.name)
                        .setSurname(mom.surname)
                        .setAge(mom.age++)
                        .setAddress(mom.city)
                        .build();
            }

        }
        System.out.printf("У %s-летней %s %s, проживающей в %s есть сын, %s", mom.age, mom.name, mom.surname,
                mom.city, son.name);

        System.out.println();


    }

    public static boolean addPerson(Person mom, List<Person> person) {

        if (persons.containsKey(mom)) {
            return false;
        }
        persons.put(mom, person);
        return true;
    }
}