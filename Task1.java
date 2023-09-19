package seminar_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Task1 {


    class Point implements Comparable<Point>{
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Point(Point point) {
            this.row = point.row;
            this.col = point.col;
        }

        @Override
        public int compareTo(Point o) {
            if (this.row == o.row && this.col == o.col){
                return 0;
            } else {
                return 1;
            }
        }

        @Override
        public String toString() {
            return "Point[" +
                    "row=" + row +
                    ", col=" + col +
                    ']';
        }
    }

    private boolean containsPoint(ArrayList<Point> arrayList, Point point){
        boolean result = false;
        for (int i = 0; i < arrayList.size(); i++) {
            if (point.compareTo(arrayList.get(i)) == 0){
                result = true;
                break;
            }
        }
        return result;
    }


    public void searchWayOut(int row, int col, int r_in, int c_in, int r_out, int c_out){
        //создаём массив
        int[][] array = new int[row][col];

//        array[0][0] = 0; array[0][1] = 0; array[0][2] = 1; array[0][3] = 1;
//        array[1][0] = 1; array[1][1] = 0; array[1][2] = 1; array[1][3] = 0;
//        array[2][0] = 0; array[2][1] = 1; array[2][2] = 0; array[2][3] = 1;
//        array[3][0] = 1; array[3][1] = 0; array[3][2] = 0; array[3][3] = 0;

        Random rnd = new Random();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = rnd.nextInt(0, 2);
            }
        }

        // устанавливаем точки входа и выхода
        array[r_in][c_in] = 0;
        array[r_out][c_out] = 0;
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }


        // создаем список перекрёстков
        ArrayList<Point> arl = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == 0) {
                    arl.add(new Point(i,j));
                }
            }
        }

        ArrayList<Point> crossRoads = new ArrayList<>();
        int count = 0; int index = 0;
        for (int i = 0; i < arl.size(); i++) {
            for (int j = 0; j < arl.size(); j++) {
                count = Math.abs(arl.get(i).row - arl.get(j).row) +
                        Math.abs(arl.get(i).col - arl.get(j).col);
                if (count == 1) index++;
            }

            if (index > 2) crossRoads.add(new Point(arl.get(i).row, arl.get(i).col));
            index= 0;
        }


        // ищем путь из лабиринта
        Point curPos = new Point(r_in, c_in);
        Point inPoint = new Point(r_in, c_in);
        Point outPoint = new Point(r_out, c_out);
        boolean search_way = true;
        int counterOut = 0;
        ArrayList<Point> wayOut = new ArrayList<>();
        ArrayList<Point> oldPoint = new ArrayList<>();

        while(search_way) {
            if ( (curPos.col+1 < array[0].length)
                    && (array[curPos.row][curPos.col + 1] == 0)
                    && !(containsPoint(oldPoint, new Point(curPos.row, curPos.col + 1)))  ) {
                wayOut.add(new Point(curPos)); oldPoint.add(new Point(curPos));
                curPos.col += 1;
                if (curPos.compareTo(inPoint) == 0) {
                    counterOut++;
                    if (counterOut > 1) {
                        System.out.println("There is no way out! SORRY!");
                        search_way = false;
                    }
                }
                if (curPos.compareTo(outPoint) == 0) {
                    wayOut.add(new Point(curPos)); oldPoint.add(new Point(curPos));
                    System.out.println("FREEDOM!!!");
                    System.out.println(wayOut);
                    search_way = false;
                }
            }

            else if ( (curPos.row+1 < array.length)
                    &&  (array[curPos.row + 1][curPos.col] == 0)
                    &&  !(containsPoint(oldPoint,new Point(curPos.row+1, curPos.col))) ) {
                wayOut.add(new Point(curPos)); oldPoint.add(new Point(curPos));
                curPos.row += 1;
                if (curPos.compareTo(inPoint) == 0) {
                    counterOut++;
                    if (counterOut > 1) {
                        System.out.println("There is no way out! SORRY!");
                        search_way = false;
                    }
                }
                if (curPos.compareTo(outPoint) == 0) {
                    wayOut.add(new Point(curPos)); oldPoint.add(new Point(curPos));
                    System.out.println("FREEDOM!!!");
                    System.out.println(wayOut);
                    search_way = false;
                }
            }

            else if ( (curPos.col-1 >= 0)
                    &&  (array[curPos.row][curPos.col - 1] == 0)
                    &&  !(containsPoint(oldPoint, new Point(curPos.row, curPos.col-1)))  ){
                wayOut.add(new Point(curPos)); oldPoint.add(new Point(curPos));
                curPos.col -= 1;
                if (curPos.compareTo(inPoint) == 0) {
                    counterOut++;
                    if (counterOut > 1) {
                        System.out.println("There is no way out! SORRY!");
                        search_way = false;
                    }
                }
                if (curPos.compareTo(outPoint) == 0) {
                    wayOut.add(new Point(curPos)); oldPoint.add(new Point(curPos));
                    System.out.println("FREEDOM!!!");
                    System.out.println(wayOut);
                    search_way = false;
                }
            }

            else if ( (curPos.row-1 >= 0)
                    &&  (array[curPos.row - 1][curPos.col] == 0)
                    &&  !(containsPoint(oldPoint, new Point(curPos.row-1, curPos.col)))  ) {
                wayOut.add(new Point(curPos)); oldPoint.add(new Point(curPos));
                curPos.row -= 1;
                if (curPos.compareTo(inPoint) == 0) {
                    counterOut++;
                    if (counterOut > 1) {
                        System.out.println("There is no way out! SORRY!");
                        search_way = false;
                    }
                }
                if (curPos.compareTo(outPoint) == 0) {
                    wayOut.add(new Point(curPos)); oldPoint.add(new Point(curPos));
                    System.out.println("FREEDOM!!!");
                    System.out.println(wayOut);
                    search_way = false;
                }
            }
            else {
                wayOut.add(new Point(curPos)); oldPoint.add(new Point(curPos));
                index = 0; boolean crossRoadFound = false;
                if (crossRoads.size() > 0){
                    //System.out.println(wayOut);
                    for (int i = wayOut.size()-1; i >= 0; i--) {
                        for (int j = 0; j < crossRoads.size(); j++) {
                            if (wayOut.get(i).compareTo(crossRoads.get(j)) == 0){
                                curPos.row = crossRoads.get(j).row;
                                curPos.col = crossRoads.get(j).col;
                                crossRoads.remove(j);
                                index = i;
                                crossRoadFound = true;
                                break;
                            }
                        }
                        if (index > 0){
                            break;
                        }
                    }
                    if (crossRoadFound) {
                        for (int i = wayOut.size()-1; i >=0; i--) {
                            if (i > index) wayOut.remove(i);
                        }
                    } else {
                        crossRoads.clear();
                    }
                } else {
                    System.out.println("There is no way out! SORRY!");
                    search_way = false;
                }
            }
        }
    }

}