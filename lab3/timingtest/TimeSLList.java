package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
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
        for (int sizeInd = 0; sizeInd < sizes.size(); sizeInd++) {
            // create list
            int N = sizes.get(sizeInd);
            SLList<Integer> test = new SLList<>();
            for (int i = 0; i < N; i++) {
                test.addLast(i);
            }

            // start timer and add M items
            int M = 1000;
            double totalTime;
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < M; j++) {
                test.addLast(j);
            }
            totalTime = sw.elapsedTime();
            times.addLast(totalTime);
            opCounts.addLast(M);
        }
        printTimingTable(sizes, times, opCounts);
    }

}
