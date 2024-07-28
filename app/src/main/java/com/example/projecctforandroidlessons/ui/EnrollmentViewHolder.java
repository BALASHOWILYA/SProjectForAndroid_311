package com.example.projecctforandroidlessons.ui;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projecctforandroidlessons.R;

public class EnrollmentViewHolder extends RecyclerView.ViewHolder {

    public TextView studentName;
    public TextView courseName;
    public TextView enrollmentDate;

    public EnrollmentViewHolder(@NonNull View itemView) {
        super(itemView);
        studentName = itemView.findViewById(R.id.textViewStudentName);
        courseName = itemView.findViewById(R.id.textViewCourseName);
        enrollmentDate = itemView.findViewById(R.id.textViewEnrollmentDate);
    }
}
