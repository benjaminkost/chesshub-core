package chessgame.figures;

import java.util.ArrayList;
import java.util.List;

import chessgame.ChessBoard;
import chessgame.ChessTile;

public class King extends ChessFigure {

	/**
	 * The image path for the black figure
	 */
	private final static String BLACK_KING_PATH = "chessgame/figures/images/BlackKing.png";

	/**
	 * The image path for the white figure
	 */
	private final static String WHITE_KING_PATH = "chessgame/figures/images/WhiteKing.png";

	/**
	 * 
	 * @param side The side of the figure
	 */
	public King(ChessColor side) {
		super(side);
	}

	public String getPath() {
		if (side == ChessColor.BLACK)
			return BLACK_KING_PATH;
		else
			return WHITE_KING_PATH;
	}

	public List<ChessTile> getStepOptions(ChessBoard board) {
		ArrayList<ChessTile> steps = new ArrayList<ChessTile>();
		int row = -1, column = -1;
		for (int i = 0; i < board.getN(); i++) {
			for (int j = 0; j < board.getM(); j++) {
				if (board.getTile(i, j).getFigure() == this) {
					row = i;
					column = j;
				}
			}
		}
		if (row == -1)
			return steps;

		steps.addAll(getDiagonalSteppingOptions(board, 1, row, column));
		steps.addAll(getHorizontalSteppingOptions(board, 1, row, column));
		steps.addAll(getVerticalSteppingOptions(board, 1, row, column));
		return steps;
	}

}
