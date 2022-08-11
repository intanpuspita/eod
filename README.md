# eod

## How to run the code
- Put csv file in upload folder (currently the program will read file with filename `Before Eod.csv`)
- Run the `App.java` program
- The csv result will be in `output` folder

## How this program works
- In `App.java`, program will read data from csv and put it in the list of `Customer`. After all of records has copied to list of customer, then it will continue to the next step.
- In [processData](https://github.com/intanpuspita/eod/blob/master/src/App.java#L55), the program defined 8 threads for each process (set average balance, set free transfer A, set free transfer B, and set additional balanced for first 100 customers). Then the total fixed thread is 32.
- Then the data will be divided into those 8 thread. And each process will update the list of customer value.
- After the executor has terminated, the program will generate csv file from list of customer in [writeCsvResult](https://github.com/intanpuspita/eod/blob/master/src/App.java#L93)
