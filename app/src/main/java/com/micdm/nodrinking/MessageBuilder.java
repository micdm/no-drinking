package com.micdm.nodrinking;

import android.content.Context;

public class MessageBuilder {

    public static String build(Context context, int days) {
        String message = getSpecialDateMessage(context, days);
        if (message != null) {
            return message;
        }
        String plural = context.getResources().getQuantityString(R.plurals.message_day, days);
        return context.getString(R.string.message, String.valueOf(days), plural);
    }

    public static String getSpecialDateMessage(Context context, int days) {
        String name = String.format("special_date_%s", days);
        int id = context.getResources().getIdentifier(name, "string", context.getPackageName());
        if (id == 0) {
            return null;
        }
        return context.getString(id);
    }
}
