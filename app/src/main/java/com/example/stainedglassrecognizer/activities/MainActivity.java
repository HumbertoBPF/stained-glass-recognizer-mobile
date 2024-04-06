package com.example.stainedglassrecognizer.activities;

import static com.example.stainedglassrecognizer.utils.Files.getFileFromUri;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stainedglassrecognizer.R;
import com.example.stainedglassrecognizer.models.StainedGlass;
import com.example.stainedglassrecognizer.services.StainedGlassClassificationService;
import com.example.stainedglassrecognizer.services.StainedGlassClassificationServiceHelper;
import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ImageView previewImageView;

    private MaterialButton selectButton, uploadButton;

    private StainedGlassClassificationService service;
    private Uri selectedImageUri;

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            this::handleImagePickerResult);

    private void handleImagePickerResult(ActivityResult result) {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();

            if (data != null && data.getData() != null) {
                selectedImageUri = data.getData();
                fillImagePreview();
            }
        }
    }

    private void fillImagePreview() {
        Bitmap previewImageBitmap = null;

        try {
            previewImageBitmap = MediaStore.Images.Media.getBitmap(
                    this.getContentResolver(),
                    selectedImageUri
            );
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        previewImageView.setImageBitmap(previewImageBitmap);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        previewImageView = findViewById(R.id.preview_image_view);

        selectButton = findViewById(R.id.select_button);

        uploadButton = findViewById(R.id.upload_button);

        service = StainedGlassClassificationServiceHelper.getService();

        selectButton.setOnClickListener(view -> launchImagePicker());
        previewImageView.setOnClickListener(view -> launchImagePicker());

        uploadButton.setOnClickListener(view -> uploadFileForClassification());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings_menu_item) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void launchImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        launcher.launch(intent);
    }

    private void setLoadButton(MaterialButton button, boolean isLoading) {
        button.setIconResource(isLoading ? R.drawable.ic_hourglass : 0);
        button.setEnabled(!isLoading);
    }

    private void uploadFileForClassification() {
        if (selectedImageUri == null || selectedImageUri.getPath() == null) {
            Toast.makeText(
                    MainActivity.this,
                    R.string.error_no_image,
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        setLoadButton(uploadButton, true);

        File file = getFileFromUri(this, selectedImageUri);

        if (file == null) {
            Toast.makeText(
                    MainActivity.this,
                    R.string.the_image_file_could_not_be_found_try_again,
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        RequestBody requestFile = RequestBody.create(MultipartBody.FORM, file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        Call<StainedGlass> call = service.classify(body);

        call.enqueue(new Callback<StainedGlass>() {
            @Override
            public void onResponse(Call<StainedGlass> call, Response<StainedGlass> response) {
                StainedGlass stainedGlass = response.body();
                setLoadButton(uploadButton, false);

                if (response.isSuccessful() && stainedGlass != null) {
                    Intent intent = new Intent(MainActivity.this, ImageInfoActivity.class);
                    intent.putExtra("stainedGlass", stainedGlass);
                    startActivity(intent);
                    return;
                }

                onApiError();
            }

            @Override
            public void onFailure(Call<StainedGlass> call, Throwable throwable) {
                setLoadButton(uploadButton, false);
                Log.e("onFailure", "Erreur lors de l'envoi du fichier à l'API", throwable);
                onApiError();
            }
        });
    }

    private void onApiError() {
        Toast.makeText(
                MainActivity.this,
                R.string.api_error_message,
                Toast.LENGTH_SHORT
        ).show();
    }
}