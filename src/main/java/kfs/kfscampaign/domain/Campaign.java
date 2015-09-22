package kfs.kfscampaign.domain;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import kfs.mailingservice.domain.MailTemplate;

/**
 *
 * @author pavedrim
 */
@Entity
public class Campaign {

    @Id
    private String campaignName;
    
    private Timestamp created;
    
    private String note;
    
    @OneToOne
    private MailTemplate mailTemplate;

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.campaignName != null ? this.campaignName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Campaign other = (Campaign) obj;
        return !((this.campaignName == null) ? (other.campaignName != null) : 
                !this.campaignName.equals(other.campaignName));
    }

    public MailTemplate getMailTemplate() {
        return mailTemplate;
    }

    public void setMailTemplate(MailTemplate mailTemplate) {
        this.mailTemplate = mailTemplate;
    }

    
    
}
