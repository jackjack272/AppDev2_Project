package com.example.appdevproject.Budget;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appdevproject.Pages.BudgetPage;
import com.example.appdevproject.R;
import com.example.appdevproject.Utility.ProjectDb;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewItemToBudget extends BottomSheetDialogFragment {
    // this is so the class can be callled
    public static final String TAG="AddNewItemToBudget";

    // these are the data fields that need to be used to get the value.
    private EditText nameOfItem, priceOfItem, yearContract, renewalFee, cancelationFee;
    private Button saveButton;
    private ProjectDb myDb;
    private Spinner category_spinner;
    //--------


    //be able to create new instance.
    public static AddNewItemToBudget newInstace(){
        return new AddNewItemToBudget();
    }



    // which view do you want to pop up?
    @Nullable
    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater,@Nullable ViewGroup container,
        @Nullable Bundle saveInstanceState
    ){
        View v = inflater.inflate(R.layout.budget_add_item, container, false);
        return v;
    }


    // link the fields here.
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState){
        makeAssociations(view);


        //check if its an update request.
            // fill in the fields with values.
            //check for null's else allow update


        //blank out the button.
//        saveButton.setEnabled(true);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences= requireContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                String userName=sharedPreferences.getString("username","");
                int foreignKey= myDb.getUserById(userName);

                //check the values are not null
                Item item= makeItem(foreignKey);

                if(item ==null){
                    return;
                }
                myDb.item_makeOne(item);
                dismiss();
            }
        });
    }

    private void makeAssociations(View view){
        nameOfItem= view.findViewById(R.id.item_getName);
        priceOfItem=view.findViewById(R.id.item_getPrice);
        yearContract=view.findViewById(R.id.item_getContract);
        renewalFee= view.findViewById(R.id.item_getYearlyFee);

        cancelationFee= view.findViewById(R.id.item_getCancelFee);
        category_spinner=view.findViewById(R.id.item_getCategory);
        saveButton= view.findViewById(R.id.item_btn_addNew);

        myDb= new ProjectDb(getActivity());

    }
    private int getCategorySelected(){
        String[] categories= getResources().getStringArray(R.array.item_category);
        String selected=category_spinner.getSelectedItem().toString();
        int categorySelected=-1;
        for(int i=0; i<categories.length; i++ ){
            if(categories[i].equals(selected) ){
                categorySelected=i;
            }
        }
        return categorySelected;
    }

    private Item makeItem(int foreignKey) {
        //fields to fill
        String name;
        Double price, yearlyNewalFee, cancelationFee;
        Integer contractLen, catSelected;

        //fill the fields
        try{
            name= nameOfItem.getText().toString();
        }catch (Exception e){
            nameOfItem.setHint("enter a name");
            name="";
        }

        try{
            price= Double.parseDouble(priceOfItem.getText().toString());
        }catch (Exception e){
            priceOfItem.setHint("enter the price of the item");
            price=-1.0;
        }

        try{
            contractLen=Integer.parseInt(yearContract.getText().toString());
        }catch (Exception e){
            yearContract.setHint("cant be <0");
            contractLen=-1;
        }

        try{
            yearlyNewalFee=Double.parseDouble( renewalFee.getText().toString());
        }catch (Exception e){
            renewalFee.setHint("cant be <0");
            yearlyNewalFee =-1.0; }

        try{
            cancelationFee= Double.parseDouble(this.cancelationFee.getText().toString());
        }catch (Exception e){
            this.cancelationFee.setHint("cant be <0");
            cancelationFee=-1.0;
        }

        //idk how else to indicate which field failed to parse


        //check for bad values
        boolean badValues=false;

        if(name.equals("")){
            badValues=true;
        }
        if(price <0 || contractLen < 0 || yearlyNewalFee < 0 ||cancelationFee < 0 ) {
            badValues = true;
        }

        catSelected= getCategorySelected();
        if(catSelected == -1){
            badValues=true;
        }

        if(!badValues){
            return new Item(
                name, price, catSelected, contractLen,
                yearlyNewalFee, cancelationFee, foreignKey
            );
        }else{
            return null;
        }

    };







    // i belive this one closes the window.
    @Override
    public void onDismiss(@NonNull DialogInterface dialogInterface){
        super.onDismiss(dialogInterface);
        Activity activity= getActivity();

        if(activity instanceof  onDialogCloseListener){
            ((onDialogCloseListener)activity).onDialogClose(dialogInterface);
        }
    }


}









