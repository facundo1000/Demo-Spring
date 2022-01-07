package ejemplodesarrollo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WorkingWithMaps {
  public static void main(String[] args) {
    Map<Person, Diamond> map = new HashMap<>();

    map.put(new Person("Marcus"), new Diamond("Patita"));
    System.out.println(new Person("Marcus").hashCode());
    System.out.println(new Person("Marcus").hashCode());
    System.out.println(map.get(new Person("Marcus")));
  }

  private static void map() {
    Map<Integer, Person> map = new HashMap<>();
    map.put(1, new Person("Robert"));
    map.put(2, new Person("Mary Ann"));
    map.put(3, new Person("Alex"));
    map.put(4, new Person("Facundo"));
    System.out.println(map);
    System.out.println(map.size());
    System.out.println(map.get(1));
    System.out.println(map.containsKey(3));

    System.out.println(map.keySet());
    System.out.println(map.entrySet());

    System.out.println("-----------------------------");
    map.entrySet().forEach(System.out::println);
    System.out.println("----------Lambda----------------");
    map.entrySet().forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));
    System.out.println("------------BiConsumer----------");
    map.forEach(
        (key, person) -> {
          System.out.println(key + " - " + person);
        });
  }

  private static class Person {
    String name;

    public Person(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "Person{ " + "name ='" + name + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Person person = (Person) o;
      return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name);
    }
  }

  record Diamond(String name) {}
}
