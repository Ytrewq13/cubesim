package mycubetimer;

import mycubetimer.cubies.*;

public class Cube {

    // The faces.
    private Cubie[] pieces;

    private boolean solved;

    public Cube() {
        this.solved = true;
        this.pieces = new Cubie[68];
        int index = 0;
        // Generate the Cubies.
        // This is as redundant as Parapa the Rapper.
        // TODO: get rid of the real cubies.
        // I will end up representing the entire cube as 54 color cubies.
        // But I can't be bothered refactoring the code right now.
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    int[] coords = {x, y, z};
                    if (x == 0 && y == 0 || x == 0 && z == 0 || y == 0 && z == 0) {
                        // we have at least 2 0's -- we only want 1 (max).
                        // this happens when we have a center or the core.
                        // We want to do nothing here.
                    } else if (x == 0 || y == 0 || z == 0) {
                        // We have an edge.
                        this.pieces[index] = new Edge(coords);
                        index++;
                    } else {
                        // We have a corner.
                        this.pieces[index] = new Corner(coords);
                        index++;
                    }
                }
            }
        }
        // Generate the color cubies.
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (!(x == 0 && y == 0)) {
                    int[] up = {x,2,y};
                    int[] down = {x,-2,y};
                    int[] right = {2,x,y};
                    int[] left = {-2,x,y};
                    int[] front = {x,y,2};
                    int[] back = {x,y,-2};
                    System.out.println(up[0] + "," + up[1] + "," + up[2]);
                    this.pieces[index] = new ColorCubie("white", up);
                    index++;
                    this.pieces[index] = new ColorCubie("yellow", down);
                    index++;
                    this.pieces[index] = new ColorCubie("red", right);
                    index++;
                    this.pieces[index] = new ColorCubie("orange", left);
                    index++;
                    this.pieces[index] = new ColorCubie("green", front);
                    index++;
                    this.pieces[index] = new ColorCubie("blue", back);
                    index++;
                }
            }
        }
        this.testCoords();
        this.rotate("r");
        System.out.println();
        this.testCoords();
        for (Cubie piece : this.pieces) {
            String color = piece.getColor();
            int[] coords = piece.getCoords();
            
            System.out.print(coords[0] + "," + coords[1] + "," + coords[2] + "  ==>  ");
            System.out.println(color);
        }
        
    }

    // Method to test parity of coordinates.
    public void testCoords() {
        for (Cubie piece : this.pieces) {
            piece.testCoords();
        }
    }

    // Method to rotate one face 90 degrees clockwise.
    public boolean rotate(String face) {
        int[] indeces = new int[2];
        int coord;
        int side;
        switch (face) {
            case "l":
                // y and z.
                indeces[0] = 1;
                indeces[1] = 2;
                coord = 0;
                side = -1;
                break;
            case "b":
                // x and y.
                indeces[0] = 0;
                indeces[1] = 1;
                coord = 2;
                side = -1;
                break;
            case "d":
                // x and z.
                indeces[0] = 0;
                indeces[1] = 2;
                coord = 1;
                side = -1;
                break;
            case "u":
                // x and z.
                indeces[0] = 0;
                indeces[1] = 2;
                coord = 1;
                side = 1;
                break;
            case "f":
                // x and y.
                indeces[0] = 0;
                indeces[1] = 1;
                coord = 2;
                side = 1;
                break;
            case "r":
                // y and z.
                indeces[0] = 1;
                indeces[1] = 2;
                coord = 0;
                side = 1;
                break;
            default:
                return false;
        }
        // Iterate through all pieces.
        for (Cubie piece : this.pieces) {
            int[] loc;
            loc = piece.getCoords();
            int repeat = 3;
            if (loc[coord] == side || loc[coord] == 2 * side) {
                if ("u".equals(face) || "l".equals(face) || "b".equals(face)) {
                    repeat = 1;
                }
                for (int j = 0; j < repeat; j++) {
                    loc = piece.getCoords();
                    // This is a piece to perform the transform on.
                    int[] newLoc = new int[3];
                    int[] locTrans = new int[2]; // location to transform.
                    locTrans[0] = loc[indeces[0]];
                    locTrans[1] = loc[indeces[1]];
                    int[] nLocPart = Cube.transform(locTrans); // Transformed partial location.
                    newLoc[indeces[0]] = nLocPart[0];
                    newLoc[indeces[1]] = nLocPart[1];
                    newLoc[coord] = side;
                    piece.moveTo(newLoc);
                }
            }
        }
        return true;
    }

    public static int[] transform(int[] coords) {
        // Multiply by the matrix:
        //   [0  1]
        //   [-1 0]
        int x = coords[0];
        int y = coords[1];
        // Multiply by the matrix.
        int newX = x * 0 + y * -1;
        int newY = x * 1 + y * 0;
        // Form the new coords.
        int[] newLoc = {newX, newY};
        // Return the new location.
        return newLoc;
    }

    // Method to return boolean of whether the cube is solved or not.
    public boolean isSolved() {
        boolean solved = true;
        //TODO: work out if cube is solved.
        return true;
    }

}
