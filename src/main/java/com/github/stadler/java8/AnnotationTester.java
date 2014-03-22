/*
 * Copyright (C) 2014 by Netcetera AG.
 * All rights reserved.
 *
 * The copyright to the computer program(s) herein is the property of Netcetera AG, Switzerland.
 * The program(s) may be used and/or copied only with the written permission of Netcetera AG or
 * in accordance with the terms and conditions stipulated in the agreement/contract under which 
 * the program(s) have been supplied.
 */
package com.github.stadler.java8;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class AnnotationTester {

  public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException, SecurityException {
    AnnotationTester annotationTester = new AnnotationTester();
    Class<? extends AnnotationTester> klass = annotationTester.getClass();
    Method[] methods = klass.getMethods();
    System.out.println("Found " + methods.length + " methods.");
    for (Method method : methods) {
      Annotation[] methodAnnotations = method.getDeclaredAnnotations();
      System.out.println("Found " + methodAnnotations.length + " annotations on method "
          + method.getName() + ".");
      for (Annotation annotation : methodAnnotations) {
        System.out.println(annotation.annotationType());
        if (annotation instanceof RunnableMethod) {
          method.invoke(annotationTester);
        }
        if (annotation instanceof RunnableMethods) {
          RunnableMethods rm = (RunnableMethods) annotation;
          for (RunnableMethod runMethod : rm.value()) {
            method.invoke(annotationTester);
          }
        }
      }

    }
  }

  @RunnableMethod
  public static void foo() {
    System.out.println("Foo");
  }

  @RunnableMethod
  @RunnableMethod
  public static void bar() {
    System.out.println("Bar");
  }

}


@Repeatable(RunnableMethods.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface RunnableMethod {

}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface RunnableMethods {

  RunnableMethod[] value();
}
