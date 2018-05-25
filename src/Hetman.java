

public class Hetman {

    private static boolean rightDiagonal[] = new boolean[256];
    private static boolean leftDiagonal[] = new boolean[256];
    private static boolean coveredColumns[] = new boolean[8];       // tablica z zajętymi kolumnami
    private static boolean chessboard[][] = new boolean[8][8];// tablica z ustawieniami hetmanów
    private static byte found = 0;                       // licznik znalezionych pozycji

    public static void main(String[] args) {
        addHetman(0);             // postaw pierwszego hetmana
    }

    private static void addHetman(int rowNumber) {
        for (int columnNumber = 0; columnNumber < 8; columnNumber++) {
            if (!(coveredColumns[columnNumber] || rightDiagonal[columnNumber - rowNumber + 128] || leftDiagonal[columnNumber + rowNumber + 128])) {
                rightDiagonal[columnNumber - rowNumber + 128] = true;     // dodaj przekątne
                leftDiagonal[columnNumber + rowNumber + 128] = true;
                coveredColumns[columnNumber] = true;               // dodaj kolumnę
                chessboard[columnNumber][rowNumber] = true;        // postaw hetmana w tablicy
                if (rowNumber < 7) {
                    addHetman(rowNumber + 1);       // analizuj nastepny wiersz (tylko jesli nie jestesmy juz w ostatnim)
                } else {                       // to jest ostatni hetman - zapisz wynik
                    found++;
                    printCombination();
                }
                // po wyjsciu z procedury rekurencyjnej (addHetman(row+1)) usun hetmana i szukaj dla niego nastepnego pola
                rightDiagonal[columnNumber - rowNumber + 128] = false;
                leftDiagonal[columnNumber + rowNumber + 128] = false;
                coveredColumns[columnNumber] = false;
                chessboard[columnNumber][rowNumber] = false;
            }
        }
    }

    public static void printCombination() {
        System.out.println("\n\n");
        System.out.println("kombinacja: " + found + '\n');
        System.out.println("  A B C D E F G H");
        for (int y = 7; y >= 0; y--) {
            System.out.print(y + 1 + " ");
            for (int x = 0; x < 8; x++) {
                if (chessboard[x][y]) {
                    System.out.print("H");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

    }



}