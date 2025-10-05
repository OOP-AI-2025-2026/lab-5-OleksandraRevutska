package ua.opnu;

public class TestTimeSpan {
    public static void main(String[] args) {
        TimeSpan t1 = new TimeSpan();
        TimeSpan t2 = new TimeSpan(90);
        TimeSpan t3 = new TimeSpan(2, 45);
        TimeSpan t4 = new TimeSpan(t3);

        System.out.println("Початкові об'єкти:");
        System.out.println("t1 = " + t1);
        System.out.println("t2 = " + t2);
        System.out.println("t3 = " + t3);
        System.out.println("t4 = " + t4);

        t1.add(1, 20);
        t2.add(45);
        t3.add(t2);

        System.out.println("\nПісля додавання:");
        System.out.println("t1 = " + t1);
        System.out.println("t2 = " + t2);
        System.out.println("t3 = " + t3);

        t3.subtract(1, 30);
        t2.subtract(45);
        t1.subtract(t2);

        System.out.println("\nПісля віднімання:");
        System.out.println("t1 = " + t1);
        System.out.println("t2 = " + t2);
        System.out.println("t3 = " + t3);
    }
}
