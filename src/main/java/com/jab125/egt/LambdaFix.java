package com.jab125.egt;

public class LambdaFix<T> {
    private T object;
    public LambdaFix(T object) {
        this.object = object;
    }

    public void set(T object) {
        this.object = object;
    }

    public T get() {
        return this.object;
    }

    @Override
    public String toString() {
        return "LambdaFix{" +
                "object=" + object +
                '}';
    }

    public String objectToString() {
        return object.toString();
    }
}
