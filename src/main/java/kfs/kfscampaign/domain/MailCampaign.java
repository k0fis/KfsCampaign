package kfs.kfscampaign.domain;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import kfs.mailingservice.domain.MailAddress;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author pavedrim
 */
@Entity
public class MailCampaign {

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Long id;

    @OneToOne
    private MailAddress mailTo;

    @Column(nullable = false)
    private Timestamp insertDate;

    @Column(nullable = false)
    private Timestamp lastModifiedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MailCampaignStatus mailStatus;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String errorText;

    @ManyToMany
    private List<Campaign> campains;

    public MailAddress getMailTo() {
        return mailTo;
    }

    public void setMailTo(MailAddress mailTo) {
        this.mailTo = mailTo;
    }

    public Timestamp getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Timestamp insertDate) {
        this.insertDate = insertDate;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public MailCampaignStatus getMailStatus() {
        return mailStatus;
    }

    public void setMailStatus(MailCampaignStatus mailStatus) {
        this.mailStatus = mailStatus;
    }

    public List<Campaign> getCampains() {
        return campains;
    }

    public void setCampains(List<Campaign> campains) {
        this.campains = campains;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
