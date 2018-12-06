package points;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ClosestPoints {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            points.add(new Point(scanner.nextLong(), scanner.nextLong()));
        }

        System.out.printf("%.9f", new ClosestPoints().run(points));
    }

    public double run(List<Point> points) {
        List<Point> pointsY = new ArrayList<>(points);
        points.sort(Comparator.comparingLong(Point::getX));
        return findClosestPoints(points, pointsY, points.size());
    }


    private double findClosestPoints(List<Point> sortedX, List<Point> pointsY, int numberOfPoints) {
        if (numberOfPoints <= 3) {
            return minDistanceArea(sortedX, Double.MAX_VALUE);
        }

        int middleIndex = numberOfPoints / 2;
        Point middlePoint = sortedX.get(middleIndex);

        List<Point> leftSubPointsY = new ArrayList<>();
        List<Point> leftSubPointsSortedX = new ArrayList<>();
        List<Point> rightSubPointsY = new ArrayList<>();
        List<Point> rightSubPointsSortedX = new ArrayList<>();

        //divide points for left and right subsets
        for (int i = 0; i < pointsY.size(); i++) {
            if (pointsY.get(i).getX() <= middlePoint.getX()) {
                leftSubPointsY.add(pointsY.get(i));
                leftSubPointsSortedX.add(sortedX.get(i));
            } else {
                rightSubPointsY.add(pointsY.get(i));
                rightSubPointsSortedX.add(sortedX.get(i));
            }
        }

        double leftClosest = findClosestPoints(leftSubPointsSortedX, leftSubPointsY, middleIndex);
        double rightClosest = findClosestPoints(rightSubPointsSortedX, rightSubPointsY, numberOfPoints - middleIndex);
        double delta = Math.min(leftClosest, rightClosest);

        List<Point> pointsInArea = new ArrayList<>();

        for (int i = 0; i < numberOfPoints; i++) {
            if (Math.abs(pointsY.get(i).getX() - middlePoint.getX()) < delta) {
                pointsInArea.add(pointsY.get(i));
            }
        }

        return Math.min(delta, minDistanceArea(pointsInArea, delta));
    }

    private double minDistanceArea(List<Point> pointsInArea, double delta) {
        double minDistance = delta;
        for (int i = 0; i < pointsInArea.size(); i++) {
            for (int j = i + 1; j < pointsInArea.size(); j++) {
                if (pointsInArea.get(i).distance(pointsInArea.get(j)) < delta) {
                    minDistance = pointsInArea.get(i).distance(pointsInArea.get(j));
                }
            }
        }
        return minDistance;
    }


    public static class Point {
        private long x;
        private long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public double distance(Point other) {
            return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        }

        public long getX() {
            return x;
        }

        public void setX(long x) {
            this.x = x;
        }

        public long getY() {
            return y;
        }

        public void setY(long y) {
            this.y = y;
        }
    }
}
