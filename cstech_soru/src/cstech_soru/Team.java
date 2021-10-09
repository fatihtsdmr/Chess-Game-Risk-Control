package cstech_soru;

public class Team {

	String teamName;
	// Bir ta��n 2 kere riske edilmesini �nlemek i�in koordinat sistemi
	boolean[][] chess_Piece_Coordinate = new boolean[8][8];

	// Team objesi ilk �al��t���nda o anki tahtada bulunan ta�lar
	// bishop=fill, king=�ah, knight= at, pawn=piyon, queen=vezir, rook=kale.
	double score;
	double bishop = 0;
	double king = 0;
	double kinght = 0;
	double pawn = 0;
	double queen = 0;
	double rook = 0;

	// Team objesi ilk �al��t���nda o anki tahtada bulunan riskli ta�lar
	double risky_bishop = 0;
	double risky_king = 0;
	double risky_kinght = 0;
	double risky_pawn = 0;
	double risky_queen = 0;
	double risky_rook = 0;

	// constructor
	Team(String name) {

		teamName = name;

	}

	// tahtada bulunan ta�lar�n toplam puan�n�n hesaplanmas�
	public void Score_Control() {

		score = (bishop * 3) + (rook * 5) + (king * 100) + (kinght * 3) + (queen * 9) + (pawn * 1)
				+ (risky_bishop * 1.5) + (risky_rook * 2.5) + (risky_kinght * 1.5) + (risky_queen * 4.5)
				+ (risky_pawn * 0.5);
		// System.out.println("Team "+ teamName+" Total score:"+score);
	}

	// tahtada o an hangi ta�lar�n oldu�unu bulup koordinatta i�aretleme metodu.
	// chess_game objesinden al�nan 2 boyutlu arrayle tahta analizi yap�l�yor.
	// bu metoda g�re tak�mlar�n ka� puanla ba�layacaklar� belli oluyor.
	public void board_Control(String[][] board) {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if (teamName.equals("Siyah")) {

					if (board[i][j].equals("as")) {
						chess_Piece_Coordinate[i][j] = true;
						kinght++;
					}

					if (board[i][j].equals("ks")) {
						rook++;
						chess_Piece_Coordinate[i][j] = true;

					}

					if (board[i][j].equals("ps")) {
						chess_Piece_Coordinate[i][j] = true;
						pawn++;

					}

					if (board[i][j].equals("vs")) {
						queen++;
						chess_Piece_Coordinate[i][j] = true;
					}

					if (board[i][j].equals("fs")) {
						bishop++;
						chess_Piece_Coordinate[i][j] = true;

					}
					if (board[i][j].equals("ss")) {
						king++;
						chess_Piece_Coordinate[i][j] = true;
					}

				}

				else if (teamName.equals("Beyaz")) {

					if (board[i][j].equals("ab")) {

						kinght++;
						chess_Piece_Coordinate[i][j] = true;
					}

					if (board[i][j].equals("kb")) {
						rook++;
						chess_Piece_Coordinate[i][j] = true;

					}

					if (board[i][j].equals("pb")) {

						pawn++;
						chess_Piece_Coordinate[i][j] = true;

					}

					if (board[i][j].equals("vb")) {
						queen++;
						chess_Piece_Coordinate[i][j] = true;
					}

					if (board[i][j].equals("fb")) {
						bishop++;
						chess_Piece_Coordinate[i][j] = true;

					}
					if (board[i][j].equals("sb")) {
						king++;
						chess_Piece_Coordinate[i][j] = true;
					}

				}

			}
		}

	}

	// chess_game objesinden riskli ta�lar�n ismi ve koordinatlar� yollan�yor
	// riskli ta�lar�n say�lar� artarken, risk alt�nda olmayan ta�lar�n say�s�
	// azal�yor.
	// riskli ta�lar�n koordinattaki i�aretlemesi yap�l�yor. Buna g�re tekrar risk
	// alt�na girip gereksiz puan kaybetmesi �nleniyor.
	public void risky_Scoring(String name, int x, int y) {

		if (name.equals("as") || name.equals("ab") && chess_Piece_Coordinate[x][y] == true) {
			risky_kinght++;
			kinght--;
			chess_Piece_Coordinate[x][y] = false;

		}

		if (name.equals("ks") || name.equals("kb") && chess_Piece_Coordinate[x][y] == true) {
			risky_rook++;
			rook--;
			chess_Piece_Coordinate[x][y] = false;

		}

		if (name.equals("ps") || name.equals("pb") && chess_Piece_Coordinate[x][y] == true) {
			risky_pawn++;
			pawn--;
			chess_Piece_Coordinate[x][y] = false;

		}

		if (name.equals("vs") || name.equals("vb") && chess_Piece_Coordinate[x][y] == true) {
			risky_queen++;
			queen--;
			chess_Piece_Coordinate[x][y] = false;

		}

		if (name.equals("fs") || name.equals("fb") && chess_Piece_Coordinate[x][y] == true) {
			risky_bishop++;
			bishop--;
			chess_Piece_Coordinate[x][y] = false;

		}

	}

}
