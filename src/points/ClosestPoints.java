package points;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClosestPoints {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            points.add(new Point(scanner.nextLong(), scanner.nextLong()));
        }

        System.out.printf("%.9f", new ClosestPoints().divideAndConquer(points));
    }

    private double bruteForce(List<Point> pointsInArea) {
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < pointsInArea.size(); i++) {
            for (int j = i + 1; j < pointsInArea.size(); j++) {
                if (pointsInArea.get(i).distance(pointsInArea.get(j)) < minDistance) {
                    minDistance = pointsInArea.get(i).distance(pointsInArea.get(j));
                }
            }
        }
        return minDistance;
    }


    public double divideAndConquer(List<Point> points) {
        List<Point> pointsSortedByX = new ArrayList<>(points);
        pointsSortedByX.sort(Comparator.comparingDouble(Point::getX));
        List<Point> pointsSortedByY = new ArrayList<>(points);
        pointsSortedByY.sort(Comparator.comparingDouble(Point::getY));
        return divideAndConquer(pointsSortedByX, pointsSortedByY);
    }

    private double divideAndConquer(List<Point> pointsSortedByX, List<Point> pointsSortedByY) {
        int numPoints = pointsSortedByX.size();
        if (numPoints <= 3)
            return bruteForce(pointsSortedByX);

        int dividingIndex = numPoints >>> 1;
        List<Point> leftOfCenter = pointsSortedByX.subList(0, dividingIndex);
        List<Point> rightOfCenter = pointsSortedByX.subList(dividingIndex, numPoints);

        double closestDistanceLeft = divideAndConquer(leftOfCenter, leftOfCenter.stream()
                .sorted(Comparator.comparingDouble(Point::getY))
                .collect(Collectors.toList()));

        double closestDistanceRight = divideAndConquer(rightOfCenter, rightOfCenter.stream()
                .sorted(Comparator.comparingDouble(Point::getY))
                .collect(Collectors.toList()));

        List<Point> tempList = new ArrayList<>();
        double shortestDistance = Math.min(closestDistanceLeft, closestDistanceRight);
        double centerX = rightOfCenter.get(0).getX();
        for (Point point : pointsSortedByY)
            if (Math.abs(centerX - point.getX()) < shortestDistance)
                tempList.add(point);

        for (int i = 0; i < tempList.size() - 1; i++) {
            Point point1 = tempList.get(i);
            for (int j = i + 1; j < tempList.size(); j++) {
                Point point2 = tempList.get(j);
                if ((point2.getY() - point1.getY()) >= shortestDistance)
                    break;
                double distance = point1.distance(point2);
                if (distance < closestDistanceLeft) {
                    closestDistanceLeft = point1.distance(point2);
                    shortestDistance = distance;
                }
            }
        }
        return closestDistanceLeft;
    }


    public static class Point {
        private final long x;
        private final long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long getX() {
            return x;
        }

        public long getY() {
            return y;
        }

        public double distance(Point other) {
            return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        }
    }
}