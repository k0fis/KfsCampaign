package kfs.kfscampaign.service.impl;

/**
 *
 * @author pavedrim
 */
public class CampaignException extends RuntimeException {
    public CampaignException(String msg) {
        super(msg);
    }
    public CampaignException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
