package xyz.luan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static final int S = 0, I = 1, R = 2;
    private static final double beta = 0.001d, mu = 0, gamma = 0.012d;
    private static final Vector U0 = new Vector(120, 1, 0);

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
        new Main(0, 240, 0.25).run();
    }

    public Main(double ti, double tf, double h) {
        this.ti = ti;
        this.tf = tf;
        this.h = h;
    }

    public void run() {
        List<Vector> us = new ArrayList<>();
        int i = 0;
        double[] ts = new double[(int) Math.ceil((tf - ti) / h) + 2];
        us.add(U0);
        for (double t = ti; t <= tf; t += h) {
            ts[i++] = t;
            us.add(next(t, us.get(us.size() - 1)));
        }
        ts[i++] = tf;

        Chart.plot(us, ts);
        System.out.println(us);
    }

    public Vector next(double t, Vector u) {
        return u.plus(f(t, u).times(h));
    }

    public Vector f(double t, Vector u) {
        return new Vector(Arrays.stream(FS).mapToDouble(fn -> fn.apply(u)).toArray());
    }
}
