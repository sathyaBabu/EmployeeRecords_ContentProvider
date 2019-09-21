package com.example.employeerecords;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.SliceProvider;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.ListBuilder.RowBuilder;
import androidx.slice.builders.SliceAction;

public class EmployeeSliceProvider extends SliceProvider {
    Context mcontext;
    /**
     * Instantiate any required objects. Return true if the provider was successfully created,
     * false otherwise.
     */
    @Override
    public boolean onCreateSliceProvider() {
        mcontext = getContext();
        if (mcontext == null){
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Converts URL to content URI (i.e. content://com.example.employeerecords...)
     */
    @Override
    @NonNull
    public Uri onMapIntentToUri(@Nullable Intent intent) {
        // Note: implementing this is only required if you plan on catching URL requests.
        // This is an example solution.
        Uri.Builder uriBuilder = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT);
        if (intent == null) return uriBuilder.build();
        Uri data = intent.getData();
        if (data != null && data.getPath() != null) {
            String path = data.getPath().replace("/", "");
            uriBuilder = uriBuilder.path(path);
        }
        Context context = getContext();
        if (context != null) {
            uriBuilder = uriBuilder.authority(context.getPackageName());
        }
        return uriBuilder.build();
    }

    /**
     * Construct the Slice and bind data if available.
     */
    public Slice onBindSlice(Uri sliceUri) {

        if (getContext() == null) {
            return null;
        }
        SliceAction activityAction = createActivityAction();
        ListBuilder listBuilder = new ListBuilder(getContext(), sliceUri, ListBuilder.INFINITY);
        if ("/EmployeeSliceProvider/hello".equals(sliceUri.getPath())) {

            // Path recognized. Customize the Slice using the androidx.slice.builders API.
            // Note: ANRs and strict mode is enforced here so don't do any heavy operations.
            // Only bind data that is currently available in memory.
            listBuilder.addRow(new ListBuilder.RowBuilder()
                    .setTitle("Hello World")
                    .setSubtitle("Name : ")

                    .setPrimaryAction(activityAction)
            );
        } else {
            // Error: Path not found.
            listBuilder.addRow(new ListBuilder.RowBuilder()
                    .setTitle("URI not recognized")
                    .setPrimaryAction(activityAction)
            );
        }
        return listBuilder.build();
    }

    private SliceAction createActivityAction() {
        if (getContext() == null) {
            return null;
        }

       // return null;
        //Instead of returning null, you should create a SliceAction. Here is an example:

        return SliceAction.create(
            PendingIntent.getActivity(
                getContext(), 0, new Intent(getContext(), MainActivity.class), 0
            ),
            IconCompat.createWithResource(getContext(), R.drawable.ic_launcher_foreground),
            ListBuilder.ICON_IMAGE,
            "Open App"
        );

    }

    /**
     * Slice has been pinned to external process. Subscribe to data source if necessary.
     */
    @Override
    public void onSlicePinned(Uri sliceUri) {
        // When data is received, call context.contentResolver.notifyChange(sliceUri, null) to
        // trigger EmployeeSliceProvider#onBindSlice(Uri) again.
    }

    /**
     * Unsubscribe from data source if necessary.
     */
    @Override
    public void onSliceUnpinned(Uri sliceUri) {
        // Remove any observers if necessary to avoid memory leaks.
    }
}
