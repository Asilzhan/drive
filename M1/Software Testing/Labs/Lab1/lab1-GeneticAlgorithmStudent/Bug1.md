# Bug 1/2

## Bug line
### File: Algorithm.java:  
---
Line 19: &nbsp; &nbsp; &nbsp; &nbsp; `- for (int i = 7; i >0; i--) {`  
Line 20: &nbsp; &nbsp; &nbsp; &nbsp; `-     for (int j = 7; j >0; j--) {`  

Line 19: &nbsp; &nbsp; &nbsp; &nbsp; `+ for (int i = 7; i >= 0; i--) {`  
Line 20: &nbsp; &nbsp; &nbsp; &nbsp; `+     for (int j = 7; j >= 0; j--) {` 

---

Line 27:  &nbsp; &nbsp; &nbsp; &nbsp; `- if (checkDiagonals(iv,iv.list.get(i), i)) clashes += 1;`  
Line 27:  &nbsp; &nbsp; &nbsp; &nbsp; `+ if (!checkDiagonals(iv,iv.list.get(i), i)) clashes += 1;`



## Bug resolution table
| Method | Variables | Intro and Analysing info | Deciding point and Final conclusion | Heuristics used | 
|--|--|--|--|--|
| runGetFitness() | Breakpoint at line 68 | We start debugging here, using predefined runGetFitness method  | No fault here | Heuristic 1, Heuristic 7 |
| runGetFitness(), lines 68-70| iv: Individual | Here we create new instance of individual and print its value and the board it represents | Continue searching | Heuristic 5 |
| printFitness() | -- | We step into printFitness method. |--|--|
| getFitness() | clashes: 0 | We step into getFitness method |--|--|
| getFitness() | clashes: 6 | We step over to the end of method | From printed board we can see that total count of clashes is 8, howerver, method found only 6. We can conclude that not all possible pairs of queens are considered. There we can see bug 1 itself: we iterate throuh rows and columns of the board from 7 to 1, i.e. row/column at index 0 remains not considered. | Consider all edge cases |
| getFitness() | clashes: 6 | We step over to the end of method | From checkDiagonals method documentation, we can see that method checkDiagonals returns true, if test passed, i.e. current queen does not have any clashes in its diagonal. But in line 27 we increment clash count when checkDiagonals return true for this queen. It contraverts with what getFitness supposed to do, according its documentation - it should count 1 when there are clashes, not when there are none | Heuristic 5 |

```
iv = [4, 3, 0, 0, 4,6, 2, 5]  
. . . . X . . .  
. . . X . . . .  
X . . . . . . .  
X . . . . . . .  
. . . . X . . .  
. . . . . . X .  
. . X . . . . .  
. . . . . X . .  
clashes = 6 (should be 8)
```  


----

# Bug 3

## Bug line
### File: Algorithm.java:  
---

Line 68: &nbsp; &nbsp; &nbsp; &nbsp; `- halfPop.individuals.add(nextGenIv2);`  
Line 68: &nbsp; &nbsp; &nbsp; &nbsp; `+ newPop.individuals.add(nextGenIv2);` 



## Bug resolution table
| Method | Variables | Intro and Analysing info | Deciding point and Final conclusion | Heuristics used | 
|--|--|--|--|--|
| runNextGeneration | Breakpoint at first line of runNextGeneration method | We generate population, print earch individual and its fitness. Then, we check if nextGeneration works correct  |--| -- |
| nextGeneration | popSize: 10, pop.individuals | First we check if pop.individuals sorted correctly. Then we check if halfPop initializes with first half of sorted individuals. | We can see that halfPop correctly takes first half of the sorted individuals. Continue searching |--|
| nextGeneration, line 60| newPop.Length: 51 | We check if newPop initializes correctly. | We can see that newPop.individuals list contains only 51 individuals, expected value is 100. That is because half of the newly generated individuals added to newPop.individuals list, and other half is added to halfPop.individuals list, making its length equal to 99. |--|
