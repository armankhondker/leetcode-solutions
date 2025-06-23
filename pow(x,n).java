Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25

Note:
-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]


BRUTE FORCE 

TC: O(N)
SC: O(1)
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        for (long i = 0; i < N; i++)
            ans = ans * x;
        return ans;
    }
};

Binary Exponentation (AKA halfing the result and using that)
Note, 2^10 = 2^5 * 2^5. We have two edge cases to handle for even and odd
      2^5 = 2^2 * 2^2 *(2)

class Solution {
    public double myPow(double x, int n) {
        if(n == 0) return 1.0;
        if(n < 0){
            x = 1/x;
            n = -n; 
        }
        return fastPow(x, n);
    }
    public double fastPow(double x, int n){
        if(n == 0) return 1.0;
        double half = fastPow(x, n/2); 
        if(n%2 == 0){
            return half * half;
        } else {
            return half * half * x;
        }
    }
}

//Iterative approach, BEST SOLUTION 
//Use idea that X^n =  (X^2)^n/2  (since the multiplications cancel)

TC: O(LOGN)
SC: O(1)
class Solution {
    private double binaryExp(double x, long n) {
        if (n == 0) {
            return 1;
        }

        // Handle case where, n < 0.
        if (n < 0) {
            n = -1 * n;
            x = 1.0 / x;
        }

        // Perform Binary Exponentiation.
        double result = 1;
        while (n != 0) {
            // If 'n' is odd we multiply result with 'x' and reduce 'n' by '1'.
            if (n % 2 == 1) {
                result = result * x;
                n -= 1;
            }
            // We square 'x' and reduce 'n' by half, x^n => (x^2)^(n/2).
            x = x * x;
            n = n / 2;
        }
        return result;
    }

    public double myPow(double x, int n) {
        return binaryExp(x, (long) n);
    }
}