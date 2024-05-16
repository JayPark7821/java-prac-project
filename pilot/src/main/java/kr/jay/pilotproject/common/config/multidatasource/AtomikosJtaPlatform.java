package kr.jay.pilotproject.common.config.multidatasource;

import jakarta.transaction.TransactionManager;
import jakarta.transaction.UserTransaction;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

/**
 * AtomikosJtaPlatform
 *
 * @author jaypark
 * @version 1.0.0
 * @since 1/12/24
 */

@Getter
@Setter
public class AtomikosJtaPlatform extends AbstractJtaPlatform {

    static TransactionManager transactionManager;
    static UserTransaction transaction;

    @Override
    protected TransactionManager locateTransactionManager() {
        return transactionManager;
    }

    @Override
    protected UserTransaction locateUserTransaction() {
        return transaction;
    }
}
