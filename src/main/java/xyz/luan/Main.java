package xyz.luan;

import java.util.Arrays;

public class Main {

    private static final int S = 0, I = 1, R = 2;
    private static final double beta = 0.5d, mu = 0.1d, gamma = 0.1d;

    private static final Fn[] FS;

    static {
        FS = new Fn[3];
        FS[S] = ys -> -beta * ys.get(I) * ys.get(S) + mu * ys.get(R);
        FS[I] = ys -> beta * ys.get(I) * ys.get(S) - gamma * ys.get(I);
        FS[R] = ys -> gamma * ys.get(I) - mu * ys.get(R);
    }

    private double ti, tf;
    private double h;

    public static void main(String[] args) {
        new Main().run();
    }

    public Main() {
        this.h = 0.5;
    }

    public void run() {
        // Vector u = U0;
    }

    public Vector next(double t, Vector u) {
        return u.plus(f(t, u).times(h));
    }

    public Vector f(double t, Vector u) {
        return new Vector(Arrays.stream(FS).mapToDouble(fn -> fn.apply(u)).toArray());
    }
}
