import java.util.Scanner;

class TicTacToe {

public static void main(String[] args) {

// Variable to represent the size of our tic tac toe board
int n = 3;

// nxn array that represents our tic tac toe board
char[][] board = new char[n][n];

//Initialize our board with dashes (empty positions)
for(int i = 0; i < n; i++) {
for(int j = 0; j < n; j++) {
board[i][j] = '-';
}
}

//Create a Scanner for asking the players for their names
Scanner in = new Scanner(System.in);
System.out.println("Tic Tac Toe is ready for play!\n");
System.out.print("What is your name?, player 1: ");
String p1 = in.nextLine();
System.out.print(" What is your name?, player 2:");
String p2 = in.nextLine();

//Create a player1 boolean that is true if it is player 1's turn and false if it is player 2's turn
boolean player1 = true;

//Create a gameEnded boolean and use it as the condition in the while loop
boolean gameEnded = false;
while(!gameEnded) {

//Draw the board
drawBoard(board);

//Print whose turn it is
if(player1) {
System.out.println(p1 + "'s Turn (x):");
} else {
System.out.println(p2 + "'s Turn (o):");
}

//Create a char variable that stores either 'x' or 'o' based on what player's turn it is
char c = '-';
if(player1) {
c = 'x';
} else {
c = 'o';
}

//Create row and col variables which represent indexes that correspond to a position on our board
int row = 0;
int col = 0;

//Only break out of the while loop once the user enters a valid position
while(true) {

//Ask the user for what position they want to place their x or o
System.out.print("Enter a row number: ");
row = in.nextInt();
System.out.print("Enter a column number: ");
col = in.nextInt();

//Check if the row and col are outside of the board
if(row < 0 || col < 0 || row >= n || col >= n) {
System.out.println("This position is off the bounds of the board! Try again.");

//Check if the position on the board the user entered is empty (has a -) or not
} else if(board[row][col] != '-') {
System.out.println("Someone has already made a move at this position! Try again.");

//Otherwise, the position is valid so break out of the while loop
} else {
break;
}
}

//Set the position on the board at row, col to c
board[row][col] = c;

//Check to see if either player has won
if(playerHasWon(board) == 'x') {
System.out.println(p1 + " has won!");
gameEnded = true;
} else if(playerHasWon(board) == 'o') {
System.out.println(p2 + " has won!");
gameEnded = true;
} else {

//If neither player has won, check to see if there has been a tie (if the board is full)
if(boardIsFull(board)) {
System.out.println("It's a tie!");
gameEnded = true;
} else {

//If player1 is true, make it false, and vice versa; this way, the players alternate each turn
player1 = !player1;
}
}
}

//Draw the board at the end of the game
drawBoard(board);
}

//Make a function to draw the tic tac toe board
public static void drawBoard(char[][] board) {
System.out.println("Board:");
for(int i = 0; i < board.length; i++) {

//The inner for loop prints out each row of the board
for(int j = 0; j < board[i].length; j++) {
System.out.print(board[i][j]);
}

//This print statement makes a new line so that each row is on a separate line
System.out.println();
}
}

//Make a function to see if someone has won and return the winning char
public static char playerHasWon(char[][] board) {

// Checking each row
for(int i = 0; i < board.length; i++) {

// The boolean inARow is true if a player has won by putting n of their chars in row i and false otherwise
boolean inARow = true;

// The char value is one of the chars in row i; we can use this to check if every other char in row i is equal to value
char value = board[i][0];

// First we have to check if the value is not -, because if it is, that means that there is an empty spot in the row so we can automatically say that inARow is false
if(value == '-') {
inARow = false;

// Otherwise, we can use a nested for loop to check each position in the row starting at index 1 (since index 0 is our value and we don't need to check if board[i][0] equals value) and check if that position equals value
} else {
for(int j = 1; j < board[i].length; j++) {

// If board[i][j] doesn't equal value, then we know that inARow is false; we can also break out of the nested for loop because we don't need to look at the rest of this row
if(board[i][j] != value) {
inARow = false;
break;
}
}
}

// If inARow is true, then we know that each position in row i had a char equal to value which was not a -. In other words, a player has won so we can return value (the char of the winner)
if(inARow) {
return value;
}
}

// We can use the same construction to check each column
for(int j = 0; j < board[0].length; j++) {
boolean inACol = true;
char value = board[0][j];

if(value == '-') {
inACol = false;
} else {

for(int i = 1; i < board.length; i++) {
if(board[i][j] != value) {
inACol = false;
break;
}
}
}

if(inACol) {

return value;
}
}

// We can use a similar construction to check the diagonals
// Check the diagonal going from upper left to bottom right: [0][0], [1][1], [2][2]...
boolean inADiag1 = true;
char value1 = board[0][0];
if(value1 == '-') {
inADiag1 = false;
} else {
for(int i = 1; i < board.length; i++) {
if(board[i][i] != value1) {
inADiag1 = false;
break;
}
}
}

if(inADiag1) {
return value1;
}

// Check the diagonal going from upper right to bottom left: [0][n-1], [1][n-2], [2][n-3]...
boolean inADiag2 = true;
char value2 = board[0][board.length-1];

if(value2 == '-') {
inADiag2 = false;
} else {
for(int i = 1; i < board.length; i++) {
if(board[i][board.length-1-i] != value2) {
inADiag2 = false;
break;
}
}
}

if(inADiag2) {
return value2;
}

//Otherwise nobody has not won yet
return ' ';
}

//Make a function to check if all of the positions on the board have been filled
public static boolean boardIsFull(char[][] board) {
for(int i = 0; i < board.length; i++) {
for(int j = 0; j < board[i].length; j++) {
if(board[i][j] == '-') {
return false;
}
}
}
return true;
}
}
