import java.util.*;

public class WeightedIntervalScheduler {
    public List<Job> jobSetOriginal;
    public List<Job> jobSetSorted;
    public List<Job> solutionSet;
    int[] M;

    WeightedIntervalScheduler(List<Job> jobs) {
        jobSetOriginal = jobs;
        jobSetSorted = jobs;
        solutionSet = new ArrayList<>();
        M = new int[jobs.size()];
    }

    //Recursive, top-down solution
    public int computeOpt(int j) {
        if (j == 0) {
            return 0;
        } else {
            return Math.max(jobSetSorted.get(j).value + computeOpt(p(j)), computeOpt(j - 1));
        }
    }

    //Iterative, bottom-up, dynamic programming solution
    public int iterativeComputeOpt() {
        M[0] = 0;
        for (int i = 1; i < M.length; i++) {
            M[i] = Math.max(jobSetSorted.get(i).value + M[p(i)], M[i-1]);
        }
        return M[M.length-1];
    }

    public int findSolutionSet(int j) {
        if (j != 0) {
            Job job = jobSetSorted.get(j);
            if (job.value + M[p(j)] > M[j - 1]) {
                solutionSet.add(job);
                return findSolutionSet(p(j));
            } else {
                return findSolutionSet(j-1);
            }
        } else {
            return 0;
        }

    }

    public void sortJobs() {
        Collections.sort(jobSetSorted, new Comparator<Job>() {
            public int compare(Job j1, Job j2) {
                if (j1.finishTime == j2.finishTime) {
                    return 0;
                }
                return j1.finishTime < j2.finishTime ? -1 : 1;
            }
        });
    }

    private int p(int i) {
        Job job = jobSetSorted.get(i);
        int laterStartTime = job.startTime;
        Job earlierJob = job;

        for (Job j : jobSetSorted) {
            if (j.finishTime <= laterStartTime) {
                earlierJob = j;
            }
        }

        if (earlierJob == job) {
            return 0;
        } else {
            return jobSetSorted.indexOf(earlierJob);
        }
    }
}
