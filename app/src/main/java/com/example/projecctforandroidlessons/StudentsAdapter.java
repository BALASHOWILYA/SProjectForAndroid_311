package com.example.projecctforandroidlessons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projecctforandroidlessons.R;
import com.example.projecctforandroidlessons.domain.models.StudentDomain;
import java.util.ArrayList;
import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder> {

    private List<StudentDomain> students = new ArrayList<>();


    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentDomain student = students.get(position);
        holder.bind(student);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void setStudents(List<StudentDomain> students) {
        this.students = students;
        notifyDataSetChanged();
    }




    static class StudentViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView emailTextView;
        private TextView birthDateTextView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_view_name);
            emailTextView = itemView.findViewById(R.id.text_view_email);
            birthDateTextView = itemView.findViewById(R.id.text_view_birth_date);
        }

        public void bind(StudentDomain student) {
            nameTextView.setText(student.getName());
            emailTextView.setText(student.getEmail());
            birthDateTextView.setText(student.getBirthDate());
        }
    }
}