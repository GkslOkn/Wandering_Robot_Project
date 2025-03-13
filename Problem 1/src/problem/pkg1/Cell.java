package problem.pkg1;

public class Cell {
    int x;
    int y;
    boolean IsObstacle;
    boolean IsStart;
    boolean IsFinish;

    public Cell(int x, int y, boolean IsObstacle, boolean IsStart, boolean IsFinish) {
        this.x = x;
        this.y = y;
        this.IsObstacle = IsObstacle;
        this.IsStart = IsStart;
        this.IsFinish = IsFinish;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isIsObstacle() {
        return IsObstacle;
    }

    public void setIsObstacle(boolean IsObstacle) {
        this.IsObstacle = IsObstacle;
    }

    public boolean isIsStart() {
        return IsStart;
    }

    public void setIsStart(boolean IsStart) {
        this.IsStart = IsStart;
    }

    public boolean isIsFinish() {
        return IsFinish;
    }

    public void setIsFinish(boolean IsFinish) {
        this.IsFinish = IsFinish;
    }
    
    
}
