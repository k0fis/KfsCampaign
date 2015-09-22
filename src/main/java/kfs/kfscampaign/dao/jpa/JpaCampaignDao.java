package kfs.kfscampaign.dao.jpa;

import kfs.kfscampaign.dao.CampaignDao;
import kfs.kfscampaign.domain.Campaign;
import kfs.springutils.BaseDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavedrim
 */
@Repository
public class JpaCampaignDao extends BaseDaoJpa<Campaign, String> implements CampaignDao {

    @Override
    protected Class<Campaign> getDataClass() {
        return Campaign.class;
    }

    @Override
    protected String getId(Campaign data) {
        return data.getCampaignName();
    }

/*    
    @Override
    public Campaign find(String name) {
        Campaign ret = em.find(Campaign.class, name);
        if (ret == null) {
            ret = new Campaign();
            ret.setCampaignName(name);
            ret.setNote("");
            ret.setCreated(new Timestamp(new Date().getTime()));
            em.persist(em);
        }
        return ret;
    }
*/
}
