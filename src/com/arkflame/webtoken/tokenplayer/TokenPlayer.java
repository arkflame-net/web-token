package com.arkflame.webtoken.tokenplayer;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TokenPlayer {
    private final static int COOLDOWN_MILLIS = 10000;

    private long lastQuery = System.currentTimeMillis();

    public float getLastQueryLeft() {
        final long leftMillis = COOLDOWN_MILLIS - (System.currentTimeMillis() - lastQuery);

        return new BigDecimal(leftMillis / 1000).setScale(1, RoundingMode.FLOOR).floatValue();
    }
}
