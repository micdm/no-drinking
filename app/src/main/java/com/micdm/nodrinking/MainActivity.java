package com.micdm.nodrinking;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TimestampManager timestampManager = new TimestampManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setup();
    }

    private void setup() {
        Integer days = timestampManager.getDaysPassed();
        if (days == null) {
            showButton();
            hideMessage();
            setupButton();
        } else {
            hideButton();
            showMessage();
            setupMessage(days);
        }
    }

    private void showButton() {
        findViewById(R.id.button).setVisibility(View.VISIBLE);
    }

    private void hideButton() {
        findViewById(R.id.button).setVisibility(View.GONE);
    }

    private void showMessage() {
        findViewById(R.id.message).setVisibility(View.VISIBLE);
    }

    private void hideMessage() {
        findViewById(R.id.message).setVisibility(View.GONE);
    }

    private void setupButton() {
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timestampManager.createTimestamp();
                hideButton();
                showMessage();
                setupMessage(timestampManager.getDaysPassed());
                updateWidget();
            }
        });
    }

    private void setupMessage(int days) {
        TextView view = (TextView) findViewById(R.id.message);
        view.setText(MessageBuilder.build(this, days));
    }

    private void updateWidget() {
        int ids[] = getWidgetIds();
        if (ids.length == 0) {
            return;
        }
        Intent intent = new Intent(this, CustomAppWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent);
    }

    private int[] getWidgetIds() {
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        ComponentName name = new ComponentName(this, CustomAppWidgetProvider.class);
        return manager.getAppWidgetIds(name);
    }
}
