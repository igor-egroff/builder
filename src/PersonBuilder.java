public class PersonBuilder {

    private String name;
    private String surname;
    private int age;
    private String city;

    protected static int i = 0;

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) {
        // Проверка незаполненности обязаттельных полей
        try {
            if (age < 0)
                throw new InternalError("возраст не может быть отрицательным или равен нулю");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        this.age = age;
        return this;

    }

    public PersonBuilder setAddress(String city) {
        this.city = city;
        return this;
    }

    public Person build() {
        i++;
        // Проверка незаполненности обязаттельных полей
        try {
            if ((i == 2) & (surname == null | name == null)) {
                throw new InternalError("Не все поля заполнены");
            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return new Person(name, surname, age, city);
    }

}
