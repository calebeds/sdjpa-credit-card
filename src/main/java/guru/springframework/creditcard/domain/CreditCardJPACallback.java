package guru.springframework.creditcard.domain;

import guru.springframework.creditcard.config.SpringContextHelper;
import guru.springframework.creditcard.services.EncryptionService;
import jakarta.persistence.*;

public class CreditCardJPACallback {

    @PrePersist
    @PreUpdate
    public void beforeInsertOrUpdate(CreditCard creditCard) {
        System.out.println("before update was called...");
        creditCard.setCreditCardNumber(this.getEncryptionService().encrypt(creditCard.getCreditCardNumber()));
    }

    @PostPersist
    @PostLoad
    @PostUpdate
    public void postLoad(CreditCard creditCard) {
        System.out.println("Post Load was called...");
        creditCard.setCreditCardNumber(this.getEncryptionService().decrypt(creditCard.getCreditCardNumber()));
    }

    private EncryptionService getEncryptionService() {
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }
}
