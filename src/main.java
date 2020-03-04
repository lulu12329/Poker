import java.util.Arrays;

public class main {
    private final static int nCards = 52;
    private final static int nColors=4;
    private final static int nCardHand = 5;
    private final static int nTrys = 10000000;

    private static int nHighestCard = 0;
    private static int nPairs = 0;
    private static int nTwoPairs = 0;
    private static int nDrilling = 0;
    private static int nStraight = 0;
    private static int nFlush = 0;
    private static int nFullHouse = 0;
    private static int nStraightFlush = 0;
    private static int nFoarOfAKind = 0;
    private static int nRoyalFlush = 0;

    public static void main(String[] args) {
        int[] cards = new int[nCards];
        for (int i = 0; i < nCards; i++) {
            cards[i] = i;
        }
        Combinations c=new Combinations(nCards,nColors,nCardHand,cards);
        for (int i = 0; i < nTrys; i++) {
            int[] hand = getCards(nCardHand, cards);
//            int[] hand={8,9,10,11,12};
            Arrays.sort(hand);

            boolean highCard = true;
            int[] values = c.gleiche(hand);

            if (values[0] > 0) {
                if (c.straight(hand)) {
                    highCard = false;
                    if (c.flush(hand)) {
                        if (c.royal(hand)) {
                            nRoyalFlush++;
                        } else {
                            nStraightFlush++;
                        }
                    } else {
                        nStraight++;
                    }
                } else if (c.flush(hand)) {
                    highCard = false;
                    nFlush++;
                }
            }
            if (values[1] > 0) {
                highCard = false;
                if (values[1] > 1) {
                    nTwoPairs++;
                } else {
                    nPairs++;
                }
            }
            if (values[2] > 0) {
                highCard = false;
                if (c.fullHouse(hand)) {
                    nFullHouse++;
                } else {
                    nDrilling++;
                }
            }
            if (values[3] > 0) {
                highCard = false;
                nFoarOfAKind++;
            }
            if (highCard) {
                nHighestCard++;
            }
        }
        System.out.println("nHighestCard: " + nHighestCard + " procent: " + getProcentValue(nTrys, nHighestCard));
        System.out.println("nPair: " + nPairs + " procent: " + getProcentValue(nTrys, nPairs));
        System.out.println("nTwoPairs: " + nTwoPairs + " procent: " + getProcentValue(nTrys, nTwoPairs));
        System.out.println("nDrilling: " + nDrilling + " procent: " + getProcentValue(nTrys, nDrilling));
        System.out.println("nStraight: " + nStraight + " procent: " + getProcentValue(nTrys, nStraight));
        System.out.println("nFlush: " + nFlush + " procent: " + getProcentValue(nTrys, nFlush));
        System.out.println("nFullHouse: " + nFullHouse + " procent: " + getProcentValue(nTrys, nFullHouse));
        System.out.println("nFoarOfAKind: " + nFoarOfAKind + " procent: " + getProcentValue(nTrys, nFoarOfAKind));
        System.out.println("nStraightFush: " + nStraightFlush + " procent: " + getProcentValue(nTrys, nStraightFlush));
        System.out.println("nRoyalFlush: " + nRoyalFlush + " procent: " + getProcentValue(nTrys, nRoyalFlush));
    }

    private static int[] getCards(int nCardHands, int[] cards) {
        int[] a = new int[nCardHands];
        for (int i = 0; i < nCardHands; i++) {
            int x = (int) (Math.random() * (cards.length-i));
            a[i] = cards[x];
            int y = cards[cards.length - i - 1];
            cards[cards.length - i - 1] = cards[x];
            cards[x] = y;
        }
        return a;
    }

    private static double getProcentValue(int max, int amount) {
        return ((double) amount / (double) max) * 100;
    }
}
