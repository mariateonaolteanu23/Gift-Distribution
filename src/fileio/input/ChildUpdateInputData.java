package fileio.input;

import enums.Category;
import enums.Elf;

import java.util.List;

public final class ChildUpdateInputData {
    private int id;
    private double niceScore;
    private List<Category> giftsPreferences;
    private Elf newElf;

    public ChildUpdateInputData(final int id, final double niceScore,
                       final List<Category> giftsPreferences, final Elf newElf) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
        this.newElf = newElf;
    }

    public int getId() {
        return id;
    }

    public double getNiceScore() {
        return niceScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public Elf getNewElf() {
        return newElf;
    }
}
