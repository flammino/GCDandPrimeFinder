import javafx.scene.control.TextArea;

/**
 * Created by Flammino on 4/4/2017.
 * Finds all prime factors of an integer
 * Based on sieve of Eratosthenes
 */
public class SOE {
    public void findPrimes(TextArea a, int n) {
        boolean[] primes = new boolean[n + 1]; // Create sieve for primes

        int l = primes.length;
        int count = 0;
        String r = "";
        String r2;
        final int NUM_PER_LINE = 5;
        // Start all values in sieve as true
        for (int i = 0; i < l; i++){
            primes[i] = true;
        }

        for (int k = 2; k < n / k; k++){
            if(primes[k]){
                for (int i = k; i<= n / k; i++){
                    primes[k * i] = false; // Any number that's a product of two integers is not prime
                }
            }
        }

        for (int i = 2; i < l; i++){ // Fills string with primes in rows of 10
            if (primes[i]){
                count++;
                if(count % NUM_PER_LINE == 0){
                    r += i + "\n";
                }
                else{
                    r += String.format("%d\t\t",i); // Two tabs needed to prevent a mess from happening with large numbers
                }
            }
        }
        r2 = String.format("There are %d primes less than or equal to %d\n", count, n );
        a.appendText(r2);
        a.appendText(r);
    }
}
