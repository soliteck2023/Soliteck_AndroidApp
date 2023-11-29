//package com.example.api_call;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class OperatorAdapter extends RecyclerView.Adapter<MyViewHolder>{
//    Context context;
//    List<Operater> operatorDataArrayList;
//    SelectOperatorfromBack selectOperator;
//
//    public interface SelectOperatorfromBack {
//        void selectOperatorfromlist(int i);
//    }
//    public OperatorAdapter(Context context, List<Operater> operatorDataArrayList) {
//        this.context = context;
//        this.operatorDataArrayList = operatorDataArrayList;
//    }
//
//    public void ChooseOperator(SelectOperatorfromBack selectOperator) {
//        this.selectOperator = selectOperator;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_operators, parent, false);
//        return new MyViewHolder(itemView);
//    }
//
//    @SuppressLint("RecyclerView")
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder,  int position) {
//        Operater listItem = this.operatorDataArrayList.get(position);
//        RequestOptions requestOptions = new RequestOptions();
//        try {
//            requestOptions.placeholder(R.drawable.app_logo);
//        } catch (CloneNotSupportedException e) {
//            throw new RuntimeException(e);
//        }
//        requestOptions.error(R.drawable.app_logo);
//        Glide.with(this.context).setDefaultRequestOptions(requestOptions).load(listItem.getImageUrl()).into(holder.operatorImage);
//        holder.operatorname.setText(listItem.getName());
//        holder.card_operator.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.adapters.OperatorAdapter.1
//            @Override // android.view.View.OnClickListener
//            public void onClick(View v) {
//                OperatorAdapter.this.selectOperator.selectOperatorfromlist(position);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return operatorDataArrayList.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        private CardView card_operator;
//        private ImageView operatorImage;
//        private TextView operatorname;
//
//        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
//        public MyViewHolder(View view) {
//            super(view);
////            OperatorAdapter.this = this$0;
//            this.operatorname = (TextView) view.findViewById(R.id.title1);
//            this.operatorImage = (ImageView) view.findViewById(R.id.list_image1);
//            this.card_operator = (CardView) view.findViewById(R.id.card_operator);
//        }
//    }
//
//}
