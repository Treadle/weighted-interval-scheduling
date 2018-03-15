import java.util.*;

public class WeightedIntervalScheduler {
    public List<Job> jobSet;
    public List<Job> jobSetSorted;
    public List<Job> solutionSet;
    private int[] M;

    WeightedIntervalScheduler(List<Job> jobs) {
        Collections.sort(jobs, new Comparator<Job>() {
            public int compare(Job j1, Job j2) {
                if (j1.finishTime == j2.finishTime) {
                    return 0;
                }
                return j1.finishTime < j2.finishTime ? -1 : 1;
            }
        });

        jobSetSorted = jobs;
        jobSetSorted.add(0,null);
        solutionSet = new ArrayList<>();
        M = new int[jobSetSorted.size()];
        
    }

    public int iterative() {
        M[0] = 0;
        for (int i = 1; i < M.length; i++) {
            M[i] = Math.max(jobSetSorted.get(i).value + M[p(i)], M[i-1]);
        }
        return M[jobSetSorted.size()-1];
    }

    public int findSolutionSet(int j) {
        if (j == 0) {
            return 0;
        } else if (jobSetSorted.get(j).value + M[p(j)] > M[j-1]) {
            solutionSet.add(jobSetSorted.get(j));
            return findSolutionSet(p(j));
        } else {
            return findSolutionSet(j-1);
        }
    }

    private int p(int i) {
        Job job = jobSetSorted.get(i);
        int laterStartTime = job.startTime;
        Job earlierJob = job;

        for (Job j : jobSetSorted) {
            if (j != null) {
                if (j.finishTime <= laterStartTime) {
                    earlierJob = j;
                }
            }
        }

        if (earlierJob == job) {
            return 0;
        } else {
            return jobSetSorted.indexOf(earlierJob);
        }
    }
}
