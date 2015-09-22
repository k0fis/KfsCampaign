package kfs.kfscampaign.dao;

import java.util.List;
import kfs.kfscampaign.domain.Campaign;
import kfs.kfscampaign.domain.MailCampaign;
import kfs.mailingservice.domain.MailAddress;
import kfs.springutils.BaseDao;

/**
 *
 * @author pavedrim
 */
public interface MailCampaignDao extends BaseDao<MailCampaign, Long>{
    
    public MailCampaign findByMail(MailAddress mail);
    public List<MailCampaign> loadCampaignEmpty(Campaign campaign, int maxLen);
}
