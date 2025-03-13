package problem.pkg2;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Problem2 extends JFrame{
    private void RandomizeStartEnd(int [][]IntMatrix, int Row, int Column){
        Random rand = new Random();
        
        int StartYX = rand.nextInt(4);
        int EndYX = rand.nextInt(4);
        
        System.out.println("StartYX: " + StartYX);
        System.out.println("EndYX: " + EndYX);
        
        if(StartYX==EndYX && StartYX!=3){
            EndYX=StartYX+1;
        }
        
        if(StartYX==EndYX && StartYX==3){
            EndYX=StartYX-1;
        }
        
        if(StartYX==0){
            IntMatrix[0][0]=5;
        }
                        
        if(StartYX==1){
            IntMatrix[0][Column-1]=5;
        }
                        
        if(StartYX==2){
            IntMatrix[Row-1][0]=5;
        }
                        
        if(StartYX==3){
            IntMatrix[Row-1][Column-1]=5;
        }
                        
        if(EndYX==0){
            IntMatrix[0][0]=8;
        }
                        
        if(EndYX==1){
            IntMatrix[0][Column-1]=8;
        }
                       
        if(EndYX==2){
            IntMatrix[Row-1][0]=8;
        }
                       
        if(EndYX==3){
            IntMatrix[Row-1][Column-1]=8;
        }
    }
    
    private JLabel CreateGridLabels(int gridnumber){
        JLabel gridlabel = new JLabel();
        gridlabel.setHorizontalAlignment(JLabel.CENTER);
        gridlabel.setPreferredSize(new Dimension(30,30));
        switch(gridnumber){
            case 0 -> gridlabel.setBackground(Color.black);
            case 1 -> gridlabel.setBackground(Color.red);
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
    
    public static boolean CanBeSolved(int [][]IntMatrix, int y, int x){
        boolean visited[][] = new boolean[y][x];
        boolean pathexist = false;
        
        for(int a=0;a<y;a++){
            for(int b=0;b<x;b++){
                if(IntMatrix[a][b] == 5 && !visited[a][b]){
                    if(CanBeSolvedIsPath(IntMatrix,a,b,visited)){
                        pathexist=true;
                        break;
                    }
                }
            }
        }
        if(pathexist)
            return true;
        else
            return false;
    }
    
    public static boolean CanBeSolvedIsSafe(int [][]IntMatrix, int y, int x){
        if(y>=0 && y<IntMatrix.length && x>=0 && x<IntMatrix[0].length){
            return true;
        }
        return false;
    }
    
    public static boolean CanBeSolvedIsPath(int [][]IntMatrix, int y, int x, boolean visited[][]){
        if(CanBeSolvedIsSafe(IntMatrix,y,x) && IntMatrix[y][x]!=1 && !visited[y][x]){
            visited[y][x]=true;
            
            if(IntMatrix[y][x]==8){
                System.out.println("Hedefe Ulasşıldı.");
                return true;
            }
            
            boolean up = CanBeSolvedIsPath(IntMatrix,y-1,x,visited);
            if(up){
                System.out.println("Yukarıya Hareket Edildi.");
                return true;
            }
            
            boolean left = CanBeSolvedIsPath(IntMatrix,y,x-1,visited);
            if(left){
                System.out.println("Sola Hareket Edildi.");
                return true;
            }
            
            boolean down = CanBeSolvedIsPath(IntMatrix,y+1,x,visited);
            if(down){
                System.out.println("Aşağıya Hareket Edildi.");
                return true;
            }
            
            boolean right = CanBeSolvedIsPath(IntMatrix,y,x+1,visited);
            if(right){
                System.out.println("Sağa Hareket Edildi.");
                return true;
            }
        }
        
        return false;
    }
    
    private void Setup(){
        JFrame Problem = new JFrame();
        Problem.setTitle("PROBLEM 2");
        Problem.setSize(1600, 900);
        Problem.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Problem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Problem.setLocationRelativeTo(null);
        
        JMenuBar MenuBar = new JMenuBar();
        JMenu ProblemMenu = new JMenu("Problem Menüsü");
        JMenuItem Generate = new JMenuItem("Izgara Oluştur");
        JMenuItem Run = new JMenuItem("Çalıştır");
        
        ProblemMenu.add(Generate);ProblemMenu.add(Run);MenuBar.add(ProblemMenu);
        Problem.add(MenuBar);Problem.setJMenuBar(MenuBar);
        
        Map GeneratedGrid = new Map();
        
        Generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Izgara Oluşturuluyor.");
                String col = JOptionPane.showInputDialog("Lütfen Sütun Sayısını Giriniz.");
                String row = JOptionPane.showInputDialog("Lütfen Satır Sayısını Giriniz.");
                
                GeneratedGrid.ColumnCount = Integer.parseInt(col);
                GeneratedGrid.RowCount = Integer.parseInt(row);
                Random rand = new Random();
                
                System.out.println("Satır Sayısı: " + GeneratedGrid.RowCount);
                System.out.println("Sütun Sayısı: " + GeneratedGrid.ColumnCount);
                
                GeneratedGrid.IntegerMatrix = null;
                GeneratedGrid.IntegerMatrix = new int [GeneratedGrid.RowCount][GeneratedGrid.ColumnCount];
                
                GeneratedGrid.Display=false;
                
                while(GeneratedGrid.Display!=true){
                    for(int i=0; i<GeneratedGrid.RowCount; i++){
                    for(int j=0; j<GeneratedGrid.ColumnCount; j++){
                        GeneratedGrid.IntegerMatrix[i][j] = rand.nextInt(2);
                        System.out.print(GeneratedGrid.IntegerMatrix[i][j] + "");
                    }
                    System.out.println();
                }
                System.out.println("Izgara Oluşturuldu.");
                
                RandomizeStartEnd(GeneratedGrid.IntegerMatrix, GeneratedGrid.RowCount, GeneratedGrid.ColumnCount);
                
                System.out.println("Giriş Çıkış Koordinatları Belirlendi.");
                
                for(int a=0; a<GeneratedGrid.RowCount; a++){
                    for(int b=0; b<GeneratedGrid.ColumnCount; b++){
                        System.out.print(GeneratedGrid.IntegerMatrix[a][b] + "");
                    }
                    System.out.println();
                }
                
               if(CanBeSolved(GeneratedGrid.IntegerMatrix,GeneratedGrid.RowCount,GeneratedGrid.ColumnCount)){
                   GeneratedGrid.Display=true;
               }
               else{
                   GeneratedGrid.Display=false;
               }
                }
                
                GeneratedGrid.CellMatrix = null;
                GeneratedGrid.CellMatrix = new Cell[GeneratedGrid.RowCount][GeneratedGrid.ColumnCount];
                CreateObjectMatrix(GeneratedGrid.IntegerMatrix, GeneratedGrid.RowCount, GeneratedGrid.ColumnCount, GeneratedGrid.CellMatrix);
                System.out.println("Obje Matrisi Oluşturuldu.");
                
                Problem.getContentPane().removeAll();
                Problem.revalidate();
                
                Problem.setLayout(new GridLayout(GeneratedGrid.RowCount, GeneratedGrid.ColumnCount));
                
                for(int y=0; y<GeneratedGrid.RowCount; y++){
                    for(int x=0; x<GeneratedGrid.ColumnCount; x++){
                        JLabel gridlabel = CreateGridLabels(GeneratedGrid.IntegerMatrix[y][x]);
                        Problem.add(gridlabel);
                    }
                }
                System.out.println("Izgara Görselleştirildi.");
                GeneratedGrid.Display=true;
            }
        });
        
        Run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Izgara Çözümü Çalıştırıldı.");
                
                long MazeSolverStartTime = System.nanoTime();
                
                MazeSolverRobot MSBot = new MazeSolverRobot();
                
                int MazeSolverPassedSquares = 0;
                
                if(GeneratedGrid.Display==true){
                    MSBot.SolveMaze(GeneratedGrid.IntegerMatrix, GeneratedGrid.RowCount, GeneratedGrid.ColumnCount, GeneratedGrid.CellMatrix);
                    
                    for(int a=0; a<GeneratedGrid.RowCount; a++){
                        for(int b=0; b<GeneratedGrid.ColumnCount; b++){
                            if(GeneratedGrid.IntegerMatrix[a][b]==4 || GeneratedGrid.IntegerMatrix[a][b]==7 || GeneratedGrid.IntegerMatrix[a][b]==5 || GeneratedGrid.IntegerMatrix[a][b]==8){
                                MazeSolverPassedSquares++;
                            }
                        }
                    }
                    
                    Problem.getContentPane().removeAll();
                    Problem.revalidate();
                    
                    for(int a=0; a<GeneratedGrid.RowCount; a++){
                        for(int b=0; b<GeneratedGrid.ColumnCount; b++){
                            JLabel gridsolvedlabel = CreateGridLabels(GeneratedGrid.IntegerMatrix[a][b]);
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
            out = new PrintStream(new FileOutputStream("problem2output.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.setOut(out);
    }
    
    public static void main(String[] args){
        Problem2 Problem = new Problem2();
        Problem.Setup();
    }
}
