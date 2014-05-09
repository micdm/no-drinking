package com.micdm.nodrinking;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class CustomAppWidgetProvider extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager manager, int[] ids) {
        TimestampManager timestampManager = new TimestampManager(context);
        Integer days = timestampManager.getDaysPassed();
        String message = (days == null) ? null : MessageBuilder.build(context, days);
        for (int id: ids) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
            if (message != null) {
                views.setTextViewText(R.id.message, message);
            }
            views.setOnClickPendingIntent(R.id.widget, getPendingIntent(context));
            manager.updateAppWidget(id, views);
        }
    }

    private PendingIntent getPendingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context, 0, intent, 0);
    }
}
