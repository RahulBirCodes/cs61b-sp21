package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> sizes = new AList<>();
        sizes.addLast(1000);
        sizes.addLast(2000);
        sizes.addLast(4000);
        sizes.addLast(8000);
        sizes.addLast(16000);
        sizes.addLast(32000);
        sizes.addLast(64000);
        sizes.addLast(128000);

        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        for (int i = 0; i < sizes.size(); i++) {
            AList<Integer> testArr = new AList<>();
            double totalTime = 0.0;
            int opCount = 0;
            for (int j = 0; j < sizes.get(i); j++) {
                Stopwatch sw = new Stopwatch();
                testArr.addLast(j);
                totalTime += sw.elapsedTime();
                opCount++;
            }
            times.addLast(totalTime);
            opCounts.addLast(opCount);
        }
        printTimingTable(sizes, times, opCounts);
    }
}
