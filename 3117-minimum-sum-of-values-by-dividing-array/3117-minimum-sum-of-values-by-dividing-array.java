import java.util.HashMap;
import java.util.Map;

class Solution {
    private int[] nums;
    private int[] andValues;
    private int n, m;
    private Map<Long, Integer> memo;
    private static final int INF = 1_000_000_000;

    public int minimumValueSum(int[] nums, int[] andValues) {
        this.nums = nums;
        this.andValues = andValues;
        this.n = nums.length;
        this.m = andValues.length;
        this.memo = new HashMap<>();

        int result = dp(0, 0, -1);
        return result >= INF ? -1 : result;
    }

    private int dp(int i, int j, int currentAnd) {
        
        if (j == m) {
            return i == n ? 0 : INF;
        }
        
        if (i == n) {
            return INF;
        }

    
        long state = ((long) i << 40) | ((long) j << 32) | (currentAnd & 0xFFFFFFFFL);
        if (memo.containsKey(state)) {
            return memo.get(state);
        }

        
        
        int nextAnd = (currentAnd == -1) ? nums[i] : (currentAnd & nums[i]);

        
        int ans = dp(i + 1, j, nextAnd);

        
        if (nextAnd == andValues[j]) {
            int subRes = dp(i + 1, j + 1, -1);
            if (subRes != INF) {
                ans = Math.min(ans, nums[i] + subRes);
            }
        }

        memo.put(state, ans);
        return ans;
    }
}