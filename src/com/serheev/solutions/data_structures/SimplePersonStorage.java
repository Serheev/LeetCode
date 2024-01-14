package com.serheev.solutions.data_structures;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class SimplePersonStorage {
    public static void main(String[] args) {
        NoThreadSafePersonStorage noThreadSafeStorage = new NoThreadSafePersonStorage();
        noThreadSafeStorage.add(new Person("John", "Doe"));
        noThreadSafeStorage.add(new Person("John", "Doe"));
        noThreadSafeStorage.add(new Person("Jane", "Smith"));
        noThreadSafeStorage.add(new Person("Bob", "Doe"));
        noThreadSafeStorage.add(new Person("Alice", "Johnson"));

        noThreadSafeStorage.remove(new Person("John", "Doe"));
        noThreadSafeStorage.remove(new Person("Bob", "Doe"));
        noThreadSafeStorage.remove(new Person("John", "Doe"));
        noThreadSafeStorage.add(new Person("Bob", "Doe"));
        noThreadSafeStorage.add(new Person("John", "Doe"));
        noThreadSafeStorage.add(new Person("John", "Doe"));

        System.out.println("NO Threadsafe storage");
        System.out.println("------------------");
        noThreadSafeStorage.print(noThreadSafeStorage.getAll());

        ThreadSafePersonStorage threadSafeStorage = ThreadSafePersonStorage.getInstance();
        CountDownLatch latch = new CountDownLatch(2);

        Thread addThread1 = new Thread(() -> {
            threadSafeStorage.add(new Person("John", "Doe"));
            threadSafeStorage.add(new Person("Jane", "Doe"));
            latch.countDown();
        });

        Thread addThread2 = new Thread(() -> {
            threadSafeStorage.add(new Person("Jim", "Beam"));
            threadSafeStorage.add(new Person("Jill", "Hill"));
            latch.countDown();
        });

        Thread removeThread1 = new Thread(() -> {
            threadSafeStorage.remove(new Person("Jane", "Doe"));
        });

        Thread removeThread2 = new Thread(() -> {
            threadSafeStorage.remove(new Person("Jim", "Beam"));
            threadSafeStorage.remove(new Person("Jill", "Hill"));
        });

        addThread1.start();
        addThread2.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        removeThread1.start();
        removeThread2.start();

        try {
            addThread1.join();
            addThread2.join();
            removeThread1.join();
            removeThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Threadsafe storage");
        System.out.println("------------------");

        threadSafeStorage.print(threadSafeStorage.getAll());
    }

}

class NoThreadSafePersonStorage {
    private Map<String, Set<Person>> storage = new HashMap<>();

    public void add(Person person) {
        storage.compute(person.getLastName(), (key, list) -> {
            if (list == null) {
                list = new HashSet<>();
            }
            list.add(person);
            return list;
        });
    }

    public void remove(Person person) {
        Set<Person> list = storage.get(person.getLastName());
        if (list != null) {
            list.remove(person);
            if (list.isEmpty()) {
                storage.remove(person.getLastName());
            }
        }
    }

    public Map<String, Set<Person>> getAll() {
        return Collections.unmodifiableMap(storage);
    }

    public void print(Map<String, Set<Person>> map) {
        for (Map.Entry<String, Set<Person>> entry : map.entrySet()) {
            System.out.println("Last Name: " + entry.getKey());
            System.out.println("People: " + entry.getValue());
            System.out.println("--------------------");
        }
    }
}

class ThreadSafePersonStorage {
    private static volatile ThreadSafePersonStorage instance;
    private final Map<String, Set<Person>> storage = new ConcurrentHashMap<>();

    private ThreadSafePersonStorage() {
    }

    public static ThreadSafePersonStorage getInstance() {
        if (instance == null) {
            synchronized (ThreadSafePersonStorage.class) {
                if (instance == null) {
                    instance = new ThreadSafePersonStorage();
                }
            }
        }
        return instance;
    }

    public void add(Person person) {
        storage.compute(person.getLastName(), (key, list) -> {
            if (list == null) {
                list = ConcurrentHashMap.newKeySet();
            }
            list.add(person);
            return list;
        });
    }

    public void remove(Person person) {
        Set<Person> list = storage.get(person.getLastName());
        if (list != null) {
            list.remove(person);
            if (list.isEmpty()) {
                storage.remove(person.getLastName());
            }
        }
    }

    public Map<String, Set<Person>> getAll() {
        return Collections.unmodifiableMap(storage);
    }

    public void print(Map<String, Set<Person>> map) {
        for (Map.Entry<String, Set<Person>> entry : map.entrySet()) {
            System.out.println("Last Name: " + entry.getKey());
            System.out.println("People: " + entry.getValue());
            System.out.println("--------------------");
        }
    }
}


class Person {
    private String firstName;
    private String lastName;

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
