import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<double[]> stars = new ArrayList<>();
    private static HashBucket position;
    private static HashBucket magnitude;
    private static HashBucket names;

    private static final int SCALE = 700;

    public static double[] coordsToPixel(double x, double y, int draper){
        double half = ((double)SCALE)/2;

        x = (half * x) + half;
        y = (half * y *-1) + half;
        return new double[] {x, y, draper};
    }

    public static void plotSquares(){
        for (double[] d: stars) {
            StdDraw.filledSquare(d[0], d[1], 0.0007);
        }

    }

    public static void readFile() throws FileNotFoundException{
        Scanner file = new Scanner(new File("stars.txt"));
        position = new HashBucket(3527 * 2);
        magnitude = new HashBucket(3527 * 2);
        names = new HashBucket(4000 * 2);

        while(file.hasNext()){
            Scanner starLine = new Scanner(file.nextLine());
            double x = starLine.nextDouble();
            double y = starLine.nextDouble();
            double z = starLine.nextDouble();

            int draper = starLine.nextInt();
            double[] coords = coordsToPixel(x, y, draper);
            stars.add(coords);

            double magnitudeN = starLine.nextDouble();
            int harvard = starLine.nextInt();

            StringBuilder name = new StringBuilder();
            while(starLine.hasNext()){
                name.append(starLine.nextLine().trim());
            }

            String[] namesA = name.toString().split("; ");

            position.put(draper, coords);
            magnitude.put(draper, magnitudeN);

            if(!namesA[0].equals("")){
                for (String n: namesA) {
                    names.put(n, draper);
                }
            }

            plotByMag(draper);
        }


    }

    public static void plotSimple(int id){

        double[] d = (double[]) position.get(id);
        StdDraw.filledCircle(d[0], d[1], ((double)10)/SCALE);

    }

    public static void plotByMag(int id){
        double[] d = (double[]) position.get(id);
        double mag = (double)magnitude.get(id);
        StdDraw.filledCircle(d[0], d[1], 3.5/(mag +2));

    }

    public static void readConsFile(String fileName) throws FileNotFoundException{

        Scanner file = new Scanner(new File(fileName));

        while(file.hasNext()){

            String[] starsNames = file.nextLine().split(",");
            drawLine(starsNames[0], starsNames[1]);

        }

    }

    public static void drawLine(String name1, String name2){

        int id1 = (int)names.get(name1);
        double[] coords1 = (double[]) position.get(id1);

        int id2 = (int)names.get(name2);
        double[] coords2 = (double[]) position.get(id2);

        StdDraw.line(coords1[0], coords1[1], coords2[0], coords2[1]);
    }

    public static void main(String[] args) throws FileNotFoundException {

        StdDraw.setCanvasSize(SCALE, SCALE);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(0, SCALE);
        StdDraw.setYscale(0 , SCALE);

        readFile();

        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.setPenRadius(0.001);
        readConsFile("const.txt");

        StdDraw.save("starChart.png");
    }
}
