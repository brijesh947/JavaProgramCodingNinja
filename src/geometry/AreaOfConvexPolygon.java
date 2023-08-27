package geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * A convex polygon is a set of n vertices that are joined by n edges, such that no two edges intersect and all angles are less than 180 degrees.
 * We can represent a polygon by listing all the vertices, starting at one vertex and following the edges until that vertex is reached again.
 * Thus, element 0 in the array represents the first vertex. The first vertex is connected to the second vertex (element 1), the second vertex is connected to the third vertex (element 2) and so on.
 * The last element represents the last vertex, which is connected to the first vertex.
 * Given the vertices of a polygon, return its exact area.
 * Note: Get the integer part of the area. (It can be long). So get your answer in double, and typecast it into long.
 *
 * Input Format:
 * First line of input will contain T(number of test cases), each test case follows.
 * Line 1: Integer N denoting the number of points.
 * Next N lines will denote the N cordinates (x,y) in a anticlockwise order.
 *
 * Constraints:
 * 1 <= T <= 10^5
 * 1 <= N <= 50
 * 1 <= X <= Y <= 10^5
 * The given polygon is guranted to be convex.
 *
 * Output Format:
 * For each test case, print the area of polygon in new line.
 * */

public class AreaOfConvexPolygon {


    private static class Point {
        public double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Polygon {
        Point[] points;

        Polygon(int n) {
            points = new Point[n];
        }
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = Integer.parseInt(reader.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine().trim());
            Polygon polygon = new Polygon(n);
            for (int i = 0; i < n; i++) {
                String[] num = reader.readLine().split(" ");
                polygon.points[i] = new Point(Double.parseDouble(num[0]), Double.parseDouble(num[1]));
            }
            double area = getArea(polygon, n);
            System.out.print((long) area + "\n");
        }

    }

    private static double getArea(Polygon polygon, int n) {
        double area = 0;
        /*
         * considered first point as pivot point and after that taking next 2's cross product and make sum into total area
         * finally divide by 2 because at each point calculating the area of trapezium so we have to divide it by 2 get the area of triangle */
        for (int i = 1; i < n - 1; i++) {
            double x1 = polygon.points[i].x - polygon.points[0].x;
            double y1 = polygon.points[i].y - polygon.points[0].y;
            double x2 = polygon.points[i + 1].x - polygon.points[0].x;
            double y2 = polygon.points[i + 1].y - polygon.points[0].y;
            area += (x1 * y2 - x2 * y1);
        }
        return Math.abs(area) / 2;
    }
}
