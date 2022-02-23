public class Clock {
    private int h0;
    private int m0;


    // Creates a clock whose initial time is h hours and m minutes.
    public Clock(int h, int m) {
        int HOURS_PER_DAY = 24;
        int MINUTES_PER_HOUR = 60;
        if (h < 0 || h >= HOURS_PER_DAY || m < 0 || m >= MINUTES_PER_HOUR)
            throw new IllegalArgumentException("Out of range");
        h0 = h;
        m0 = m;
    }

    // Creates a clock whose initial time is specified as a string, using the format HH:MM.
    public Clock(String s) {
        int HOURS_PER_DAY = 24;
        int MINUTES_PER_HOUR = 60;
        if (s.length() != 5) throw new IllegalArgumentException("Illegal input");
        char c = s.charAt(2);
        if (c != ':') throw new IllegalArgumentException("Illegal input");

        String hs = s.substring(0, 2);
        String ms = s.substring(3, 5);
        try {
            Integer.parseInt(hs);
            Integer.parseInt(ms);
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Illegal input");
        }
        int h = Integer.parseInt(hs);
        int m = Integer.parseInt(ms);
        if (h < 0 || h >= HOURS_PER_DAY || m < 0 || m >= MINUTES_PER_HOUR)
            throw new IllegalArgumentException("Out of range");
        h0 = h;
        m0 = m;
    }

    // Returns a string representation of this clock, using the format HH:MM.
    public String toString() {
        String hh = Integer.toString(h0);
        if (hh.length() < 2) hh = 0 + hh;
        String mm = Integer.toString(m0);
        if (mm.length() < 2) mm = 0 + mm;
        return hh + ":" + mm;
    }

    // Is the time on this clock earlier than the time on that one?
    public boolean isEarlierThan(Clock that) {
        if (h0 < that.h0 || (h0 == that.h0 && m0 < that.m0)) return true;
        return false;
    }

    // Adds 1 minute to the time on this clock.
    public void tic() {
        int HOURS_PER_DAY = 24;
        int MINUTES_PER_HOUR = 60;
        m0++;
        if (m0 == MINUTES_PER_HOUR) {
            h0++;
            m0 = 0;
        }
        if (h0 == HOURS_PER_DAY) {
            h0 = 0;
        }

    }

    // Adds Δ minutes to the time on this clock.
    public void toc(int delta) {
        int HOURS_PER_DAY = 24;
        int MINUTES_PER_HOUR = 60;
        if (delta < 0) throw new IllegalArgumentException("Δ is negative");
        h0 += delta / MINUTES_PER_HOUR;
        m0 += delta % MINUTES_PER_HOUR;
        h0 = h0 % HOURS_PER_DAY;
        if (m0 >= MINUTES_PER_HOUR) {
            h0++;
            m0 = m0 - MINUTES_PER_HOUR;
        }
        if (h0 == HOURS_PER_DAY) {
            h0 = 0;
        }

    }

    // Test client (see below).
    public static void main(String[] args) {
        Clock first = new Clock("HH:MM");
        Clock second = new Clock(8, 54);
        System.out.println(first);
        System.out.println(first.isEarlierThan(second));
        first.tic();
        second.toc(6);


    }
}
