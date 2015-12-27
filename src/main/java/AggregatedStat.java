/**
 * Created by Zane on 12/21/2015.
 */
public class AggregatedStat {
    private int totalChampionKills;
    private int totalMinionKills;
    private int totalTurretsKilled;
    private int totalNeutralMinionsKilled;
    private int totalAssists;

    public int getTotalChampionKills() {
        return totalChampionKills;
    }

    public void setTotalChampionKills(int totalChampionKills) {
        this.totalChampionKills = totalChampionKills;
    }

    public int getTotalMinionKills() {
        return totalMinionKills;
    }

    public void setTotalMinionKills(int totalMinionKills) {
        this.totalMinionKills = totalMinionKills;
    }

    public int getTotalTurretsKilled() {
        return totalTurretsKilled;
    }

    public void setTotalTurretsKilled(int totalTurretsKilled) {
        this.totalTurretsKilled = totalTurretsKilled;
    }

    public int getTotalNeutralMinionsKilled() {
        return totalNeutralMinionsKilled;
    }

    public void setTotalNeutralMinionsKilled(int totalNeutralMinionsKilled) {
        this.totalNeutralMinionsKilled = totalNeutralMinionsKilled;
    }

    public int getTotalAssists() {
        return totalAssists;
    }

    public void setTotalAssists(int totalAssists) {
        this.totalAssists = totalAssists;
    }

    @Override
    public String toString() {
        return "AggregatedStat{" +
                "totalChampionKills=" + totalChampionKills +
                ", totalMinionKills=" + totalMinionKills +
                ", totalTurretsKilled=" + totalTurretsKilled +
                ", totalNeutralMinionsKilled=" + totalNeutralMinionsKilled +
                ", totalAssists=" + totalAssists +
                '}';
    }
}

