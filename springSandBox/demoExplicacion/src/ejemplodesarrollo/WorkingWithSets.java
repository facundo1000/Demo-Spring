package ejemplodesarrollo;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class WorkingWithSets {
  public static void main(String[] args) {

    Set<Ball> balls = new HashSet<>();
    balls.add(new Ball("blue"));
    balls.add(new Ball("blue"));
    balls.add(new Ball("red"));
    balls.add(new Ball("yellow"));
    balls.forEach(System.out::println);
    System.out.println(balls.size());
  }
  record Ball(String color){};
}
