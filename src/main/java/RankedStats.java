import dto.Stats.ChampionStats;

/**
 * Created by Zane on 12/22/2015.
 */
public class RankedStats {
    private long summonerId;
    private long modifyDate;
    private ChampionStats[] champions;

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public ChampionStats[] getChampions() {
        return champions;
    }

    public void setChampions(ChampionStats[] champions) {
        this.champions = champions;
    }
}
