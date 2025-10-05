package ua.opnu;

public class TestPersonStudentLecturer {
    public static void main(String[] args) {
        // --- Створюємо об'єкти з використанням висхідного перетворення ---
        Person p1 = new Person("Шевченко", "Оксана", 30);
        Person s1 = new Student("Ревуцька", "Олександра", 17, "КН-245", "STU9103053");
        Person s2 = new Student("Іваненко", "Марія", 19, "КН-245", "STU9103054");
        Person l1 = new Lecturer("Коваленко", "Петро", 45, "Інформатика", 18500.0);
        Person l2 = new Lecturer("Сидоренко", "Наталія", 50, "Математика", 21000.0);

        // --- Масив, який містить об'єкти різних типів ---
        Person[] people = { p1, s1, s2, l1, l2 };

        // --- Виводимо інформацію про кожен об'єкт ---
        System.out.println("=== Інформація про об'єкти класів Person, Student, Lecturer ===");
        for (Person p : people) {
            System.out.println(p.toString());
        }
    }
}
