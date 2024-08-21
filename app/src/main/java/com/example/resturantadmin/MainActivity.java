package com.example.resturantadmin;


import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private EditText foodName, foodPrice;
    private CheckBox availability;
    private Button selectImage, uploadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        foodName = findViewById(R.id.foodName);
//        foodPrice = findViewById(R.id.foodPrice);
//        availability = findViewById(R.id.availability);
//        selectImage = findViewById(R.id.selectImage);
//        uploadButton = findViewById(R.id.uploadButton);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AddUpdateFrag())
                    .commit();
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;

                switch (menuItem.getTitle().toString()) {
                    case "Add":
                        selectedFragment = new AddUpdateFrag();
                        Toast.makeText(MainActivity.this, "Home selected", Toast.LENGTH_SHORT).show();
                        break;

                    case "Orders":
                        selectedFragment = new OrdersFrag();
                        Toast.makeText(MainActivity.this, "Orders selected", Toast.LENGTH_SHORT).show();
                        break;

                    case "Generate QR":
                        selectedFragment = new GenerateQRFrag();
                        Toast.makeText(MainActivity.this, "QR    selected", Toast.LENGTH_SHORT).show();
                       break;
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                }
                return true;
            }

        });

//        selectImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, PICK_IMAGE_REQUEST);
//            }
//        });
//
//        uploadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadData();
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            imageUri = data.getData();
//        }
//    }
//
//    private void uploadData() {
//        final String name = foodName.getText().toString().trim();
//        final String price = foodPrice.getText().toString().trim();
//        final boolean isAvailable = availability.isChecked();
//
//        if (imageUri == null || name.isEmpty() || price.isEmpty()) {
//            Toast.makeText(this, "Please fill all fields and select an image", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        final String uploadUrl = "https://mobileland.in/ResturantDataFolder/foodUpload.php"; // Replace with your server URL
//
//        StringRequest request = new StringRequest(Request.Method.POST, uploadUrl,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(MainActivity.this, "Upload Successful", Toast.LENGTH_LONG).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this, "Upload Failed: " + error.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("name", name);
//                params.put("price", price);
//                params.put("availability", isAvailable ? "1" : "0");
//
//                if (imageUri != null) {
//                    params.put("image", encodeImageToBase64(imageUri));
//                }
//                return params;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(request);
//    }
//
//    private String encodeImageToBase64(Uri uri) {
//        try {
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//            byte[] imageBytes = byteArrayOutputStream.toByteArray();
//            return Base64.encodeToString(imageBytes, Base64.DEFAULT);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
    }
}
