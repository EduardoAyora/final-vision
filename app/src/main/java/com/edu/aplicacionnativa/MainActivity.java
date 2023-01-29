package com.edu.aplicacionnativa;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.edu.aplicacionnativa.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'aplicacionnativa' library on application startup.
    static {
        System.loadLibrary("aplicacionnativa");
    }

    private android.widget.ImageView original, gris;
    private ActivityMainBinding binding;
    private android.widget.Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText("Hello from C++");

        original = findViewById(R.id.imageView);

        boton = findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(stringFromJNI());
                Bitmap bIn =
                        BitmapFactory.decodeResource(getResources(),R.drawable.san_basilio);
                Bitmap bOut = bIn.copy(bIn.getConfig(), true);
                aEscalaGrises(bIn, bOut);
                original.setImageBitmap(bOut);
            }
        });
    }

    /**
     * A native method that is implemented by the 'aplicacionnativa' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native void aEscalaGrises(Bitmap in, Bitmap out);
}