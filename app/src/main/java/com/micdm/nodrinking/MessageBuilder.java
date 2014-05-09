package com.micdm.nodrinking;

import android.content.Context;

public class MessageBuilder {

    public static String build(Context context, int days) {
        if (days == 0) {
            return context.getString(R.string.message_first_day);
        } else {
            String plural = context.getResources().getQuantityString(R.plurals.message_day, days);
            return context.getString(R.string.message_many_days, String.valueOf(days), plural);
        }
    }
}
