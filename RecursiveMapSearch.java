/*
1. Always go right first if given the choice
2. Have directional assignments for the direction you are facing (NEWS)
    Check if there is x , . in the space
    Check every move for a valid move 
    Once theres a dead end check the direction for + until theres empty space 
3. Put + in the empty spaces
4. Tip, use a giant if else structure 
5. BAse cases, mark the current spot, are we in bounds, 
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class RecursiveMapSearch {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("Test.Maze.txt"));
        int rows = file.nextInt();
        int cols = file.nextInt();
        file.nextLine(); 
        int startrow = 0;
        int startcol = 0;
        char[][] maze = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            String line = file.nextLine();
            for (int c = 0; c < cols; c++) {
                maze[r][c] = line.charAt(c);
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if(maze[r][c] == '+'){
                    startrow = r;
                    startcol = c;
                }
            }
            System.out.println();
        }
        file.close();
        maze[startrow][startcol] = ' ';
        boolean solved = solve(maze, startrow, startcol);
        if(solved){
            System.out.println("Maze was solved");
        }
        else{
            System.out.println("Could not solve maze");
        }
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(maze[r][c]);
            }
            System.out.println();
        }
    }
    public static boolean solve(char[][] maze, int row, int col){
        if(row < 0 || row >= maze.length || col < 0 || col >= maze[0].length){
            return false;
        }
        if(maze[row][col] == '-'){
            return true;
        }
        if(maze[row][col] == 'X' || maze[row][col] == '.'|| maze[row][col] == '+'){
            return false;
        }
        maze[row][col] = '+';
        
        if(solve(maze, row, col+1)){
            return true;
        }
        else if(solve(maze, row+1, col)){
            return true;
        }
        else if(solve(maze, row, col-1)){
            return true;
        }
        else if(solve(maze, row-1, col)){
            return true;
        }
        maze[row][col] = '.';
        return false;
    }
}
