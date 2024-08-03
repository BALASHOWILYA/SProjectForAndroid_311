package com.example.projecctforandroidlessons.presentation;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.example.projecctforandroidlessons.MApplication;
import com.example.projecctforandroidlessons.R;
import com.example.projecctforandroidlessons.data.roomdb.AppDatabase;
import com.example.projecctforandroidlessons.data.roomdb.StudentRepositoryImpl;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.AddStudentUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.DeleteStudentUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.FindStudentUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.GetAllStudentsUseCase;
import com.example.projecctforandroidlessons.domain.usecase.studentusecase.UpdateStudentUseCase;

public class FragmentSettings extends PreferenceFragmentCompat {

    private StudentViewModel studentViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase database = MApplication.getDatabase();
        StudentRepositoryImpl repository = new StudentRepositoryImpl(database.studentDao());

        // Initialize the use cases
        AddStudentUseCase addStudentUseCase = new AddStudentUseCase(repository);
        UpdateStudentUseCase updateStudentUseCase = new UpdateStudentUseCase(repository);
        FindStudentUseCase findStudentUseCase = new FindStudentUseCase(repository);
        GetAllStudentsUseCase getAllStudentsUseCase = new GetAllStudentsUseCase(repository);
        DeleteStudentUseCase deleteStudentUseCase = new DeleteStudentUseCase(repository);

        // Initialize the ViewModel
        studentViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new StudentViewModel(addStudentUseCase, updateStudentUseCase, findStudentUseCase, getAllStudentsUseCase, deleteStudentUseCase );
            }
        }).get(StudentViewModel.class);
    }

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey);

        // Handle theme preference change
        SwitchPreference themePreference = findPreference("theme_pref");
        if (themePreference != null) {
            themePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                // Restart activity to apply new theme
                requireActivity().recreate();
                return true;
            });
        }

        // Handle delete student preference
        Preference deleteStudentPref = findPreference("delete_student_pref");
        if (deleteStudentPref != null) {
            deleteStudentPref.setOnPreferenceClickListener(preference -> {
                showDeleteStudentDialog();
                return true;
            });
        }
    }

    private void showDeleteStudentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Delete Student")
                .setMessage("Enter the email of the student to delete:")
                .setView(R.layout.dialog_delete_student) // макет с EditText для ввода email
                .setPositiveButton("Delete", (dialog, which) -> {
                    AlertDialog alertDialog = (AlertDialog) dialog;
                    EditText emailInput = alertDialog.findViewById(R.id.email_input);
                    if (emailInput != null) {
                        String email = emailInput.getText().toString().trim();
                        if (!email.isEmpty()) {
                            deleteStudentByEmail(email);
                        } else {
                            Toast.makeText(requireContext(), "Email cannot be empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void deleteStudentByEmail(String email) {
        studentViewModel.deleteStudentByEmail(email);
        Toast.makeText(requireContext(), "Student with email " + email + " deleted", Toast.LENGTH_SHORT).show();
    }
}
