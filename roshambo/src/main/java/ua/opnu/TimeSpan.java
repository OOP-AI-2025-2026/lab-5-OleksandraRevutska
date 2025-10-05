package ua.opnu;

public class TimeSpan {
    private int hours;
    private int minutes;

    // --- Конструктори ---
    public TimeSpan() {
        this.hours = 0;
        this.minutes = 0;
    }

    public TimeSpan(int minutes) {
        if (minutes < 0) {
            this.hours = 0;
            this.minutes = 0;
        } else {
            this.hours = minutes / 60;
            this.minutes = minutes % 60;
        }
    }

    public TimeSpan(int hours, int minutes) {
        if (hours < 0 || minutes < 0) {
            this.hours = 0;
            this.minutes = 0;
        } else {
            this.hours = hours + minutes / 60;
            this.minutes = minutes % 60;
        }
    }

    public TimeSpan(TimeSpan other) {
        this.hours = other.hours;
        this.minutes = other.minutes;
    }

    // --- Гетери ---
    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getTotalMinutes() {
        return hours * 60 + minutes;
    }

    public double getTotalHours() {
        return hours + minutes / 60.0;
    }

    // --- Методи додавання (перевантаження) ---
    public void add(int hours, int minutes) {
        if (hours < 0 || minutes < 0) return;

        this.minutes += minutes;
        this.hours += hours + this.minutes / 60;
        this.minutes %= 60;
    }

    public void add(int minutes) {
        if (minutes < 0) return;
        add(0, minutes);
    }

    public void add(TimeSpan span) {
        add(span.getHours(), span.getMinutes());
    }

    // --- Методи віднімання (перевантаження) ---
    public void subtract(int hours, int minutes) {
        int total = getTotalMinutes() - (hours * 60 + minutes);
        if (total < 0) return;

        this.hours = total / 60;
        this.minutes = total % 60;
    }

    public void subtract(int minutes) {
        if (minutes < 0) return;
        subtract(0, minutes);
    }

    public void subtract(TimeSpan span) {
        subtract(span.getHours(), span.getMinutes());
    }

    // --- Вивід у зручному форматі ---
    @Override
    public String toString() {
        return hours + " год " + minutes + " хв";
    }
}
