import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    ArrayList<Double[]> stars = new ArrayList<>();
    private HashBucket position;
    private HashBucket magnitude;
    private HashBucket names;

    private static final int SCALE = 1000;

    public static double[] coordsToPixel(double x, double y){
        double half = ((double)SCALE)/2;

        x = (half * x) + half;
        y = (half * y *-1) + half;
        return new double[] {x, y};
    }

    public void plotSquares(){
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setCanvasSize(SCALE, SCALE);

        for (Double[] d: stars) {
            StdDraw.square(d[0], d[1], 1);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File("stars.txt"));

        int starNum = 0;
        while(file.hasNext()){
            Scanner starLine = new Scanner(file.nextLine());
            double x = starLine.nextDouble();
            double y = starLine.nextDouble();
            double z = starLine.nextDouble();

            int draper = starLine.nextInt();
            double magnitude = starLine.nextDouble();
            int harvard = starLine.nextInt();

            try{
                System.out.println(starLine.nextLine().trim());
            }
            catch (Exception e){
            }
        }

    }
}
