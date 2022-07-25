package cat.ironhack.utils;

public class UtilsRandom {
    /**
     * Method used to get a random number given a min and a max
     * @param min will get the minimal value
     * @param max will get the maximal value
     * @return a random number in the range given
     */
    public static int getRandomNum(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}
