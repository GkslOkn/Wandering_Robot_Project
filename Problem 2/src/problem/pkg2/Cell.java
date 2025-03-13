package problem.pkg2;

public class Cell {
    int x;
    int y;
    boolean IsWall;
    boolean IsStart;
    boolean IsFinish;

    public Cell(int x, int y, boolean IsWall, boolean IsStart, boolean IsFinish) {
        this.x = x;
        this.y = y;
        this.IsWall = IsWall;
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

    public boolean isIsWall() {
        return IsWall;
    }

    public void setIsWall(boolean IsWall) {
        this.IsWall = IsWall;
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
