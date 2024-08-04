package com.example.projecctforandroidlessons.data.roomdb.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projecctforandroidlessons.MApplication;
import com.example.projecctforandroidlessons.data.roomdb.AppDatabase;
import com.example.projecctforandroidlessons.data.roomdb.Course;

public class CourseContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.projecctforandroidlessons.data.roomdb.provider";
    private static final String COURSE_TABLE = "courses";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + COURSE_TABLE);

    private static final int COURSES = 1;
    private static final int COURSE_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, COURSE_TABLE, COURSES);
        uriMatcher.addURI(AUTHORITY, COURSE_TABLE + "/#", COURSE_ID);
    }

    private AppDatabase database;

    @Override
    public boolean onCreate() {
        database = MApplication.getDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case COURSES:
                cursor = database.courseDao().getAllCursor();
                break;
            case COURSE_ID:
                long id = ContentUris.parseId(uri);
                cursor = database.courseDao().getCourseByIdCursor(id);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case COURSES:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + COURSE_TABLE;
            case COURSE_ID:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + COURSE_TABLE;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long id;
        switch (uriMatcher.match(uri)) {
            case COURSES:
                id = database.courseDao().insert(Course.fromContentValues(values));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        Uri insertUri = ContentUris.withAppendedId(CONTENT_URI, id);
        getContext().getContentResolver().notifyChange(uri, null);
        return insertUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowsDeleted;
        switch (uriMatcher.match(uri)) {
            case COURSE_ID:
                long id = ContentUris.parseId(uri);
                rowsDeleted = database.courseDao().deleteById(id);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        int rowsUpdated;
        switch (uriMatcher.match(uri)) {
            case COURSE_ID:
                long id = ContentUris.parseId(uri);
                Course course = Course.fromContentValues(values);
                course.setId((int) id);
                rowsUpdated = database.courseDao().update(course);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}
