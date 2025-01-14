import java.util.*;

public class Main {

    static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Position move(Direction dir) {
            return new Position(x + dir.dx, y + dir.dy);
        }

        Position wallBetween(Position other) {
            return new Position((x + other.x) / 2, (y + other.y) / 2);
        }
    }

    enum Direction {
        UP(0, -2), DOWN(0, 2), LEFT(-2, 0), RIGHT(2, 0);

        final int dx, dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }

    static void percorso(char[][] pos, int n) {
        Stack<Position> stack = new Stack<>();
        Random random = new Random();

        // Trova un punto iniziale valido
        Position start;
        do {
            int x = random.nextInt(n);
            int y = random.nextInt(n);
            if (x % 2 == 1 && y % 2 == 1 && pos[y][x] == '#') {
                start = new Position(x, y);
                break;
            }
        } while (true);

        pos[start.y][start.x] = 'S'; // Punto iniziale
        stack.push(start);

        // Crea un buco nel bordo per il punto di inizio
        if (start.x == 1) pos[start.y][0] = ' ';
        else if (start.x == n - 2) pos[start.y][n - 1] = ' ';
        else if (start.y == 1) pos[0][start.x] = ' ';
        else if (start.y == n - 2) pos[n - 1][start.x] = ' ';

        Position end = null;

        while (!stack.isEmpty()) {
            Position current = stack.peek();
            List<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));
            Collections.shuffle(directions);

            boolean moved = false;
            for (Direction dir : directions) {
                Position next = current.move(dir);
                if (isValidMove(pos, next, n)) {
                    Position wall = current.wallBetween(next);
                    pos[wall.y][wall.x] = ' ';
                    pos[next.y][next.x] = ' ';
                    stack.push(next);
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                end = stack.pop();
            }
        }

        if (end != null) {
            pos[end.y][end.x] = 'E'; // Punto finale

            // Crea un buco nel bordo per il punto di fine
            if (end.x == 1) pos[end.y][0] = ' ';
            else if (end.x == n - 2) pos[end.y][n - 1] = ' ';
            else if (end.y == 1) pos[0][end.x] = ' ';
            else if (end.y == n - 2) pos[n - 1][end.x] = ' ';
        }

        aggiornaTabella(pos, n);
    }

    static boolean isValidMove(char[][] pos, Position p, int n) {
        return p.x > 0 && p.x < n - 1 && p.y > 0 && p.y < n - 1 && pos[p.y][p.x] == '#';
    }

    static void creaTabella(char[][] pos, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pos[i][j] = (i % 2 == 1 && j % 2 == 1) ? '#' : '#';
            }
        }
    }

    static void aggiornaTabella(char[][] pos, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(pos[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 31;
        char[][] pos = new char[n][n];

        creaTabella(pos, n);
        percorso(pos, n);
    }
}
