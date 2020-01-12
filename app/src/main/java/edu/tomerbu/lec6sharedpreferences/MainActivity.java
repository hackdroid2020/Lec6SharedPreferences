package edu.tomerbu.lec6sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnSave;
    private Button btnLogOut;
    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.edit_name);
        btnSave = findViewById(R.id.button_save);
        btnLogOut = findViewById(R.id.button_log_out);
        btnLogOut.setOnClickListener(v -> {
            //Delete the value:
            saveUserName(null);
            //show the button and Edit Text:
            btnSave.setVisibility(View.VISIBLE);
            etName.setVisibility(View.VISIBLE);
        });
        
        btnSave.setOnClickListener(v -> {
            saveUserName(etName.getText().toString());
        });

        if (getUserName() != null) {
            Toast.makeText(this, "Hello, " + getUserName(), Toast.LENGTH_SHORT).show();
            //hide the button
            etName.setVisibility(View.GONE);
            btnSave.setVisibility(View.GONE);
            //hide the editText
        }
    }

    private void saveUserName(String userName) {
        SharedPreferences prefs = getSharedPreferences("userDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("user_name", userName);
        editor.apply();
    }

    //read from SharedPreferences:
    private String getUserName() {
        SharedPreferences prefs = getSharedPreferences("userDetails", MODE_PRIVATE);
        return prefs.getString("user_name", null); //TODO
    }
}
