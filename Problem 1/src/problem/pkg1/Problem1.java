package problem.pkg1;

import java.io.*;
import java.util.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Problem1 extends JFrame{
    private void RandomizeStartEnd(int [][]IntMatrix, int Row, int Column){
        int StartY;
        int StartX;
        int EndY;
        int EndX;
        boolean Start = false;
        boolean End = false;
        
        Random rand = new Random();
        
        while(Start!=true){
            StartY = rand.nextInt(Row);
            StartX = rand.nextInt(Column);
            
            if(IntMatrix[StartY][StartX]==0){
                IntMatrix[StartY][StartX]=5;
                Start=true;
            }
        }
        
        while(End!=true){
            EndY = rand.nextInt(Row);
            EndX = rand.nextInt(Column);
            
            if(IntMatrix[EndY][EndX]==0){
                IntMatrix[EndY][EndX]=8;
                End=true;
            }
        }
    }
    
    private JLabel CreateGridLabels(int gridnumber){
        JLabel gridlabel = new JLabel();
        gridlabel.setHorizontalAlignment(JLabel.CENTER);
        gridlabel.setPreferredSize(new Dimension(30,30));
        switch(gridnumber){
            case 0 -> gridlabel.setBackground(Color.black);
            case 1 -> gridlabel.setBackground(Color.red);
            case 2 -> gridlabel.setBackground(Color.red);
            case 3 -> gridlabel.setBackground(Color.red);
            case 5 -> gridlabel.setBackground(Color.green);
            case 8 -> gridlabel.setBackground(Color.yellow);
            case 4 -> gridlabel.setBackground(Color.blue);
            case 7 -> gridlabel.setBackground(Color.orange);
            default -> {
            }
        }
        gridlabel.setOpaque(true);
        gridlabel.setBorder(BorderFactory.createLineBorder(Color.white));
        return gridlabel;
    }
    
    private void CreateObjectMatrix(int [][]IntMatrix, int Row, int Column, Cell [][]CellMatrix){
        for(int a=0;a<Row;a++){
            for(int b=0;b<Column;b++){
                int gridnumber = IntMatrix[a][b];
                switch(gridnumber){
                    case 0 -> {
                        Cell BlankCell = new Cell(a,b,false,false,false);
                        CellMatrix[a][b] = BlankCell;
                    }
                    case 1 -> {
                        Cell Obstacle = new Cell(a,b,true,false,false);
                        CellMatrix[a][b] = Obstacle;
                    }
                    case 2 -> {
                        Cell Obstacle2 = new Cell(a,b,true,false,false);
                        CellMatrix[a][b] = Obstacle2;
                    }
                    case 3 -> {
                        Cell Obstacle3 = new Cell(a,b,true,false,false);
                        CellMatrix[a][b] = Obstacle3;
                    }
                    case 5 -> {
                        Cell StartCell = new Cell(a,b,false,true,false);
                        CellMatrix[a][b] = StartCell;
                    }
                    case 8 -> {
                        Cell FinishCell = new Cell(a,b,false,false,true);
                        CellMatrix[a][b] = FinishCell;
                    }
                    default -> {
                    }
                }
            }
        }
    }
    
    private void Setup(){
        JFrame Problem = new JFrame();
        Problem.setTitle("PROBLEM 1");
        Problem.setSize(1600, 900);
        Problem.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Problem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Problem.setLocationRelativeTo(null);
        
        JMenuBar MenuBar = new JMenuBar();
        JMenu ProblemMenu = new JMenu("Problem Menüsü");
        JMenuItem URL1 = new JMenuItem("1. URL'den Izgara Oluştur");
        JMenuItem URL2 = new JMenuItem("2. URL'den Izgara Oluştur");
        JMenuItem Run = new JMenuItem("Çalıştır");
        
        ProblemMenu.add(URL1);ProblemMenu.add(URL2);ProblemMenu.add(Run);MenuBar.add(ProblemMenu);
        Problem.add(MenuBar);Problem.setJMenuBar(MenuBar);
        
        Map Grid1 = new Map();
        Map Grid2 = new Map();
        
        URL1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("1. URL'den Izgara Oluşturuldu.");
                
                Scanner RowScanner = null;
                Scanner ColumnScanner = null;
                Scanner MatrixScanner = null;
                
                try {
                    URL GridMatrixData = new URL("http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt");
                    
                    RowScanner = new Scanner(GridMatrixData.openStream());
                    ColumnScanner = new Scanner(GridMatrixData.openStream());
                    MatrixScanner = new Scanner(GridMatrixData.openStream());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                Grid1.RowCount = 0;
                Grid1.ColumnCount = 0;
                
                while(RowScanner.hasNextLine()){
                    Grid1.RowCount++;
                    RowScanner.nextLine();
                }
                System.out.println("Satır Sayısı: " + Grid1.RowCount);
                RowScanner.close();
                
                if(ColumnScanner.hasNextLine()){
                    Grid1.ColumnCount = ColumnScanner.nextLine().split("").length;
                    System.out.println("Sütun Sayısı: " + Grid1.ColumnCount);
                }
                ColumnScanner.close();
                
                Grid1.IntegerMatrix = null;
                Grid1.IntegerMatrix = new int [Grid1.RowCount][Grid1.ColumnCount];
                
                for(int i=0; i<Grid1.RowCount; i++){
                    String MatrixNumbers[] = MatrixScanner.nextLine().split("");
                    for(int j=0; j<Grid1.ColumnCount; j++){
                        Grid1.IntegerMatrix[i][j] = Integer.parseInt(MatrixNumbers[j]);
                    }
                }
                
                for(int a=0; a<Grid1.RowCount; a++){
                    for(int b=0; b<Grid1.ColumnCount; b++){
                        System.out.print(Grid1.IntegerMatrix[a][b] + "");
                    }
                    System.out.println();
                }
                MatrixScanner.close();
                
                RandomizeStartEnd(Grid1.IntegerMatrix, Grid1.RowCount, Grid1.ColumnCount);
                
                System.out.println("Giriş Çıkış Koordinatları Belirlendi.");
                
                for(int a=0; a<Grid1.RowCount; a++){
                    for(int b=0; b<Grid1.ColumnCount; b++){
                        System.out.print(Grid1.IntegerMatrix[a][b] + "");
                    }
                    System.out.println();
                }
                
                Grid1.CellMatrix = null;
                Grid1.CellMatrix = new Cell [Grid1.RowCount][Grid1.ColumnCount];
                CreateObjectMatrix(Grid1.IntegerMatrix, Grid1.RowCount, Grid1.ColumnCount, Grid1.CellMatrix);
                System.out.println("Obje Matrisi Oluşturuldu.");
                
                Problem.getContentPane().removeAll();
                Problem.revalidate();
                
                Problem.setLayout(new GridLayout(Grid1.RowCount, Grid1.ColumnCount));
                
                for(int y=0; y<Grid1.RowCount; y++){
                    for(int x=0; x<Grid1.ColumnCount; x++){
                        JLabel gridlabel = CreateGridLabels(Grid1.IntegerMatrix[y][x]);
                        Problem.add(gridlabel);
                    }
                }
                System.out.println("Izgara Görselleştirildi.");
                Grid2.Display=false;
                Grid1.Display=true;
            }
        });
        
        URL2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("2. URL'den Izgara Oluşturuldu.");
                
                Scanner RowScanner = null;
                Scanner ColumnScanner = null;
                Scanner MatrixScanner = null;
                
                try {
                    URL GridMatrixData = new URL("http://bilgisayar.kocaeli.edu.tr/prolab2/url2.txt");
                    
                    RowScanner = new Scanner(GridMatrixData.openStream());
                    ColumnScanner = new Scanner(GridMatrixData.openStream());
                    MatrixScanner = new Scanner(GridMatrixData.openStream());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                Grid2.RowCount = 0;
                Grid2.ColumnCount = 0;
                
                while(RowScanner.hasNextLine()){
                    Grid2.RowCount++;
                    RowScanner.nextLine();
                }
                System.out.println("Satır Sayısı: " + Grid2.RowCount);
                RowScanner.close();
                
                if(ColumnScanner.hasNextLine()){
                    Grid2.ColumnCount = ColumnScanner.nextLine().split("").length;
                    System.out.println("Sütun Sayısı: " + Grid2.ColumnCount);
                }
                ColumnScanner.close();
                
                Grid2.IntegerMatrix = null;
                Grid2.IntegerMatrix = new int [Grid2.RowCount][Grid2.ColumnCount];
                
                for(int i=0; i<Grid2.RowCount; i++){
                    String MatrixNumbers[] = MatrixScanner.nextLine().split("");
                    for(int j=0; j<Grid2.ColumnCount; j++){
                        Grid2.IntegerMatrix[i][j] = Integer.parseInt(MatrixNumbers[j]);
                    }
                }
                
                for(int a=0; a<Grid2.RowCount; a++){
                    for(int b=0; b<Grid2.ColumnCount; b++){
                        System.out.print(Grid2.IntegerMatrix[a][b] + "");
                    }
                    System.out.println();
                }
                MatrixScanner.close();
                
                RandomizeStartEnd(Grid2.IntegerMatrix, Grid2.RowCount, Grid2.ColumnCount);
                
                System.out.println("Giriş Çıkış Koordinatları Belirlendi.");
                
                for(int a=0; a<Grid2.RowCount; a++){
                    for(int b=0; b<Grid2.ColumnCount; b++){
                        System.out.print(Grid2.IntegerMatrix[a][b] + "");
                    }
                    System.out.println();
                }
                
                Grid2.CellMatrix = null;
                Grid2.CellMatrix = new Cell [Grid2.RowCount][Grid2.ColumnCount];
                CreateObjectMatrix(Grid2.IntegerMatrix, Grid2.RowCount, Grid2.ColumnCount, Grid2.CellMatrix);
                System.out.println("Obje Matrisi Oluşturuldu.");
                
                Problem.getContentPane().removeAll();
                Problem.revalidate();
                
                Problem.setLayout(new GridLayout(Grid2.RowCount, Grid2.ColumnCount));
                
                for(int row=0; row<Grid2.RowCount; row++){
                    for(int col=0; col<Grid2.ColumnCount; col++){
                        JLabel gridlabel = CreateGridLabels(Grid2.IntegerMatrix[row][col]);
                        Problem.add(gridlabel);
                    }
                }
                
                System.out.println("Izgara Görselleştirildi.");
                Grid1.Display=false;
                Grid2.Display=true;
            }
        });
        
        Run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Izgara Çözümü Çalıştırıldı.");
                
                long MazeSolverStartTime = System.nanoTime();
                
                MazeSolverRobot MSBot = new MazeSolverRobot();
                
                int MazeSolverPassedSquares = 0;
                
                if(Grid1.Display==true && Grid2.Display==false){
                    MSBot.SolveMaze(Grid1.IntegerMatrix, Grid1.RowCount, Grid1.ColumnCount, Grid1.CellMatrix);
                    
                    for(int a=0; a<Grid1.RowCount; a++){
                        for(int b=0; b<Grid1.ColumnCount; b++){
                            if(Grid1.IntegerMatrix[a][b]==4 || Grid1.IntegerMatrix[a][b]==7 || Grid1.IntegerMatrix[a][b]==5 || Grid1.IntegerMatrix[a][b]==8){
                                MazeSolverPassedSquares++;
                            }
                        }
                    }
                    
                    Problem.getContentPane().removeAll();
                    Problem.revalidate();
                    
                    for(int a=0; a<Grid1.RowCount; a++){
                        for(int b=0; b<Grid1.ColumnCount; b++){
                            JLabel gridsolvedlabel = CreateGridLabels(Grid1.IntegerMatrix[a][b]);
                            Problem.add(gridsolvedlabel);
                        }
                    }
                }
                
                if(Grid1.Display==false && Grid2.Display==true){
                    MSBot.SolveMaze(Grid2.IntegerMatrix, Grid2.RowCount, Grid2.ColumnCount, Grid2.CellMatrix);
                    
                    for(int a=0; a<Grid2.RowCount; a++){
                        for(int b=0; b<Grid2.ColumnCount; b++){
                            if(Grid2.IntegerMatrix[a][b]==4 || Grid2.IntegerMatrix[a][b]==7 || Grid2.IntegerMatrix[a][b]==5 || Grid2.IntegerMatrix[a][b]==8){
                                MazeSolverPassedSquares++;
                            }
                        }
                    }
                    
                    Problem.getContentPane().removeAll();
                    Problem.revalidate();
                    
                    for(int a=0; a<Grid2.RowCount; a++){
                        for(int b=0; b<Grid2.ColumnCount; b++){
                            JLabel gridsolvedlabel = CreateGridLabels(Grid2.IntegerMatrix[a][b]);
                            Problem.add(gridsolvedlabel);
                        }
                    }
                }
                
                long MazeSolverFinishTime = System.nanoTime();
                long MazeSolverElapsedTime = MazeSolverFinishTime - MazeSolverStartTime;
                JOptionPane.showMessageDialog(Problem,"Izgara Labirentinin Çözümü İçin Geçen Süre: " + MazeSolverElapsedTime + "\n" + 
                                                      "Izgara Labirentinin Çözümü İçin Geçilen Kare Sayısı: " + MazeSolverPassedSquares);
                System.out.println("Izgara Labirentinin Çözümü İçin Geçen Süre: " + MazeSolverElapsedTime);
                System.out.println("Izgara Labirentinin Çözümü İçin Geçilen Kare Sayısı: " + MazeSolverPassedSquares);
            }
        });
        
        Problem.setVisible(true);
        
        PrintStream out = null;
        try {
            out = new PrintStream(new FileOutputStream("problem1output.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.setOut(out);
    }
    
    public static void main(String[] args){
        Problem1 Problem = new Problem1();
        Problem.Setup();
    }
}
