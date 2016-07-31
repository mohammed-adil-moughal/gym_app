package com.vorane.gymassistant;

        import android.media.Ringtone;
        import android.media.RingtoneManager;
        import android.net.Uri;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class timeralert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeralert);
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
        Button cancel= (Button) findViewById(R.id.buttoncancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r.stop();
                finish();
            }
        });






    }
    @Override
    public void onBackPressed() { }

}
