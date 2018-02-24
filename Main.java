import java.util.*;

import javafx.beans.binding.SetExpression;


public class Main {

    static int maxJobLength = 20;
    static int maxValue = 20;
    static int numJobs = 20;
    static int latestStartTime = 20;
    static Random rand = new Random();
    static List<Job> jobSet; 

    public static void main(String[] args) {
        jobSet = generateJobSet();

        System.out.println("ORIGINAL JOB SET");
        displayJobSet(jobSet);

        WeightedIntervalScheduler weightedIntervalScheduler = new WeightedIntervalScheduler(jobSet);
        weightedIntervalScheduler.sortJobs();

        int solution = weightedIntervalScheduler.iterativeComputeOpt();
        weightedIntervalScheduler.findSolutionSet(19);

        System.out.println("SOLUTION JOB SET (value = " + solution + ")");
        displayJobSet(weightedIntervalScheduler.solutionSet);
    }

    private static List<Job> generateJobSet() {
        List<Job> jobs = new ArrayList<>();

        for (int i = 0; i < numJobs; i++) {
            int s = rand.nextInt(latestStartTime) + 1;
            int f = s + rand.nextInt(maxJobLength) + 1;
            int v = rand.nextInt(maxValue) + 1;

            jobs.add(new Job(s, f, v));
        }
        return jobs;
    }

    private static void displayJobSet(List<Job> set) {
        for (Job j : set) {
            System.out.println(displayJob(j));
        }

    }

    private static String displayJob(Job j) {
        String leadingSpaces = new String(new char[j.startTime]).replace("\0", " ");
        String trailingSpaces = new String(new char[(maxJobLength + latestStartTime) - j.finishTime]).replace("\0", " ");
        String dashes = new String(new char[j.finishTime - j.startTime]).replace("\0", "-");
        return leadingSpaces + dashes + j.value + trailingSpaces;

    }
}