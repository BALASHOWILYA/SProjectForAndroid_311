package com.example.projecctforandroidlessons.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projecctforandroidlessons.R;
import com.example.projecctforandroidlessons.domain.EnrollmentDomain;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class EnrollmentAdapter extends RecyclerView.Adapter<EnrollmentViewHolder> {

    private List<EnrollmentDomain> enrollments;

    public EnrollmentAdapter(List<EnrollmentDomain> enrollments) {
        this.enrollments = enrollments;
    }

    @NonNull
    @Override
    public EnrollmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enrollment, parent, false);
        return new EnrollmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnrollmentViewHolder holder, int position) {
        EnrollmentDomain enrollment = enrollments.get(position);


        holder.studentName.setText(String.valueOf(enrollment.getStudentId()));
        holder.courseName.setText(String.valueOf(enrollment.getCourseId()));


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = dateFormat.format(enrollment.getEnrollmentDate());
        holder.enrollmentDate.setText(formattedDate);
    }

    @Override
    public int getItemCount() {
        return enrollments.size();
    }

    // Добавьте метод для обновления данных
    public void updateEnrollments(List<EnrollmentDomain> newEnrollments) {
        this.enrollments = newEnrollments;
        notifyDataSetChanged(); // Уведомите адаптер об изменениях
    }
}