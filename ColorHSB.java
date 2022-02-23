public class ColorHSB {
    private final int h0;
    private final int s0;
    private final int b0;

    // Creates a color with hue h, saturation s, and brightness b.
    public ColorHSB(int h, int s, int b) {
        if (h < 0 || h > 359 || s < 0 || s > 100 || b < 0 || b > 100)
            throw new IllegalArgumentException("Out of range");
        b0 = b;
        s0 = s;
        h0 = h;
    }

    // Returns a string representation of this color, using the format (h, s, b).
    public String toString() {
        return "(" + h0 + ", " + s0 + "," + b0 + ")";
    }

    // Is this color a shade of gray?
    public boolean isGrayscale() {
        if (b0 == 0 || s0 == 0) return true;
        return false;
    }

    // Returns the squared distance between the two colors.
    public int distanceSquaredTo(ColorHSB that) {
        if (that == null) throw new IllegalArgumentException("Argument is null");
        return Math.min((h0 - that.h0) * (h0 - that.h0),
                        (360 - Math.abs(h0 - that.h0)) * (360 - Math.abs(h0 - that.h0)))
                + (s0 - that.s0) * (
                s0 - that.s0) + (b0 -
                that.b0) * (b0 -
                that.b0);
    }

    // Sample client (see below).
    public static void main(String[] args) {
        int h = Integer.parseInt(args[0]);
        int s = Integer.parseInt(args[1]);
        int b = Integer.parseInt(args[2]);
        ColorHSB first = new ColorHSB(h, s, b);
        int resd = Integer.MAX_VALUE;
        ColorHSB rescol = new ColorHSB(0, 0, 0);
        String resname = "";
        while (!StdIn.isEmpty()) {
            String name = StdIn.readString();
            int h1 = StdIn.readInt();
            int s1 = StdIn.readInt();
            int b1 = StdIn.readInt();
            ColorHSB second = new ColorHSB(h1, s1, b1);
            if (first.distanceSquaredTo(second) < resd) {
                resd = first.distanceSquaredTo(second);
                resname = name;
                rescol = second;
            }
        }
        StdOut.printf("%s (%d, %d, %d)", resname, rescol.h0, rescol.s0, rescol.b0);
    }

}
