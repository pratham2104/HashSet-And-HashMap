/*   HashSetAndHashMapTest.java
 *   Pratham Agarwal
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class HashSetAndHashMapTest {
    static class Person implements Serializable {
        private static final long serialVersionUID = 1L;
        int id;
        String name;
        int age;

        public Person(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person person = (Person) obj;
            return id == person.id;
        }
    }

    public static void main(String[] args) {
        int datasetSize = 100000;
        HashSet<Person> personSet = new HashSet<>();
        HashMap<Integer, Person> personMap = new HashMap<>();

        // Generate dataset
        for (int i = 0; i < datasetSize; i++) {
            personSet.add(new Person(i, "Person" + i, 20 + (i % 30)));
            personMap.put(i, new Person(i, "Person" + i, 20 + (i % 30)));
        }

        // Measure performance of HashSet
        System.out.println("Testing HashSet...");
        testSetPerformance(personSet);

        // Measure performance of HashMap
        System.out.println("\nTesting HashMap...");
        testMapPerformance(personMap);
    }

    private static void testSetPerformance(HashSet<Person> personSet) {
        Random random = new Random();
        int sampleId = random.nextInt(personSet.size());

        long start, end;

        // Add operation
        start = System.nanoTime();
        personSet.add(new Person(100001, "New Person", 25));
        end = System.nanoTime();
        System.out.println("Add: " + (end - start) + " ns");

        // Read operation
        start = System.nanoTime();
        boolean contains = personSet.contains(new Person(sampleId, "Person" + sampleId, 25));
        end = System.nanoTime();
        System.out.println("Read: " + (end - start) + " ns");

        // Delete operation
        start = System.nanoTime();
        personSet.remove(new Person(sampleId, "Person" + sampleId, 25));
        end = System.nanoTime();
        System.out.println("Delete: " + (end - start) + " ns");
    }

    private static void testMapPerformance(HashMap<Integer, Person> personMap) {
        Random random = new Random();
        int sampleKey = random.nextInt(personMap.size());

        long start, end;

        // Add operation
        start = System.nanoTime();
        personMap.put(100001, new Person(100001, "New Person", 25));
        end = System.nanoTime();
        System.out.println("Add: " + (end - start) + " ns");

        // Read operation
        start = System.nanoTime();
        Person person = personMap.get(sampleKey);
        end = System.nanoTime();
        System.out.println("Read: " + (end - start) + " ns");

        // Update operation
        start = System.nanoTime();
        personMap.put(sampleKey, new Person(sampleKey, "Updated Person", 30));
        end = System.nanoTime();
        System.out.println("Update: " + (end - start) + " ns");

        // Delete operation
        start = System.nanoTime();
        personMap.remove(sampleKey);
        end = System.nanoTime();
        System.out.println("Delete: " + (end - start) + " ns");
    }
}
