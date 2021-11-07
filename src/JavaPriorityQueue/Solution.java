package JavaPriorityQueue;

import java.util.*;

class Student implements Comparable<Student> {

    private final int id;
    private final String name;
    private final double cgpa;

    Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    double getCGPA() {
        return cgpa;
    }

    @Override
    public int compareTo(Student o) {
        if (Double.compare(cgpa, o.cgpa) == 0) {
            if (name.compareTo(o.name) == 0) {
                return Integer.compare(id, o.id);
            } else {
                return name.compareTo(o.name);
            }
        } else {
            return -Double.compare(cgpa, o.cgpa);
        }
    }
}

class Priorities {
    public List<Student> getStudents(List<String> events) {
        PriorityQueue<Student> pq = new PriorityQueue<Student>();
        for (String event : events) {
            String[] command = event.split("\s+", 2);
            if (command[0].equals("ENTER")) {
                String[] rawStudent = command[1].split("\s+");
                pq.add(new Student(Integer.parseInt(rawStudent[2]),
                        rawStudent[0],
                        Double.parseDouble(rawStudent[1])));
            } else if (command[0].equals("SERVED")) {
                pq.poll();
            }
        }
        ArrayList<Student> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }
        return result;
    }
}

public class Solution {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();

    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st : students) {
                System.out.println(st.getName());
            }
        }
    }
}
