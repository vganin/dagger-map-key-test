package net.ganin.dmkt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import net.ganin.dmkt.provider.DumbContentProvider;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.text)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.click_foo)
    void onFooClicked() {
        addToText(DumbContentProvider.request(getContentResolver(), DumbContentProvider.ID_FOO));
    }

    @OnClick(R.id.click_bar)
    void onBarClicked() {
        addToText(DumbContentProvider.request(getContentResolver(), DumbContentProvider.ID_BAR));
    }

    private void addToText(String text) {
        textView.append(text);
    }
}
