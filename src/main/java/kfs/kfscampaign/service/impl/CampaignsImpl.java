package kfs.kfscampaign.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import kfs.kfscampaign.dao.*;
import kfs.kfscampaign.domain.*;
import kfs.kfscampaign.service.CampaignService;
import kfs.mailingservice.domain.MailAddress;
import kfs.mailingservice.service.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pavedrim
 */
@Service
@Transactional
public class CampaignsImpl implements CampaignService {

    @Autowired
    private MailCampaignDao mailCampaignDao;
    @Autowired
    private CampaignDao campaignDao;
    @Autowired
    private MailingService mailing;

    @Override
    public Campaign campaignGet(String name) {
        return campaignDao.findById(name);
    }

    @Override
    public void campaignInsert(Campaign campaign){
        campaignDao.insert(campaign);
    }

    @Override
    public void campaignUdate(Campaign campaign){
        campaignDao.update(campaign);
    }

    @Override
    public MailCampaign campaignAddMailContact(Campaign campaign, MailAddress email) {
        MailCampaign mc = mailCampaignDao.findByMail(email);
        if (!mc.getCampains().contains(campaign)) {
            mc.getCampains().add(campaign);
            mailCampaignDao.update(mc);
        }
        return mc;
    }

    @Override
    public List<MailCampaign> campaignLoadEmptyMails(Campaign campaign, int maxCount) {
        return mailCampaignDao.loadCampaignEmpty(campaign, maxCount);
    }

    @Override
    public int campaignImportMailContacts(Campaign campaign, InputStream mails) {
        int cnt = 0, ok = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(mails, Charset.forName("UTF-8")));
            String mail;
            while ((mail = mailing.filterEmailAddress(br.readLine())) != null) {
                MailAddress ma = mailing.mailAddressFind(mail);
                if (ma == null) {
                    System.err.println("BA: " + mail);
                } else {
                    ok++;
                    campaignAddMailContact(campaign, ma);
                }
            }
        } catch (IOException ex) {
            throw new CampaignException("Cannot read input file", ex);
        }
        return cnt;
    }

    @Override
    public void mailCampaignUpdateStatus(MailCampaign mc, MailCampaignStatus newStatus) {
        mc.setLastModifiedDate(new Timestamp(new Date().getTime()));
        mc.setMailStatus(newStatus);
        mailCampaignDao.update(mc);
    }

    @Override
    public void mailCampaignUpdateStatus(MailCampaign mc, MailCampaignStatus newStatus, Throwable throwable) {
        mc.setLastModifiedDate(new Timestamp(new Date().getTime()));
        mc.setMailStatus(newStatus);
        StringBuilder sb = new StringBuilder();
        sb.append(throwable.getMessage()).append("\n");
        for (StackTraceElement ste : throwable.getStackTrace()) {
            sb.append(ste).append("\n");
        }
        mc.setErrorText(sb.toString());
        mailCampaignDao.update(mc);
    }

}
