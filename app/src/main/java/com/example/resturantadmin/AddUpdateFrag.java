package com.example.resturantadmin;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;


public class AddUpdateFrag extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private EditText foodName, foodPrice;
    private CheckBox availability;
    private ImageView selectImage;
    private Button  uploadButton;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_update, container, false);
        uploadButton = view.findViewById(R.id.uploadButton);

        foodName = view.findViewById(R.id.foodName);
        foodPrice = view.findViewById(R.id.foodPrice);
        availability = view.findViewById(R.id.availability);
        selectImage = view.findViewById(R.id.selectImage);
        uploadButton = view.findViewById(R.id.uploadButton);

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            // Set the image in the ImageView
            selectImage.setImageURI(imageUri);
        }
    }

    private void uploadData() {
        final String name = foodName.getText().toString().trim();
        final String price = foodPrice.getText().toString().trim();
        final boolean isAvailable = availability.isChecked();

        if (imageUri == null || name.isEmpty() || price.isEmpty()) {
            Toast.makeText(getContext(), "Please Enter All The Detailes First", Toast.LENGTH_SHORT).show();
            return;
        }

        final String uploadUrl = "https://mobileland.in/ResturantDataFolder/foodUpload.php"; // Replace with your server URL

        StringRequest request = new StringRequest(Request.Method.POST, uploadUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), "Upload Successful", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Upload Failed: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("price", price);
                params.put("availability", isAvailable ? "1" : "0");

                if (imageUri != null) {
                    params.put("image", encodeImageToBase64(imageUri));
                }
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    private String encodeImageToBase64(Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), uri);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(imageBytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}