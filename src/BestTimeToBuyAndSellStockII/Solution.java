package BestTimeToBuyAndSellStockII;

public class Solution {
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int lastProfit = 0;
        int totalProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (min > prices[i]) {
                totalProfit += lastProfit;
                lastProfit = 0;
                min = prices[i];
            } else {
                int currentProfit = prices[i] - min;
                System.out.println("current: " + currentProfit);
                if (currentProfit > lastProfit) {
                    lastProfit = currentProfit;
                } else if (currentProfit < lastProfit) {
                    totalProfit += lastProfit;
                    lastProfit = 0;
                    min = prices[i];
                }
            }
            System.out.println("min  : " + min);
            System.out.println("last : " + lastProfit);
            System.out.println("total: " + totalProfit);
        }
        return totalProfit + lastProfit;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.maxProfit(new int[] {2,1,2,0,1}));
    }
}
