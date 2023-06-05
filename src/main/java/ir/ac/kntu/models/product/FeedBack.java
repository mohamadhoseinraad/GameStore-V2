package ir.ac.kntu.models.product;

import ir.ac.kntu.models.User;

import java.time.LocalDate;
import java.time.Period;

public class FeedBack {

    private Period baseperiod = Period.of(0, 0, 5);

    private final String massage;

    private final String userFeedBackId;

    private final String productId;

    private String lastReqDevId;

    private boolean isAccept = false;

    private boolean isSolved = false;

    private LocalDate timeReq;


    public FeedBack(String massage, User user, Product product) {
        this.massage = massage;
        this.userFeedBackId = user.getId();
        this.productId = product.getId();
        timeReq = java.time.LocalDate.now();
    }

    public boolean isAccept() {
        return isAccept;
    }

    public void setAccept(boolean accept) {
        isAccept = accept;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public String getLastReqDevId() {
        return lastReqDevId;
    }

    public void setLastReqDevId(String lastReqDevId) {
        this.lastReqDevId = lastReqDevId;
    }

    public Period getBaseperiod() {
        return baseperiod;
    }

    public void incrementBasePeriod() {
        baseperiod.multipliedBy(2);
    }

    public String getMassage() {
        return massage;
    }

    public String getUserFeedBackId() {
        return userFeedBackId;
    }

    public String getProductId() {
        return productId;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }
}
