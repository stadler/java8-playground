package com.github.stadler.java8;

public class FuncionalInterfaceTester {
  public static final void main (String... args) {
    Foo f = () -> System.out.println("Foo!");
    Bar b = () -> System.out.println("Bar!");
    f.foo();
    b.bar();
  }
}

interface Foo {
  void foo();
//  void xong();
}

@FunctionalInterface
interface Bar {
  void bar();
//  void kong();
}
