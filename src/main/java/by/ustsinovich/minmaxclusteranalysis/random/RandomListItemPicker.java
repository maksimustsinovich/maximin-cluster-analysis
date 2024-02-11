package by.ustsinovich.minmaxclusteranalysis.random;

import java.util.List;
import java.util.Random;

public class RandomListItemPicker<T> {
    private final Random random;
    private final List<T> list;

    public RandomListItemPicker(List<T> list) {
        this.random = new Random();
        this.list = list;
    }

    public T nextItem() {
        int index = random.nextInt(list.size());
        return list.remove(index);
    }

    public List<T> getList() {
        return list;
    }

    public Random getRandom() {
        return random;
    }
}
