package kfs.kfscampaign.service;

import java.io.InputStream;
import java.util.List;
import kfs.kfscampaign.domain.*;
import kfs.mailingservice.domain.MailAddress;

/**
 *
 * @author pavedrim
 */
public interface CampaignService {

    Campaign campaignGet(String name);
    void campaignInsert(Campaign campaign);
    void campaignUdate(Campaign campaign);
    
    MailCampaign campaignAddMailContact(Campaign campaign, MailAddress email);
    
    void mailCampaignUpdateStatus(MailCampaign mailCampaign, MailCampaignStatus newStatus);
    void mailCampaignUpdateStatus(MailCampaign mailCampaign, MailCampaignStatus newStatus, Throwable throwable);

    int campaignImportMailContacts(Campaign campaign, InputStream mails);
    
    List<MailCampaign> campaignLoadEmptyMails(Campaign campaign, int maxCount);
    
}
