package kfs.kfscampaign.dao.jpa;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kfs.kfscampaign.dao.MailCampaignDao;
import kfs.kfscampaign.domain.Campaign;
import kfs.kfscampaign.domain.MailCampaign;
import kfs.kfscampaign.domain.MailCampaignStatus;
import kfs.mailingservice.domain.MailAddress;
import kfs.springutils.BaseDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavedrim
 */
@Repository
public class JpaMailCampaignDao extends BaseDaoJpa<MailCampaign, Long> implements MailCampaignDao {

    @Override
    public MailCampaign findByMail(MailAddress mail) {
        MailCampaign mc = null;
        try {
            em.createQuery("Select mc from MailCampaign mc where mc.mailTo = :mail")
                    .setParameter("mail", mail)
                    .getSingleResult();
        } catch (Exception ex) {
            mc = null;
        }
        if (mc == null) {
            mc = new MailCampaign();
            mc.setInsertDate(new Timestamp(new Date().getTime()));
            mc.setLastModifiedDate(mc.getInsertDate());
            mc.setMailStatus(MailCampaignStatus.empty);
            mc.setMailTo(mail);
            mc.setCampains(new ArrayList<Campaign>());
            em.persist(mc);
        }
        return mc;
    }

    @Override
    public List<MailCampaign> loadCampaignEmpty(Campaign campaign, int maxLen) {
        return em.createQuery("SELECT mc FROM MailCampaign mc WHERE"
                + " mc.mailStatus = :mailStatus AND"
                + " :campaign member of mc.campains")
                .setParameter("mailStatus", MailCampaignStatus.empty)
                .setParameter("campaign", campaign)
                .setMaxResults(maxLen)
                .getResultList();
    }

    @Override
    protected Class<MailCampaign> getDataClass() {
        return MailCampaign.class;
    }

    @Override
    protected Long getId(MailCampaign data) {
        return data.getId();
    }

}
