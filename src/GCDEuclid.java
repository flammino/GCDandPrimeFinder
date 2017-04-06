/**
 * Created by Flammino on 4/4/2017.
 * Finds greatest common denominator using Euclid's recursive method
 */
public class GCDEuclid {
    public int gcd(int m, int n){
        if (m % n == 0){
            return n;
        }
        else{
            return gcd(n, m % n);
        }
    }
}
