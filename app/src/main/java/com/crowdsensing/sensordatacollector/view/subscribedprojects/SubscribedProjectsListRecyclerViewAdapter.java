package com.crowdsensing.sensordatacollector.view.subscribedprojects;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crowdsensing.sensordatacollector.R;
import com.crowdsensing.sensordatacollector.data.Project;
import com.crowdsensing.sensordatacollector.view.subscribedprojects.SubscribedProjectListFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class SubscribedProjectsListRecyclerViewAdapter extends RecyclerView.Adapter<SubscribedProjectsListRecyclerViewAdapter.ViewHolder> {

    private final List<Project> mProjectList;
    private final OnListFragmentInteractionListener mListener;

    public SubscribedProjectsListRecyclerViewAdapter(List<Project> projectList, OnListFragmentInteractionListener listener) {
        mProjectList = projectList;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_project, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(mProjectList != null){
            holder.mProject = mProjectList.get(position);
            holder.mNameTextView.setText(mProjectList.get(position).id);
//            holder.mSensorCountTextView.setText(mProjectList.get(position).sensors.size() + "");

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.onListFragmentInteraction(holder.mProject);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mProjectList != null ? mProjectList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameTextView;
        public final TextView mSensorCountTextView;
        public Project mProject;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameTextView = view.findViewById(R.id.textProjectName);
            mSensorCountTextView = view.findViewById(R.id.textSensorCount);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mSensorCountTextView.getText() + "'";
        }
    }
}
