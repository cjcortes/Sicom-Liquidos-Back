package com.sicom.ms.domain.model.common;

import com.sicom.ms.domain.model.di.Injectable;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Injectable
public class TimeProvider {

    public Date currentDate() {
        return new Date();
    }

    public Date currentDatePlus(int amountToAdd, ChronoUnit chronoUnit) {
        return Date.from(Instant.now()
                .plus(amountToAdd, chronoUnit));
    }

    public Date currentDateMinus(int amountToSubtract, ChronoUnit chronoUnit) {
        return Date.from(Instant.now()
                .minus(amountToSubtract, chronoUnit));
    }

}
