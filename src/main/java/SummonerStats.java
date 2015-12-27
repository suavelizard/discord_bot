import java.util.Arrays;
import java.util.List;

/**
 * Created by Zane on 12/21/2015.
 */
public class SummonerStats {
    private long summonerId;
    private PlayerStatSummary[] playerStatSummaries;

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }

    public PlayerStatSummary[] getPlayerStatSummaries() {
        return playerStatSummaries;
    }

    public void setPlayerStatSummaries(PlayerStatSummary[] playerStatSummaries) {
        this.playerStatSummaries = playerStatSummaries;
    }

    @Override
    public String toString() {
        return "SummonerStats{" +
                "summonerId=" + summonerId +
                ", playerStatSummaries=" + Arrays.toString(playerStatSummaries) +
                '}';
    }
}
