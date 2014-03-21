package com.github.stadler.java8;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;


public class LambdaTester {
  
  private static UnaryOperator<Integer> factorial =  
      i -> { return i == 0 ? 1 : i * LambdaTester.factorial.apply( i - 1 ); };
  private static UnaryOperator<Integer> fibonacci =  
      i -> { return i < 2 ? 1 : LambdaTester.fibonacci.apply(i-2) + LambdaTester.fibonacci.apply(i-1); };


  public static void main(String[] args) {
    
    System.out.println("Fraction 5: " + LambdaTester.factorial.apply(5));
    
    
    System.out.print("Fibonnaci to 20: ");
    IntStream.range(0, 20).forEach(i -> System.out.print(LambdaTester.fibonacci.apply(i) + " "));
    System.out.println();
    
    testCollections();
  }
    
  private static void testCollections() {
    
    System.out.println("Collections:");
    List<String> list = Arrays.asList("A", "B", "C", "Z", "G", "L", "A", "Z", "G", "L");
    list.forEach(System.out::print);
    System.out.println();
    
    // One lambda two types
    
    System.out.println("x -> 2 * x for double and int");
    DoubleUnaryOperator doubleop = x -> 2 * x;
    IntUnaryOperator intop = x -> 2 * x;
    System.out.println(doubleop.applyAsDouble(3));
    System.out.println(intop.applyAsInt(4));
    
    ArrayList<String> newList = new ArrayList<>(list);
    newList.removeIf(element -> element == "B");
    newList.forEach(letter -> System.out.print(letter));
    
    System.out.println("\nStreams:");
    list.stream().filter(mychar -> mychar != "G").sorted().distinct().forEach(LambdaTester::giveString);
    
    System.out.println("\nStreams2:");
    System.out.println(list.stream().parallel().map(letter -> "!"+letter+"!").reduce((a, b) -> "<" + a +","+ b +">").get());
    
    Collections.sort(list, (String a, String b) -> {
      if (a == "B")
        return 1;
      else
       return a.equals(b) ? 0 : -1;
    });
    System.out.println("");
    list.forEach(letter -> System.out.print(letter));
    
    
  }
  
//  public interface A {
//    default void hello() { System.out.println("Hello World from A"); }
//  }
//  public interface B extends A {
//      default void hello() { System.out.println("Hello World from B"); }
//  }
//  public class C implements B, A {
//      public static void main(String... args) {
//          new C().hello();
//      }
//  } 
  
  public static String giveString(String a) {
    return "Balu" + a;
  }
  
  
}
