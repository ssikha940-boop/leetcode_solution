class Solution {
    public String maxSumOfSquares(int num, long sum) {
        // Quick check if the sum is out of the possible range
        if (sum < 1 || sum > (long) num * 9) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        long remainingSum = sum;

        for (int i = 0; i < num; i++) {
            int remainingPositions = num - 1 - i;
            
            // Try digits from 9 down to 0
            for (int digit = 9; digit >= 0; digit--) {
                // If this is the first digit, it cannot be 0
                if (i == 0 && digit == 0 && num > 1) {
                    continue;
                }
                
                // Check if choosing this 'digit' leaves a valid remaining sum
                long minPossibleSumAfter = Math.max(0L, remainingSum - digit - (long) remainingPositions * 9);
                long maxPossibleSumAfter = remainingSum - digit;
                
                if (minPossibleSumAfter <= 0 && maxPossibleSumAfter >= 0) {
                    sb.append(digit);
                    remainingSum -= digit;
                    break;
                }
            }
        }

        return remainingSum == 0 ? sb.toString() : "";
    }
}