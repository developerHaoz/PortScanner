package com.example.developerhaoz.portscanner.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.developerhaoz.portscanner.R;
import com.example.developerhaoz.portscanner.bean.PortInfoBean;

import java.util.List;

/**
 *
 * Created by developerHaoz on 2017/6/14.
 */

public class PortInfoAdapter extends Adapter<PortInfoAdapter.PortInfoViewHolder>{

    private List<PortInfoBean> mPortInfoBeanList;
    private Context mContext;
    private String startIp;
    private String endIp;

    public PortInfoAdapter(String startIp, String endIp, Context context, List<PortInfoBean> portInfoBeanList){
        this.startIp = startIp;
        this.endIp = endIp;
        mContext = context;
        this.mPortInfoBeanList = portInfoBeanList;
    }

    @Override
    public PortInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_port_info, parent, false);
        PortInfoViewHolder holder = new PortInfoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PortInfoViewHolder holder, int position) {
        PortInfoBean portInfoBean = mPortInfoBeanList.get(position);
        holder.mTvIp.setText(startIp);
        holder.mTvPort.setText(portInfoBean.getPort());
        holder.mtvService.setText(portInfoBean.getService());
        holder.mtvType.setText(portInfoBean.getType());
    }

    @Override
    public int getItemCount() {
        return mPortInfoBeanList.size() > 0 ? mPortInfoBeanList.size() : 0;
    }

    static class PortInfoViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvIp;
        private TextView mTvPort;
        private TextView mtvService;
        private TextView mtvType;

        public PortInfoViewHolder(View itemView) {
            super(itemView);
            mTvIp = (TextView) itemView.findViewById(R.id.port_info_tv_ip);
            mTvPort = (TextView) itemView.findViewById(R.id.port_info_tv_port);
            mtvService = (TextView) itemView.findViewById(R.id.port_info_tv_service);
            mtvType = (TextView) itemView.findViewById(R.id.port_info_tv_type);
        }
    }
}
