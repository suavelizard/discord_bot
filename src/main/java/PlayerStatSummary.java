import java.util.Arrays;

/**
 * Created by Zane on 12/21/2015.
 */
public class PlayerStatSummary {
    private String playerStatSummaryType;
    private int wins;
    private long modifyDate;
    private AggregatedStat aggregatedStats;

    public String getPlayerStatSummaryType() {
        return playerStatSummaryType;
    }

    public void setPlayerStatSummaryType(String playplayerStatSummaryType) {
        this.playerStatSummaryType = playplayerStatSummaryType;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public AggregatedStat getAggregatedStats() {
        return aggregatedStats;
    }

    public void setAggregatedStats(AggregatedStat aggregatedStats) {
        this.aggregatedStats = aggregatedStats;
    }

    @Override
    public String toString() {
        return "PlayerStatSummary{" +
                "playplayerStatSummaryType='" + playerStatSummaryType + '\'' +
                ", wins=" + wins +
                ", modifyDate=" + modifyDate +
                ", aggregatedStats=" + aggregatedStats +
                '}';
    }
}

