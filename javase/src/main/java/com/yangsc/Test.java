package com.yangsc;

import java.util.*;

/**
 * <p>Description: Test</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/7/2 20:04
 **/
public class Test {

    private static boolean fun(String a, String b, String c) {

        Set<String> setA = new HashSet(8);
        List<String> listA = new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            char charAt = a.charAt(i);
            setA.add(charAt + "");
            listA.add(charAt + "");
        }
        Set<String> setB = new HashSet(8);
        List<String> listB = new ArrayList<>();

        for (int i = 0; i < b.length(); i++) {
            char charAt = b.charAt(i);
            listB.add(charAt + "");
            setB.add(charAt + "");
        }

        Integer indexA = 0;
        Integer indexB = 0;
        HashMap<String, Integer> charIndexA = new HashMap<>(8);
        HashMap<String, Integer> charIndexB = new HashMap<>(8);

        for (int i = 0; i < c.length(); i++) {
            String charAt = c.charAt(i) + "";
            if (!setA.contains(charAt) && !setB.contains(charAt)) {
                return false;
            }

            if (setA.contains(charAt) && setB.contains(charAt)) {
                for (int j = 0; j < listA.size(); j++) {
                    String ca = listA.get(j);
                    if (ca.equals(charAt)) {
                        charIndexA.put(charAt, j);
                        if (charIndexB.containsKey(charAt)) {
                            break;
                        }
                        if (indexA > j) {
                            return false;
                        }
                        break;
                    }
                }

                for (int j = 0; j < listB.size(); j++) {
                    String cb = listB.get(j);
                    if (cb.equals(charAt)) {
                        charIndexB.put(charAt, j);
                        if (charIndexA.containsKey(charAt)) {
                            break;
                        }
                        if (indexB > j) {
                            return false;
                        }
                        break;
                    }
                }
            }

            if (setA.contains(charAt)) {
                for (int j = 0; j < listA.size(); j++) {
                    String ca = listA.get(j);
                    if (ca.equals(charAt)) {
                        if (charIndexB.containsKey(charAt)) {
                            break;
                        }
                        if (indexA > j) {
                            return false;
                        }
                        indexA = j;
                        break;
                    }
                }
            }

            if (setB.contains(charAt)) {
                for (int j = 0; j < listB.size(); j++) {
                    String cb = listB.get(j);
                    if (cb.equals(charAt)) {
                        if (charIndexA.containsKey(charAt)) {
                            break;
                        }
                        if (indexB > j) {
                            return false;
                        }
                        indexB = j;
                        break;
                    }
                }
            }
        }
        return true;
    }


    static class Dfs {
        private int aLength, bLength, cLength;
        private boolean flag;
        private int[][] visble;
        private char aChar[], bChar[], cChar[];

        Dfs(String a, String b, String c) {
            this.aLength = a.length();
            this.bLength = b.length();
            this.cLength = c.length();
            this.visble = new int[100][100];
            ;
            this.aChar = a.toCharArray();
            this.bChar = b.toCharArray();
            this.cChar = c.toCharArray();
        }

        public void check(int x, int y, int z) {
            if (z == cLength) {
                flag = true;
                return;
            }

            if (flag) {
                return;
            }

            if (visble[x][y] == 1) {
                return;
            }

            visble[x][y] = 1;

            if (x < aLength && aChar[x] == cChar[z]) {
                check(x + 1, y, z + 1);
            }
            if (y < bLength && bChar[y] == cChar[z]) {
                check(x, y + 1, z + 1);
            }
        }
    }


    /**
     * 分析： 首先给出详细分析如何得到dp状态转移方程，然后就是详细的实现过程(很多地方结合图形有利于理解分析)
     *
     * 首先，我们可以知道 C 的最后一个字母，要不是由 A的最后一个组成，要不就是B的最后一个组成，
     * 那么对于C的任意一位(这里说成)第 i + j 位要么就是由A的 i 位 或者 B 的 j 位组成，
     * 那么这个先给出 一个数组 dp[i][j] (它的行是由A的所有字母按顺序组成，列是B组成，比如题目的案例可以有以下图:
     * 它的值为 1 的时候表示 C 的 第 i + j 位 可以由A 的i 或者 B 的j 组成，为0 则表示不可以，
     * 那么我们对于整个的是否可以组成C我们可以用两个循环列举去组成的所有情况：for循环如下
     *
     * for(i=1;i<=a;i++)//a表示A的长度
     *       for(j=1;j<=b;j++)//b表示B的长度
     *
     * 那么循环的过程就是求解dp这个二维方程组的过程，那么dp[i][j]的取值是怎么决定的呢？那么这里就涉及到 状态转移方程 dp[i][j] = ？
     * 那么这里可以得到状态转移方程为：dp[i][j] = { (A[i] == c[i+j] && dp[i-1][j] == 1) || (B[j] == c[i+j] && dp[i][j-1]),
     * 那么为什么是这样转移的呢? 我们考虑dp[i][j] 的值得时候为 只是考虑如下两种情况（红色字标出的）：
     */
    public static boolean check(String a, String b, String c) {
        int[][] dp = new int[100][100];
        dp[0][0] = 1;
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i > 0 && dp[i - 1][j] == 1 && c.charAt(i + j - 1) == a.charAt(i - 1)) {
                    dp[i][j] = 1;
                }
                if (j > 0 && dp[i][j - 1] == 1 && c.charAt(i + j - 1) == b.charAt(j - 1)) {
                    dp[i][j] = 1;
                }
            }
        }

        return dp[a.length()][b.length()] == 1;
    }


    public static void main(String[] args) {

        // 评测题目: 给定三个字符串A，B，C；判断C能否由AB中的字符组成，
        // 同时这个组合后的字符顺序必须是A，B中原来的顺序，不能逆序；
        // 例如：A：long，B：short；如果C为lshoonrtg 或者是lonshortg，就符合题意；
        // 如果C为hsolongrt，就不符合题意

        // first fun
        System.out.println(check("long", "short", "lshoonrtg"));
        System.out.println(check("long", "short", "lonshortg"));
        System.out.println(check("long", "short", "hsolongrt"));


        System.out.println("----");

        // second fun
        Dfs dfs = new Dfs("long", "short", "lshoonrtg");
        dfs.check(0, 0, 0);
        System.out.println(dfs.flag);

        Dfs dfs2 = new Dfs("long", "short", "lonshortg");
        dfs2.check(0, 0, 0);
        System.out.println(dfs2.flag);

        Dfs dfs3 = new Dfs("long", "short", "hsolongrt");
        dfs3.check(0, 0, 0);
        System.out.println(dfs3.flag);
        Map<Integer,Integer> map = new HashMap<>();
    }

}
