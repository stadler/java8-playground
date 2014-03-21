package com.github.stadler.java8;

public class DefaultMethods implements DefaultMethodsInterface {

  public static final void main(String... args) {
    new DefaultMethods().printHello();
  }

  /** HEllo <br>
   * <ul>
   * <li>Test
   * @foobar Bla */
  public void printHello() {
    DefaultMethodsInterface.super.printHello();
    System.out.println("World ");
  }

}

interface DefaultMethodsInterface {

  default void printHello() {
    System.out.print("Hello ");
  }
}

