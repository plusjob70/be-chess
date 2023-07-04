package softeer2nd;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Pawn> pawnList;

    public Board() {
        pawnList = new ArrayList<>();
    }

    public void add(Pawn pawn) {
        pawnList.add(pawn);
    }

    public int size() {
        return pawnList.size();
    }

    public Pawn findPawn(int index) {
        return pawnList.get(index);
    }

}
