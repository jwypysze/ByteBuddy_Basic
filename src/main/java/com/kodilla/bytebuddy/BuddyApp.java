package com.kodilla.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.InvocationTargetException;


public class BuddyApp {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {

        //tworzymy obiekt klasy ByteBuddy, który pełni funkcję buildera naszego typu
        //metoda subclass informuje builder o tym, ze klasa ma powstac na bazie klasy Book
        //mozna tez stworzyc typ od zera

        //method + intercept = zmieniamy zachowanie metody toString z klasy Book (odziedziczona z Object)

        Class<?> dynamicBookClass = new ByteBuddy()
                .subclass(Book.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello my Buddy!"))
                .make()
                .load(Book.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        Class[] parameterTypes = {String.class, String.class, int.class};
        System.out.println
                (dynamicBookClass.getDeclaredConstructor(parameterTypes).newInstance("title", "author", 2010));
    }

}
