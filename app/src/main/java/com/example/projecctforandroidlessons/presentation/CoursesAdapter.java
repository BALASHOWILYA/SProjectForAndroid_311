package com.example.projecctforandroidlessons.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projecctforandroidlessons.R;
import com.example.projecctforandroidlessons.domain.models.CourseDomain;

import java.util.ArrayList;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    private List<CourseDomain> courses = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(CourseDomain course);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        CourseDomain course = courses.get(position);
        holder.bind(course);
    }

    @Override
    public int getItemCount() {
        return courses != null ? courses.size() : 0;
    }

    public void setCourses(List<CourseDomain> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {

        private final TextView courseNameTextView;
        private final TextView courseCodeTextView;
        private final TextView courseCreditsTextView;

        CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameTextView = itemView.findViewById(R.id.course_name);
            courseCodeTextView = itemView.findViewById(R.id.course_code);
            courseCreditsTextView = itemView.findViewById(R.id.course_credits);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                        onItemClickListener.onItemClick(courses.get(position));
                    }
                }
            });
        }

        void bind(CourseDomain course) {
            courseNameTextView.setText(course.getName());
            courseCodeTextView.setText(String.valueOf(course.getCourseCode()));
            courseCreditsTextView.setText(String.valueOf(course.getCredits()));
        }
    }
}
