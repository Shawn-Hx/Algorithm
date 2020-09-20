package cn.edu.nju.trick;

/**
 * 快速幂
 */
public class BinaryExponentiation {

    // compute a^n
    long pow(long a, long n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1)
                res *= a;
            a *= a;
            n >>= 1;
        }
        return res;
    }

    // compute a^n mod m
    long modPow(long a, long n, long m) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1)
                res = res * a % m;
            a = a * a % m;
            n >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        long a = 2;
        long n = 31;
        long m = (long)(1e6 + 9);
        System.out.println(new BinaryExponentiation().pow(a, n));
        System.out.println(new BinaryExponentiation().modPow(a, n, m));
    }

}
