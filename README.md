# weighted-interval-scheduling
## Introduction
This is a dynamically programmed implementation of the weighted interval scheduling problem.

## Sample output
```
ORIGINAL JOB SET
                                        ----------------------------------(2)
          -----(49)
                ---------(44)
                             --------------------------(8)
                 --------------------(15)
                                       -----------------(32)
  --------(47)
    ----(18)
  ----------------------(47)
                            ------------------------------(19)
          -------------------------(27)
  -----(30)
         -----(32)
     ----------------------------------------(9)
  ---------------------------(31)
         -------------------(28)
                    ----------------(16)
     ----------(29)
             ---------------(4)
                     ---------(5)
SORTED JOB SET
  -----(30)
    ----(18)
  --------(47)
         -----(32)
          -----(49)
     ----------(29)
  ----------------------(47)
                ---------(44)
         -------------------(28)
             ---------------(4)
  ---------------------------(31)
                     ---------(5)
          -------------------------(27)
                    ----------------(16)
                 --------------------(15)
     ----------------------------------------(9)
                             --------------------------(8)
                                       -----------------(32)
                            ------------------------------(19)
                                        ----------------------------------(2)
SOLUTION JOB SET (value = 172)
                                       -----------------(32)
                ---------(44)
          -----(49)
  --------(47)
```
