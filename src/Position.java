/**
 * * Position class
 *  * @author 25bauroth
 *  * @date May 18th
 *  * Worked with Issie, Bobby, Sarah
 */
public class Position {
    private int row, col;

    /**
     * Constructor for Position
     * @param c column
     * @param r row
     */
    public Position(int c, int r) {
        col = c;
        row = r;
    }

    /**
     * getter for row
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * getter for column
     * @return the column
     */
    public int getCol() {
        return col;
    }

    /**
     * setter for row
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * setter for column
     * @param col
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Equals method for Position class
     * @param other
     * @return true of equal false if otherwise
     */
    public boolean equals(Object other){
        if(other instanceof Position){
            Position temp = (Position)other;
            return this.row == temp.row && this.col == temp.col;
        }
        return false;
    }
}
