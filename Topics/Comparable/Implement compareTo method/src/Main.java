class Person implements Comparable<Person> {
    private final String name;
    private final int age;
    private final int height;
    private final int weight;

    public Person(String name, int age, int height, int weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(Person otherPerson) {
        return getName().equals(otherPerson.getName()) ?
                Integer.valueOf(getAge()).compareTo(otherPerson.getAge()) :
                getName().compareTo(otherPerson.getName());
    }
}