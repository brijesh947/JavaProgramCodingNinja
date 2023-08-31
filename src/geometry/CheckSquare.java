package geometry;


import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;


/*
 * You are given four points on a two-dimensional coordinate system.
 * Can you check if those four points make a square?
 * Example:
 * Let the input be [1,0,2,1] and [0,1,1,2].
 * So, the coordinates of the four points be [ {1, 0}, {0, 1}, {2, 1}, {1, 2} ]
 * example

 * From the above image, we can see that it is a square. Thus, the output will be ‘Yes’.
 * Input format:
 * The first line of input contains an integer ‘T’ denoting the number of test cases.

 * The first line of each test case contains four space-separated integers representing x-coordinates of the four points.

 * The second line of each test case contains four space-separated integers representing y-coordinates of the four points.
 * Output format :
 * For each test case, print ‘Yes’ if four points make a square otherwise print ‘No’.
 * Note:
 * Don’t print anything, just return True if four points make a square otherwise return False.
 * Constraints:
 * 1 <= T <= 10^4
 * -10^9 <= xi, yi <= 10^9

 * Time limit: 1 sec
 * */

class SquareHelper {

    public static boolean isSquare(List<Long> x, List<Long> y) {

        Object[] xCordinate = x.toArray();
        Object[] yCordinate = y.toArray();
        /*
         * I have generated all possible combination of co-ordinates and
         * check whether any shape is square or not. and that's it.
         * */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {

                        if (isValid(i, j, k, l)) {
                            Point[] shape = new Point[4];
                            shape[0] = new Point((Long) xCordinate[i], (Long) yCordinate[i]);
                            shape[1] = new Point((Long) xCordinate[j], (Long) yCordinate[j]);
                            shape[2] = new Point((Long) xCordinate[k], (Long) yCordinate[k]);
                            shape[3] = new Point((Long) xCordinate[l], (Long) yCordinate[l]);
                            if (isSquarePossible(shape))
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean isSquarePossible(Point[] shape) {

        Point prevPoint = shape[0];
        for (int i = 1; i < 4; i++) {
            long distance1 = getDistance(prevPoint, shape[i]);
            Point slope1 = getSlope(prevPoint, shape[i]);
            long distance2 = getDistance(shape[i], shape[(i + 1) > 3 ? 0 : i + 1]);
            Point slope2 = getSlope(shape[i], shape[(i + 1) > 3 ? 0 : i + 1]);
            long ans = ((slope1.y) * (slope2.y) + ((slope1.x) * (slope2.x)));
            if (distance2 != distance1 || ans != 0 || (distance1 == 0 && distance2 == 0))
                return false;
            prevPoint = shape[i];
        }
        return true;

    }

    private static boolean isValid(int i, int j, int k, int l) {
        return i != j && i != k && i != l && j != k && j != l && k != l;
    }


    private static long getDistance(Object point, Object point1) {
        long y = ((Point) point1).y - ((Point) point).y;
        long x = ((Point) point1).x - ((Point) point).x;
        return ((y * y) + (x * x));
    }

    private static Point getSlope(Object point, Object point1) {

        long y = ((Point) point1).y - ((Point) point).y;
        long x = ((Point) point1).x - ((Point) point).x;
        Point slope = new Point(x, y);

        return slope;

    }

    private static class Point implements Comparable<Point> {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point secondPoint) {
            if (secondPoint.x == x) {
                return (y > secondPoint.y) ? 1 : -1;
            }
            return x > secondPoint.x ? 1 : -1;


        }
    }


}


public class CheckSquare {

    private static int t;
    private static List<ArrayList<Long>> arr1;
    private static List<ArrayList<Long>> arr2;


    private static void takeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine().split(" ")[0]);
        arr1 = new ArrayList<ArrayList<Long>>(t);
        arr2 = new ArrayList<ArrayList<Long>>(t);
        String[] data;
        for (int i = 0; i < t; i++) {
            arr1.add(new ArrayList<Long>(4));

            data = br.readLine().split(" ");
            for (int k = 0; k < 4; k++) {
                arr1.get(i).add(Long.parseLong(data[k]));
            }

            arr2.add(new ArrayList<Long>(4));
            data = br.readLine().split(" ");
            for (int k = 0; k < 4; k++) {
                arr2.get(i).add(Long.parseLong(data[k]));
            }

        }
    }

    public static void main(String[] args) throws IOException {

        StringBuffer sb = new StringBuffer();
        takeInput();

        for (int i = 0; i < t; i++) {

            boolean ans = SquareHelper.isSquare(arr1.get(i), arr2.get(i));

            if (ans) {
                sb.append("Yes");
            } else {
                sb.append("No");
            }

            sb.append("\n");

        }


        System.out.println(sb);
    }
}
