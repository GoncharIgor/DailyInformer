package utils;

import java.io.Serializable;

/**
 * Created by i.gonchar on 6/16/2016.
 */
public class Pair<F, S> implements Serializable {
    private F first = null;
    private S second = null;

    public Pair(F key, S value) {
        this.first = key;
        this.second = value;
    }

    public F getFirst() {
        return this.first;
    }

    public S getSecond() {
        return this.second;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Pair pair = (Pair) o;
            if (this.first != null) {
                if (!this.first.equals(pair.first)) {
                    return false;
                }
            } else if (pair.first != null) {
                return false;
            }

            if (this.second != null) {
                if (this.second.equals(pair.second)) {
                    return true;
                }
            } else if (pair.second == null) {
                return true;
            }

            return false;
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.first != null ? this.first.hashCode() : 0;
        result = 31 * result + (this.second != null ? this.second.hashCode() : 0);
        return result;
    }
}