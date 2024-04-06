package com.example.stainedglassrecognizer.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Files {
    private static final String TAG = "Files utils";

    public static File getFileFromUri(Context context, Uri uri) {
        try (InputStream inputStream = context.getContentResolver().openInputStream(uri)) {
            if (inputStream == null) {
                Log.e(TAG, "inputStream is null for the URI = " + uri);
                return null;
            }

            return createTempFile(inputStream);

        } catch (FileNotFoundException e) {
            Log.e(TAG, "FileNotFoundException for the URI = " + uri, e);
        } catch (IOException e) {
            Log.e(TAG, "IOException for the URI =  " + uri, e);
        }

        return null;
    }

    private static File createTempFile(InputStream inputStream) throws IOException {
        File file = File.createTempFile("temp", null);
        file.deleteOnExit();

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        return file;
    }
}
