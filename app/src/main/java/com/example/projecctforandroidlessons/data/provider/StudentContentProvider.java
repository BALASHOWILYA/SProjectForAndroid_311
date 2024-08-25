package com.example.projecctforandroidlessons.data.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projecctforandroidlessons.data.roomdb.AppDatabase;
import com.example.projecctforandroidlessons.data.roomdb.Student;

public class StudentContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.example.projecctforandroidlessons.studentprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/students");

    private static final int STUDENTS = 1;
    private static final int STUDENT_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, "students", STUDENTS);
        uriMatcher.addURI(AUTHORITY, "students/#", STUDENT_ID);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        final Context context = getContext();
        if (context == null) {
            return null;
        }

        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                cursor = AppDatabase.getInstance(context).studentDao().getAllStudentsCursor();
                break;
            case STUDENT_ID:
                cursor = AppDatabase.getInstance(context).studentDao().getStudentByIdCursor(ContentUris.parseId(uri));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        cursor.setNotificationUri(context.getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                return "vnd.android.cursor.dir/vnd.com.example.projecctforandroidlessons.students";
            case STUDENT_ID:
                return "vnd.android.cursor.item/vnd.com.example.projecctforandroidlessons.students";
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final Context context = getContext();
        if (context == null) {
            return null;
        }

        long id;
        if (uriMatcher.match(uri) == STUDENTS) {
            id = AppDatabase.getInstance(context).studentDao().insert(Student.fromContentValues(values));
        } else {
            throw new IllegalArgumentException("Invalid URI for insert: " + uri);
        }

        if (id > 0) {
            Uri insertedUri = ContentUris.withAppendedId(CONTENT_URI, id);
            context.getContentResolver().notifyChange(insertedUri, null);
            return insertedUri;
        } else {
            return null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final Context context = getContext();
        if (context == null) {
            return 0;
        }

        int rowsDeleted;
        if (uriMatcher.match(uri) == STUDENT_ID) {
            long id = ContentUris.parseId(uri);
            rowsDeleted = AppDatabase.getInstance(context).studentDao().deleteStudentById(id);
        } else {
            throw new IllegalArgumentException("Invalid URI for delete: " + uri);
        }

        if (rowsDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final Context context = getContext();
        if (context == null) {
            return 0;
        }

        int rowsUpdated;
        if (uriMatcher.match(uri) == STUDENT_ID) {
            Student student = Student.fromContentValues(values);
            student.setId((int) ContentUris.parseId(uri));
            rowsUpdated = AppDatabase.getInstance(context).studentDao().update(student);
        } else {
            throw new IllegalArgumentException("Invalid URI for update: " + uri);
        }

        if (rowsUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
