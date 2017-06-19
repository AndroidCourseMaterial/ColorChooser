package edu.rosehulman.boutell.colorchooser;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

public class InputActivity extends AppCompatActivity {

    private RelativeLayout mLayout;
    private EditText mEditText;
    private int mCurrentBackgroundColor;
    private String mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        mLayout = (RelativeLayout) findViewById(R.id.activity_input_layout);
        mEditText = (EditText) findViewById(R.id.activity_input_message);

        mMessage = "Hello World";
        mCurrentBackgroundColor = Color.GRAY;
        updateUI();

        Button colorButton = (Button) findViewById(R.id.activity_input_button);
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorDialog();
            }
        });
    }

    private void updateUI() {
        mEditText.setText(mMessage);
        mLayout.setBackgroundColor(mCurrentBackgroundColor);
    }

    // From https://android-arsenal.com/details/1/1693
    private void showColorDialog() {
        ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose HSV color")
                .initialColor(mCurrentBackgroundColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(6)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        Toast.makeText(InputActivity.this, "onColorSelected: 0x" + Integer.toHexString(selectedColor), Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton(getString(android.R.string.ok), new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        mCurrentBackgroundColor = selectedColor;
                        mMessage = mEditText.getText().toString();
                        updateUI();
						// TODO: Use an intent to send info back to activity that called this one for a result.
					   
                    }
                })
                .setNegativeButton(getString(android.R.string.cancel), null)
                .build()
                .show();
    }
}
