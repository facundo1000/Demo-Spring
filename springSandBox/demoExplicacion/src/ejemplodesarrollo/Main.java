package ejemplodesarrollo;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class Main {
  public static void main(String[] args) {

    LinkedList<Person> linkedList = new LinkedList<>();
    linkedList.add(new Person("Robert", 26));
    linkedList.add(new Person("Mark", 58));
    linkedList.addFirst(new Person("Markus", 25));

    ListIterator<Person> personListIterator = linkedList.listIterator();

    // Mientras haya un "siguiente"
    while (personListIterator.hasNext()) {
      System.out.println(personListIterator.next());
    }
    System.out.println("---------------------------------------");
    // Miestras tenga un "anterior"
    while (personListIterator.hasPrevious()) {
      System.out.println(personListIterator.previous());
    }
  }

  private static void queue() {
    Queue<Person> supermarket = new LinkedList<>();
    supermarket.add(new Person("Robert", 26));
    supermarket.add(new Person("Mary Ann", 16));
    supermarket.add(new Person("Mark", 32));

    System.out.println(supermarket.size());
    System.out.println(supermarket.peek());
    System.out.println(supermarket.poll());
    System.out.println(supermarket.size());
    System.out.println(supermarket.peek());
  }

  static record Person(String name, int age) {}
}
