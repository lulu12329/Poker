import java.util.Arrays;

public class Combinations {
    private int nCards;
    private int nColors;
    private int nCardHand;
    private int[] cards;

    public Combinations(int nCards,int nColors,int nCardHand,int[] cards){
        this.nCards=nCards;
        this.nColors=nColors;
        this.nCards=nCards;
    }

    public int[] gleiche(int[] cards) {
        int[] anz = {0, 0, 0, 0};
        int x=nCards/nColors;
        for(int i = 0; i< cards.length-1; i++){
            int a=0;
            for(int j = i+1; j< cards.length; j++){
                if(cards[i]%x== cards[j]%x){
                    a++;
                }
            }
            anz[a]++;
        }

        return anz;
    }

    public boolean straight(int[] _return) {
        for (int i = 1; i < _return.length - 1; i++) {
            if ((_return[0] % 13) +i != _return[i] % 13) {
                return false;
            }
        }
        return true;
    }

    public boolean flush(int[] _return) {
        for (int i = 1; i < _return.length; i++) {
            if (_return[i]/13 != _return[0]/13) {
                return false;
            }
        }
        return true;
    }

    public boolean royal(final int[] _return) {
        if (_return[_return.length-1] % 13 == 12) {
            return true;
        } else {
            return false;
        }
    }

    public boolean fullHouse(int[] _return) {
        int a = getStartOfTripple(_return);

        int[] b = new int[2];
        if (a == 0 || a == 3) {
            for (int i = 0; i < 2; i++) {
                if (a == 0) {
                    b[i] = _return[3 + i];
                } else {
                    b[i] = _return[-3 + i];
                }
            }
            if (b[0] % 13 == b[1] % 13) return true;
        }
        return false;
    }

    public int getStartOfTripple(int[] _return) {
        for (int i = 0; i < _return.length; i++) _return[i] = _return[i] % 13;
        Arrays.sort(_return);

        for (int i = 0; i < _return.length - 1; i++) {
            int counter = 0;
            for (int j = i + 1; j < _return.length; j++) {
                int a = _return[i];
                int b = _return[j];
                if (a == b) {
                    counter++;
                    if (counter == 2) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}
