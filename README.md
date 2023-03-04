# race-clock


This is a program that is intended to be used in a race. There are two different programs meant to be used together. The file hirearchy is described below.

### Prerequisites
- Java 17

### File/Folder explanation
| Foldername | Description |
| -------- | ----------- |
| register | register contains the files for the registerprogram |
| register/src/main | contains the main program for register and 2 Folders for model and view |
| register/src/main/java/register/model | contains the files creating the model for the Gui |
| register/src/main/java/register/view | contains the files for the Gui |
| register/src/test/java/register/model | contains the files responsible for testing the Gui |
| result | result contains the files for the resultprogram |
| result/src/main | contains 2 folders, 1 for the program and 1 for the tests |
| result/src/main/java/result | contains 3 folders and files for the resultprogram |
| result/src/main/java/result/checks | contaisn 3 files, where errors in the register is maintained |
| result/src/main/java/result/listSorterts | contains 8 files, which handles different sorting algortims used to produces the resultfile |
| result/src/main/java/result/parser | contains 5 files which parses text document into different entries for the resultprogram |
| result/src/main/java/result/* | contains 6 files used to generate a result with resultProgram |
| result/src/main/java/util | contains 1 file for formating and converting times |
| result/src/test/java/result | contains 5 files which handles the testing of resultprogram and its functionality |
| result/testdata | containing the files used in the test to genereate different types of usage |


