package xyz.luan;

import java.util.Arrays;

import Jama.Matrix;

class Vector extends Matrix {
    private static final long serialVersionUID = -620593777634305565L;

    private Vector(double[][] values) {
        super(values);
    }

    public Vector(double... values) {
        super(new double[][] { values });
    }

    public double get(int i) {
        return get(0, i);
    }

    public Vector plus(Vector v) {
        return new Vector(super.plus(v).getArray());
    }

    @Override
    public Vector times(double t) {
        return new Vector(super.times(t).getArray());
    }

    @Override
    public String toString() {
        return Arrays.toString(getArray()[0]);
    }
}